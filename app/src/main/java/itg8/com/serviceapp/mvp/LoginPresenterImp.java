package itg8.com.serviceapp.mvp;


import android.text.TextUtils;
import android.view.View;

import java.util.List;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.profile.model.ProfileModel;

/**
 * Created by Android itg 8 on 10/9/2017.
 */

public class LoginPresenterImp extends BaseWeakPresenter<LoginMvp.LoginView> implements LoginMvp.LoginPresenter,LoginMvp.LoginListener {


    private final LoginMvp.LoginModule module;

    public LoginPresenterImp(LoginMvp.LoginView view) {
        super(view);
        module = new LoginModuleImp();
    }

    @Override
    public void onDestroy() {
        module.onDestroy();
         if(hasView())
         {
             detactView();
         }

    }

    @Override
    public void onLoginClicked(View view) {
        if(hasView()){
            boolean isValid=true;
            String password=getView().getPassword();
            String username=getView().getUsername();
            if(TextUtils.isEmpty(username)){
                isValid=false;
                getView().onUsernameInvalid(view.getContext().getString(R.string.empty));
            }
            if(TextUtils.isEmpty(password)){
                isValid=false;
                getView().onPasswordInvalid(view.getContext().getString(R.string.empty));
            }
            if(!isValid)
                return;
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                isValid=false;
                getView().onUsernameInvalid(view.getContext().getString(R.string.invalid_email));
            }
            if(password.length()<6){
                isValid=false;
                getView().onPasswordInvalid(view.getContext().getString(R.string.invalid_pass));
            }
            if(isValid){
                getView().showProgress();

               module.onSendToServer((AppApplication.getInstance().getRetroController()),view.getContext().getString(R.string.url_login),username, password, this);

            }
        }


    }

    @Override
    public void onNoInternetConnect(boolean b) {
        if(hasView())
        {
            getView().hideProgress();

            getView().onNoInternetConnect(b);
        }
    }

    @Override
    public void onInternetConnect(boolean b) {


    }

    @Override
    public void onGetProfile(String url) {
        if(hasView()){
            getView().showProgress();

        module.onGetProfileFromServer((AppApplication.getInstance().getRetroController()),url, this);

    }

    }

    @Override
    public void onUsernameInvalid(String err) {
        if(hasView()) {
            getView().hideProgress();
            getView().onUsernameInvalid(err);
        }

    }

    @Override
    public void onPasswordInvalid(String err) {
        if(hasView()) {
            getView().hideProgress();

            getView().onPasswordInvalid(err);
        }

    }

    @Override
    public void showProgress() {
        if(hasView())
        {
            getView().showProgress();

        }

    }

    @Override
    public void hideProgress() {
        if(hasView())
        {
            getView().hideProgress();
        }

    }

    @Override
    public void onSuccess() {
        if(hasView()) {
           getView().hideProgress();
            getView().onSuccess();
        }

    }

    @Override
    public void onFail(String message) {
        if(hasView()) {
            getView().hideProgress();


            getView().onFail(message);
        }



    }

    @Override
    public void onError(Object t) {
        if(hasView()) {
            getView().hideProgress();



            getView().onFail(t.toString());
        }



    }

    public void onGetProfileSuccessList(List<ProfileModel> body) {
        if(hasView()) {
            getView().hideProgress();



            getView().onGetProfileModel(body);
        }


    }
@Override
    public void onFirstTimeLogin(String success) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onFirstTimeLogin(success);
        }
    }
}


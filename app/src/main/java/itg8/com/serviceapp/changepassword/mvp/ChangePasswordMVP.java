package itg8.com.serviceapp.changepassword.mvp;

import android.view.View;

import itg8.com.serviceapp.common_method.RetroController;

/**
 * Created by USER-pc on 10/16/2017.
 */

public interface ChangePasswordMVP {

    public  interface  ChangePswdView
    {
        String getOldPassword();
        String getNewPassword();
        String getConfirmPassword();
        void onSuccess(String status);
        void onFail(String message);
        void onError(Object t);

        void onOldPswdInvalid(String err);

        void onNewPswdInvalid(String err);
        void onConfirmswdInvalid(String err);

        void showProgress();

        void hideProgress();

        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
    }
    public interface ChangePswdPresenter
    {
        void onDestroy();
        void onSubmitButtonClicked(View view, String from, String url);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);


    }

    public interface ChangePswdModule
    {
        void onDestroy();
        void onFail(String message);
        void onAuthenticationToChangePswd(RetroController controller, String url,String from,  String oldpswd, String newpswd, String confirmpswd, ChangePswdPresenterImp listner);
    }

    public interface ChangePswdListener{
        void onSuccess(String status);
        void onFail(String message);
        void onError(Object t);
        void onOldPswdInvalid(String err);

        void onNewPswdInvalid(String err);
        void onConfirmswdInvalid(String err);
        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

    }
}

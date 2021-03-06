package itg8.com.serviceapp.forgetpassword.mvp;


import android.view.View;

import itg8.com.serviceapp.common_method.RetroController;


/**
 * Created by Android itg 8 on 10/13/2017.
 */

public interface ForgetMVP {


    public  interface  ForgetView
    {
        String getEmailId();


        void onSuccess(String message);
        void onFail(String message);
        void onError(Object t);

        void onEmailInvalid(String err);

        void showProgress();

        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
    }
    public interface ForgetPresenter
    {
        void onDestroy();
        void onSubmitButtonClicked(View view);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);


    }

    public interface ForgetModule
    {
        void onDestroy();
        void onFail(String message);
        void onSendForgetToServer(RetroController controller, String email, String url, ForgetPresenterImp listener);
    }

    public interface ForgetListener{
        void onSuccess(String message);
        void onFail(String message);
        void onError(Object t);

        void onEmailInvalid(String err);
        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

    }

}

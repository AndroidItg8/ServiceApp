package itg8.com.serviceapp.mvp;

import android.view.View;

import java.util.List;

import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.profile.model.ProfileModel;

/**
 * Created by Android itg 8 on 10/9/2017.
 */

public interface LoginMvp {
     public  interface  LoginView
     {
         String getUsername();
         String getPassword();
         void onSuccess();
         void onFail(String message);
         void onError(Object t);

         void onUsernameInvalid(String err);

         void onPasswordInvalid(String err);

         void showProgress();

         void hideProgress();
         void onNoInternetConnect(boolean b);
         void onInternetConnect(boolean b);


         void onGetProfileModel(List<ProfileModel> body);

         void onFirstTimeLogin(String success);
     }
      public interface LoginPresenter
      {
          void onDestroy();
          void onLoginClicked(View view);
          void onNoInternetConnect(boolean b);
          void onInternetConnect(boolean b);


          void onGetProfile(String url);
      }

      public interface LoginModule
      {
          void onDestroy();
          void onFail(String message);
          void onSendToServer(RetroController controller, String string, String username, String password, LoginListener loginPresenterImp);

          void onGetProfileFromServer(RetroController retroController, String url, LoginPresenterImp loginPresenterImp);
      }

    public interface LoginListener{
        void onSuccess();
        void onFail(String message);
        void onError(Object t);
        void onUsernameInvalid(String err);
        void onPasswordInvalid(String err);
        void showProgress();
        void hideProgress();
        void onFirstTimeLogin(String success);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
    }
}

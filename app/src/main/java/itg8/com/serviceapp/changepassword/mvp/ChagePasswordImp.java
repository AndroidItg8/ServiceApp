package itg8.com.serviceapp.changepassword.mvp;


import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER-pc on 10/16/2017.
 */

public class ChagePasswordImp implements ChangePasswordMVP.ChangePswdModule {


    private Call<FeedbackModel> call;

    @Override
    public void onDestroy() {
         if(call != null)
         {
             if(!call.isCanceled())
             {
                 call.cancel();
             }
         }

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onAuthenticationToChangePswd(RetroController controller, String url, String from, String oldpswd, String newpswd, String confirmpswd, final ChangePswdPresenterImp listener) {
         if(from  != null && from.equalsIgnoreCase(CommonMethod.FROM_LOGIN))
         {
             call = controller.changePasswordAfterLogin(url,newpswd);
         }else
         {
             call = controller.changePassword(url, oldpswd, newpswd);
         }
          call.enqueue(new Callback<FeedbackModel>() {
              @Override
              public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                  if (response.isSuccessful()) {
                      if(response.body() != null )
                      {
                          if(response.body().getFlag()) {
                              listener.onSuccess(response.body().getStatus());
                          }
                          else
                          {
                              listener.onFail(response.body().getStatus());
                          }

                      }
                      else
                      {
                          listener.onFail(response.body().getStatus());
                      }

                  }else
                  {
                      listener.onFail(response.message());
                  }


              }

              @Override
              public void onFailure(Call<FeedbackModel> call, Throwable t) {
                  if(t instanceof NoConnectivityException)
                  {
                      listener.onNoInternetConnect(true);

                  }else
                  {
                      listener.onError(t);
                  }

              }
          });

    }


}

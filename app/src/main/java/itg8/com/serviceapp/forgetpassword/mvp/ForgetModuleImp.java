package itg8.com.serviceapp.forgetpassword.mvp;

import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public class ForgetModuleImp  implements ForgetMVP.ForgetModule {
    private Call<FeedbackModel> call;

    @Override
    public void onDestroy() {
         if(call != null)
         {
             if(!call.isCanceled())
                 call.cancel();
         }

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onSendForgetToServer(RetroController controller, String email, String url, final ForgetPresenterImp listener) {
    call = controller.forgetPaswd(url, email);
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




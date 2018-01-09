package itg8.com.serviceapp.enquiry.mvp;

import java.util.List;

import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackModel;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class EnquiryModuleImp implements EnquiryMVP.EnquiryModule {
    private Call<List<ProfileModel>> cal;
    private Call<FeedbackModel> call;
    private Call<List<ProductModel>> callproduct;

    @Override
    public void onDestroy() {
         if(call != null){

        if(!call.isCanceled())
         {
             call.cancel();
         }}
         else if(callproduct != null){
        if(!callproduct.isCanceled())
         {
             callproduct.cancel();
         }}

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onSendEnquiryToServer(RetroController controller, Integer productId, String personName, String mobileNumber, String anotherMobileNumber, String email, String query, String url, final EnquiryPresenterImp listener) {
     call = controller.sendEnquiryFormToServer(url ,productId,mobileNumber,query);
        call.enqueue(new Callback<FeedbackModel>() {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                if(response.isSuccessful())
                {
                    if(response.body() != null)
                    {
                        if(response.body().getFlag())
                        {
                            listener.onSuccess(response.body().getStatus());
                        }
                        else{
                            listener.onFail(response.body().getStatus());
                        }
                    }else{
                        listener.onFail(response.body().getStatus());
                    }
                }else
                {listener.onFail(response.body().getStatus());
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

    @Override
    public void onGetProductListFromServer(RetroController retroController, String url, final EnquiryPresenterImp listener) {
     callproduct = retroController.getProductList(url);
        callproduct.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        listener.onGetProductListSuccess(response.body());
                    }else
                    {
                        listener.onFail("Failed to Download");
                    }
                }
                else
                {
                    listener.onFail("Failed to Download");
                }

            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnect(true);
                }else
                {
                    listener.onFail(t.getMessage());
                }

            }
        });
    }

    @Override
    public void onGetProfileFromServer(RetroController controller, String url, final EnquiryPresenterImp listener) {
     cal = controller.getProfile(url);
        cal.enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        listener.onSuccessProfile(response.body());
                    }else
                    {
                        listener.onFail("Download Failed");
                    }
                }else
                {
                    listener.onFail("Download Failed");
                }

            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnect(true);
                }else
                {
                    listener.onFail(t.getMessage());
                }

            }
        });


    }
    }




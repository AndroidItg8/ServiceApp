package itg8.com.serviceapp.ticket.mvp;

import java.util.List;

import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public class RaisedTicketModuleImp implements RaisedTicketMVP.RaisedTicketModule {
    private Call<List<ProblemModel>> callProblem;
    private Call<FeedbackModel> call;

    @Override
    public void onDestroy() {
        if(callProblem!= null)
        {
         if(!callProblem.isCanceled())
             callProblem.cancel();
         }
         else if(call != null){
             if(!call.isCanceled())
                 call.cancel();
         }

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onSendTicketRaisedToServer(RetroController controller, String productName, String personName, String number, String date, String deal, String problem, String problemDescription, int ProductId, int problemId, String url, final RaisedTicketPresenterImp listener) {
       call = controller.sendRaisedTicketInfoToServer(url, number,date, deal, problemId, problemDescription, ProductId ,0);
        call.enqueue(new Callback<FeedbackModel>() {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        if(response.body().getFlag())
                        {
                            listener.onRaisedSubmitSuccess(response.body().getStatus());
                        }
                        else
                        {
                            listener.onFail(response.body().getStatus());
                        }
                    }else
                    {
                        listener.onFail(response.body().getStatus());
                    }
                }else
                {
                    listener.onFail("Submit Failed");
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
    public void onGetProductListFromServer(RetroController controller, String url, final RaisedTicketPresenterImp listener) {
  Call<List<ProductModel>> call = controller.getProductList(url);
        call.enqueue(new Callback<List<ProductModel>>() {
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
    public void onGetProblemListFromServer(RetroController controller, String url, final RaisedTicketPresenterImp listener) {
  callProblem = controller.getProblemList(url);
        callProblem.enqueue(new Callback<List<ProblemModel>>() {
            @Override
            public void onResponse(Call<List<ProblemModel>> call, Response<List<ProblemModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!=null)
                    {
                        listener.onGetProblemListSuccess(response.body());
                    }
                    else
                    {
                        listener.onFail("Download Failed");
                    }
                } else
                {
                    listener.onFail("Download Failed");
                }

            }

            @Override
            public void onFailure(Call<List<ProblemModel>> call, Throwable t) {

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

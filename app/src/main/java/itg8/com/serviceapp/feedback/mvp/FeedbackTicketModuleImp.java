package itg8.com.serviceapp.feedback.mvp;

import java.util.List;

import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackListModel;
import itg8.com.serviceapp.ticket.model.TicketModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER-pc on 10/17/2017.
 */

public class FeedbackTicketModuleImp implements FeedbackTicketMVP.FeedbackTicketModule {
    private Call<List<TicketModel>> call;
    private Call<List<FeedbackListModel>> feedbackListCall;

    @Override
    public void onDestroy() {
         if(call != null)
         {
             if(!call.isCanceled())
             {
                 call.cancel();
             }
         }if(feedbackListCall != null)
         {
             if(!feedbackListCall.isCanceled())
             {
                 feedbackListCall.cancel();
             }
         }

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onGetTicketList(RetroController controller, String url, final FeedbackTicketPresenterImp listener) {
//       call = controller.getTicketList(url);
//        call.enqueue(new Callback<List<TicketModel>>() {
//            @Override
//            public void onResponse(Call<List<TicketModel>> call, Response<List<TicketModel>> response) {
//                if(response.isSuccessful())
//                {
//                    if(response.body()!= null)
//                    {
//                       listener.onSuccess(response.body());
//                    }else
//                    {
//                        listener.onFail("Download Failed");
//                    }
//                }else
//                {
//                    listener.onFail("Download Failed");
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<TicketModel>> call, Throwable t) {
//                 if(t instanceof NoConnectivityException)
//                 {
//                     listener.onNoInternetConnect(true);
//                 }else
//                 {
//                     listener.onError(t);
//                 }
//
//            }
//        });

    }

    @Override
    public void onGetFeedbackList(RetroController retroController, String url, final FeedbackTicketPresenterImp listner) {

   feedbackListCall = retroController.getFeedbackList(url);
        feedbackListCall.enqueue(new Callback<List<FeedbackListModel>>() {
            @Override
            public void onResponse(Call<List<FeedbackListModel>> call, Response<List<FeedbackListModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        listner.onGetFeedbackList(response.body());

                    }else
                    {
                        listner.onFail("Download Failed");
                    }
                }else {
                    listner.onFail("Download Failed");
                }

            }

            @Override
            public void onFailure(Call<List<FeedbackListModel>> call, Throwable t) {
                        t.printStackTrace();
                     listner.onNoInternetConnect(t instanceof NoConnectivityException);


            }
        });
    }
}

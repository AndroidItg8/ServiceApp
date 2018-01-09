package itg8.com.serviceapp.feedback.mvp;

import android.view.View;

import java.util.List;

import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackListModel;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * Created by USER-pc on 10/17/2017.
 */

public interface FeedbackTicketMVP {
    public  interface  FeedbackTicketView
    {

        void onSuccess(List<TicketModel> models);
        void onFail(String message);
        void onError(Object t);



        void showProgress();

        void hideProgress();

        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

        void getFeedbackList(List<FeedbackListModel> body);
    }
    public interface FeedbackTicketPresenter
    {
        void onDestroy();
       void getTicketList(String url);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);


        void getFeedbackList(String url);


    }

    public interface FeedbackTicketModule
    {
        void onDestroy();
        void onFail(String message);
        void onGetTicketList(RetroController controller, String url,FeedbackTicketPresenterImp FeedbackTicketPresenterImp);

        void onGetFeedbackList(RetroController retroController, String url, FeedbackTicketPresenterImp feedbackTicketPresenterImp);
    }

    public interface FeedbackTicketListener{
        void onSuccess(List<TicketModel> list);
        void onFail(String message);
        void onError(Object t);

        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);

        void onGetFeedbackList(List<FeedbackListModel> body);
    }
}

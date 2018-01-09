package itg8.com.serviceapp.feedback.mvp;

import android.view.View;

import itg8.com.serviceapp.common_method.RetroController;

/**
 * Created by Android itg 8 on 10/10/2017.
 */

public interface FeedbackMVP {

    public  interface  FeedbackView
    {
        String getFeedbackTitle();
        String getDescription();
        int getRating();
        void onSuccess(String status);
        void onFail(String message);
        void onError(Object t);

        void onTitleInvalid(String err);

        void onDescriptionInvalid(String err);
        void onRatingBarInvalid(String err);

        void showProgress();

        void hideProgress();

        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
    }
    public interface FeedbackPresenter
    {
        void onDestroy();
        void onSubmitButtonClicked(View view, int ticketId);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);


    }

    public interface FeedbackModule
    {
        void onDestroy();
        void onFail(String message);
        void onSendFeedbackToServer(RetroController controller, String url, int rating, String title, String description, int ticketId, FeedbackPresenterImp feedbackPresenterImp);
    }

    public interface FeedbackListener{
        void onSuccess(String status);
        void onFail(String message);
        void onError(Object t);
        void onTitleInvalid(String err);
        void onDescriptionInvalid(String err);
        void onRatingInvalid(String err);
        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

    }
}

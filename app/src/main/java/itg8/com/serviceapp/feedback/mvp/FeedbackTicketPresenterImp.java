package itg8.com.serviceapp.feedback.mvp;

import java.util.List;

import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.feedback.model.FeedbackListModel;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * Created by USER-pc on 10/17/2017.
 */

public class FeedbackTicketPresenterImp extends BaseWeakPresenter<FeedbackTicketMVP.FeedbackTicketView> implements FeedbackTicketMVP.FeedbackTicketListener, FeedbackTicketMVP.FeedbackTicketPresenter {

    private final FeedbackTicketMVP.FeedbackTicketModule module;

    public FeedbackTicketPresenterImp(FeedbackTicketMVP.FeedbackTicketView feedbackTicketView) {
        super(feedbackTicketView);
        module = new FeedbackTicketModuleImp();
    }

    @Override
    public void onDestroy() {
        module.onDestroy();

    }

    @Override
    public void getTicketList(String url) {
         if(hasView())
         {
            getView().showProgress();
             module.onGetTicketList(AppApplication.getInstance().getRetroController(), url, this);
         }

    }


    @Override
    public void onSuccess(List<TicketModel> list) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onSuccess(list);
        }

    }

    @Override
    public void onInternetConnect(boolean b) {


    }

    @Override
    public void getFeedbackList(String url) {
        if(hasView())
        {
            getView().showProgress();
            module.onGetFeedbackList(AppApplication.getInstance().getRetroController(), url, this);
        }

    }


    @Override
    public void onFail(String message) {
        if (hasView()) {
            getView().showProgress();

            getView().onFail(message);
        }

    }

    @Override
    public void onError(Object t) {
        if (hasView()) {
            getView().showProgress();

            getView().onError(t);
        }
    }


    @Override
    public void showProgress() {
        if (hasView())
            getView().showProgress();
    }

    @Override
    public void hideProgress() {
        if (hasView())
            getView().hideProgress();


    }

    @Override
    public void onNoInternetConnect(boolean b) {
        if (hasView()) {
            getView().hideProgress();
            getView().onNoInternetConnect(b);

        }
    }

    @Override
    public void onGetFeedbackList(List<FeedbackListModel> body) {
        if (hasView()) {
            getView().hideProgress();
            getView().getFeedbackList(body);

        }
    }
}

package itg8.com.serviceapp.feedback.mvp;


import android.text.TextUtils;
import android.view.View;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class FeedbackPresenterImp extends BaseWeakPresenter<FeedbackMVP.FeedbackView> implements FeedbackMVP.FeedbackPresenter, FeedbackMVP.FeedbackListener {


    private final FeedbackMVP.FeedbackModule module;

    public FeedbackPresenterImp(FeedbackMVP.FeedbackView feedbackView) {
        super(feedbackView);
        module = new FeedbackModuleImp();
    }

    @Override
    public void onDestroy() {
        module.onDestroy();

    }

    @Override
    public void onSubmitButtonClicked(View view, int ticketId) {
        if(hasView()){
            boolean isValid=true;
            String title=getView().getFeedbackTitle();
            String description=getView().getDescription();
            int rating=getView().getRating();
            if(TextUtils.isEmpty(title)){
                isValid=false;
                getView().onTitleInvalid(view.getContext().getString(R.string.empty));
            }
            if(TextUtils.isEmpty(description)){
                isValid=false;
                getView().onDescriptionInvalid(view.getContext().getString(R.string.empty));
            }
            if(rating==0)
            {
                isValid =false;
                getView().onRatingBarInvalid(view.getContext().getString(R.string.rating));

            }
            if(isValid){
                getView().showProgress();

                module.onSendFeedbackToServer((AppApplication.getInstance().getRetroController()),view.getContext().getString(R.string.url_feedback),rating, title,description, ticketId,this);

            }
        }

    }

    @Override
    public void onNoInternetConnect(boolean b) {
        if(hasView())
        {
           getView().hideProgress();
            getView().onNoInternetConnect(b);
        }

    }

    @Override
    public void onInternetConnect(boolean b) {

    }

    @Override
    public void onSuccess(String status) {
        if(hasView()) {
            getView().hideProgress();


            getView().onSuccess(status);
        }

    }

    @Override
    public void onFail(String message) {
        if(hasView())
        {
            getView().hideProgress();

            getView().onFail(message);
        }

    }

    @Override
    public void onError(Object t) {
        if(hasView())
        {
            getView().hideProgress();

            getView().onError(t);
        }
    }

    @Override
    public void onTitleInvalid(String err) {
        if(hasView()) {
            getView().hideProgress();

            getView().onTitleInvalid(err);
        }

    }

    @Override
    public void onDescriptionInvalid(String err) {
        if(hasView()) {
            getView().hideProgress();

            getView().onDescriptionInvalid(err);
        }

    }

    @Override
    public void onRatingInvalid(String err) {
        if(hasView()) {
            getView().hideProgress();

            getView().onRatingBarInvalid(err);
        }

    }

    @Override
    public void showProgress() {
        if(hasView())
            getView().showProgress();
    }

    @Override
    public void hideProgress() {
        if(hasView())
            getView().hideProgress();


    }
}

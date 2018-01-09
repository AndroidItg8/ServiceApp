package itg8.com.serviceapp.enquiry.mvp;

import android.text.TextUtils;
import android.view.View;

import java.util.List;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class EnquiryPresenterImp extends BaseWeakPresenter<EnquiryMVP.EnquiryView> implements EnquiryMVP.EnquiryPresenter, EnquiryMVP.EnquiryListener {


    private final EnquiryMVP.EnquiryModule module;

    public EnquiryPresenterImp(EnquiryMVP.EnquiryView enquiryView) {
        super(enquiryView);
        module = new  EnquiryModuleImp();
    }

    @Override
    public void onDestroy() {
        module.onDestroy();

    }

    @Override
    public void onSubmitButtonClicked(View view, Integer productId) {
        if(hasView()) {
            boolean isValid = true;
            String personName = getView().getPersonName();
            String productName = getView().getProductName();
            String mobileNumber = getView().getMobileNumber();
            String emailId = getView().getEmailId();
            String mobileAnother = getView().getAnotherMobileNumber();
            String query = getView().getQuery();

            if (TextUtils.isEmpty(personName)) {
                isValid = false;
                getView().onPersonNameInvalid(view.getContext().getString(R.string.empty));
            }
            if (TextUtils.isEmpty(productName)) {
                isValid = false;
                getView().onProductNameInvalid(view.getContext().getString(R.string.empty));
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
                isValid = false;
                getView().onEmailInvalid(view.getContext().getString(R.string.invalid_email));
            }


                if(TextUtils.isEmpty(mobileNumber)){
                    isValid=false;
                    getView().onMobileInvalid(view.getContext().getString(R.string.empty));
                }


                if(mobileNumber.length()!=10){
                    isValid=false;
                    getView().onMobileInvalid(view.getContext().getString(R.string.invalid_number));
                }

            if(TextUtils.isEmpty(mobileAnother)){
                isValid=false;
                getView().onAnotherMobileInvalid(view.getContext().getString(R.string.empty));
            }

            if(mobileAnother.length()!= 10){
                isValid=false;
                getView().onAnotherMobileInvalid(view.getContext().getString(R.string.invalid_number));
            }
                if(TextUtils.isEmpty(query)){
                    isValid=false;
                    getView().onQuerynvalid(view.getContext().getString(R.string.empty));
                }



            if(isValid){
                getView().showProgress();
                module.onSendEnquiryToServer((AppApplication.getInstance().getRetroController()),productId, personName,mobileNumber,mobileAnother,emailId,query, view.getContext().getString(R.string.url_enquiry) ,this);

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
        if(hasView())
            getView().hideProgress();
            getView().onSuccess();

    }



    @Override
    public void onProductNameInvalid(String err) {
        if(hasView())
            getView().onProductNameInvalid(err);

    }

    @Override
    public void onPersonNameInvalid(String err) {
        if(hasView())
            getView().onPersonNameInvalid(err);

    }

    @Override
    public void onMobileInvalid(String err) {
        if(hasView())
            getView().onMobileInvalid(err);

    }

    @Override
    public void onAnotherMobileInvalid(String err) {
        if(hasView())
            getView().onAnotherMobileInvalid(err);
    }

    @Override
    public void onEmailInvalid(String err) {
        if(hasView())
        {
            getView().onEmailInvalid(err);

        }

    }

    @Override
    public void onQuerynvalid(String err) {
        if(hasView())
            getView().onQuerynvalid(err);

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

    @Override
    public void onFail(String message) {
        if(hasView())
            getView().hideProgress();

        getView().onFail(message);

    }

    @Override
    public void onError(Object t) {
        if(hasView())
            getView().hideProgress();


        getView().onError(t);

    }
    public void onGetProductListSuccess(List<ProductModel> list) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onProductList(list);
        }

    }

    @Override
    public void onGetProductList(String url) {
        if(hasView()) {
            getView().showProgress();

            module.onGetProductListFromServer(AppApplication.getInstance().getRetroController(),url, this);
        }


    }

    @Override
    public void onGetProfile(String url) {
        if(hasView()) {
            getView().showProgress();

            module.onGetProfileFromServer(AppApplication.getInstance().getRetroController(),url, this);
        }

    }

    public void onSuccessProfile(List<ProfileModel> body) {
         if(hasView()) {
             getView().hideProgress();

             getView().getProfile(body);
         }


    }
}

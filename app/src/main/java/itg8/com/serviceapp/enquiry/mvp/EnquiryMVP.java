package itg8.com.serviceapp.enquiry.mvp;

import android.view.View;

import java.util.List;

import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public interface EnquiryMVP {
    public  interface  EnquiryView
    {
        String getProductName();
        String getPersonName();
        String getEmailId();
        String getMobileNumber();
        String getAnotherMobileNumber();
        String getQuery();

        void onSuccess();
        void onFail(String message);
        void onError(Object t);

        void onProductNameInvalid(String err);
        void onPersonNameInvalid(String err);
        void onMobileInvalid(String err);
        void onAnotherMobileInvalid(String err);
        void onEmailInvalid(String err);
        void onQuerynvalid(String err);

        void showProgress();

        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

        void onProductList(List<ProductModel> list);

        void getProfile(List<ProfileModel> body);
    }
    public interface EnquiryPresenter
    {
        void onDestroy();
        void onSubmitButtonClicked(View view, Integer productId);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
        void onGetProductList(String url);



        void onGetProfile(String string);
    }

    public interface EnquiryModule
    {
        void onDestroy();
        void onFail(String message);
        void onSendEnquiryToServer(RetroController controller, Integer productId, String personName, String mobileNumber, String anotherMobileNumber, String email, String query, String url, EnquiryPresenterImp enquiryPresenterImp);


        void onGetProductListFromServer(RetroController retroController, String url, EnquiryPresenterImp enquiryPresenterImp);

        void onGetProfileFromServer(RetroController retroController, String url, EnquiryPresenterImp enquiryPresenterImp);
    }

    public interface EnquiryListener{
        void onSuccess(String status);
        void onFail(String message);
        void onError(Object t);
        void onProductNameInvalid(String err);
        void onPersonNameInvalid(String err);
        void onMobileInvalid(String err);
        void onAnotherMobileInvalid(String err);
        void onEmailInvalid(String err);
        void onQuerynvalid(String err);
        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);


    }


}

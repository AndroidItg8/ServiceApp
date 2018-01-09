package itg8.com.serviceapp.ticket.mvp;

import android.view.View;

import java.util.List;

import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public interface RaisedTicketMVP {

    public  interface RaisedTicketView {
        void onRaisedTicketSubmitSuccess(String message);
        String getProductName();
        String getPersonName();
        String getInvoiceNumber();
        String getInvoiceDate();
        String getProductProblem();
        String getProductDeal();
        String getPreblemDescription();

        void onProductNameInvalid(String err);
        void onPersonNameInvalid(String err);
        void onInvoiceNumberInvalid(String err);
        void onInvoiceDateInvalid(String err);
        void onProductProblemInvalid(String err);
        void onPreblemDescriptionInvalid(String err);
        void onProductDealInvalid(String err);
        void onFail(String message);
        void onError(Object t);
        void showProgress();
        void hideProgress();
        void onProductList(List<ProductModel> list);
        void onProblemList(List<ProblemModel> list);

        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

    }

    public interface RaisedTicketPresenter {
        void onDestroy();

        void onSubmitButtonClicked(View view, Integer productId, int problemId);
        void onGetProductProblemList(String url);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
        void onGetProductList(String url);

        void onGetProfile(String url);
    }


    public interface RaisedTicketModule {
        void onDestroy();
        void onFail(String message);
        void onSendTicketRaisedToServer(RetroController controller, String productName, String personName, String invoiceNumber, String InvoiceDate, String deal, String problem, String problemDescription, int ProductId, int problemId, String url, RaisedTicketPresenterImp listener);
        void onGetProductListFromServer(RetroController controller, String url, RaisedTicketPresenterImp listener);
        void onGetProblemListFromServer(RetroController controller, String url, RaisedTicketPresenterImp listener);
    }

    public interface RaisedTicketListener {
        void onFail(String message);

        void onError(Object t);
        void showProgress();
        void hideProgress();
        void onRaisedSubmitSuccess(String message);
        void onGetProductListSuccess(List<ProductModel> list);
        void onGetProblemListSuccess(List<ProblemModel> list);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);
    }




    }

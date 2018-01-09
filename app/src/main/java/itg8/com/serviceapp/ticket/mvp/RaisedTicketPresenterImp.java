package itg8.com.serviceapp.ticket.mvp;

import android.text.TextUtils;
import android.view.View;

import java.util.List;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public class RaisedTicketPresenterImp extends BaseWeakPresenter<RaisedTicketMVP.RaisedTicketView> implements RaisedTicketMVP.RaisedTicketPresenter, RaisedTicketMVP.RaisedTicketListener {
    private RaisedTicketMVP.RaisedTicketModule module;

    public RaisedTicketPresenterImp(RaisedTicketMVP.RaisedTicketView raisedTicketView) {
        super(raisedTicketView);
        module = new RaisedTicketModuleImp();
    }

    @Override
    public void onDestroy() {
        module.onDestroy();
        if(hasView())
        {
            detactView();
        }

    }

    @Override
    public void onSubmitButtonClicked(View view, Integer productId, int problemId) {
        boolean isValid=true;


        if(hasView()) {
            String personName = getView().getPersonName();
            String productName = getView().getProductName();
            String invoiceNumber = getView().getInvoiceNumber();
            String invoiceDate = getView().getInvoiceDate();
            String deal = getView().getProductDeal();
            String problem = getView().getProductProblem();
            String problemDescription = getView().getPreblemDescription();

//            if (TextUtils.isEmpty(personName)) {
//                isValid = false;
//                getView().onPersonNameInvalid(view.getContext().getString(R.string.empty));
//            }
            if (TextUtils.isEmpty(productName)) {
                isValid = false;
                getView().onProductNameInvalid(view.getContext().getString(R.string.empty));
            }
            if (TextUtils.isEmpty(invoiceNumber)) {
                isValid = false;
                getView().onInvoiceNumberInvalid(view.getContext().getString(R.string.empty));
            }

            if (TextUtils.isEmpty(invoiceDate)) {
                isValid = false;
                getView().onInvoiceDateInvalid(view.getContext().getString(R.string.empty));
            }
            if (TextUtils.isEmpty(deal)) {
                isValid = false;
                getView().onProductDealInvalid(view.getContext().getString(R.string.empty));
            }

            if (TextUtils.isEmpty(problem)) {
                isValid = false;
                getView().onProductProblemInvalid(view.getContext().getString(R.string.empty));
            }
//            if (TextUtils.isEmpty(problemDescription)) {
//                isValid = false;
//                getView().onPreblemDescriptionInvalid(view.getContext().getString(R.string.empty));
//            }


            if (isValid) {
                getView().showProgress();

                module.onSendTicketRaisedToServer((AppApplication.getInstance().getRetroController()), productName, personName, invoiceNumber, invoiceDate, deal, problem,problemDescription, productId, problemId,view.getContext().getString(R.string.url_raised_ticket),this);

            }
        }

    }





    @Override
    public void onNoInternetConnect(boolean b) {
        if(hasView()) {
            getView().hideProgress();
        getView().onNoInternetConnect(b);
        }
    }

    @Override
    public void onInternetConnect(boolean b) {
        if(hasView()) {
            getView().hideProgress();
            getView().onInternetConnect(b);
        }
    }

    @Override
    public void onGetProductList(String url) {
        if(hasView()) {
            showProgress();
            module.onGetProductListFromServer(AppApplication.getInstance().getRetroController(),url, this);
        }
//
    }

    @Override
    public void onGetProfile(String url) {

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
    public void showProgress() {
        if(hasView())
        {
            getView().showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if(hasView())
        {
            getView().hideProgress();
        }
    }

    @Override
    public void onRaisedSubmitSuccess(String message) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onRaisedTicketSubmitSuccess(message);
        }

    }

    @Override
    public void onGetProductListSuccess(List<ProductModel> list) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onProductList(list);
        }

    }


    @Override
    public void onGetProblemListSuccess(List<ProblemModel> list) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onProblemList(list);
        }
    }
    @Override
    public void onGetProductProblemList(String url) {
        if(hasView()) {
           getView().showProgress();
            module.onGetProblemListFromServer(AppApplication.getInstance().getRetroController(),url, this);
        }

    }
}

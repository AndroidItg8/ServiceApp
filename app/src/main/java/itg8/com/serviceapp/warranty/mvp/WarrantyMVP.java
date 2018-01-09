package itg8.com.serviceapp.warranty.mvp;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.warranty.WarrantyModel;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public interface WarrantyMVP {


    public  interface WarrantyView extends BaseView
    {

        void onSuccess(List<WarrantyModel> list);
        void onDownloadPdf();

    }
    public interface WarrantyPresenter extends BasePresenter
    {
        void onGetList(String url);
        void onDownloadInvoice(int invoiceNumber);

        RecyclerView.OnScrollListener recyclerViewScrllListener(LinearLayoutManager linearLayoutManager);
    }

    public interface WarrantyModule extends BaseModule
    {
        //url,page,limit, invoiceNumber,this

        void onGetListFromServer( String url,int page,int limit,WarrantyListener listener);
        void onDownloadInvoiceFromServer(RetroController controller, WarrantyListener listener, int invoiceNumber);

    }

    public interface WarrantyListener extends BaseListener{
        void onSuccess(List<WarrantyModel> list, int page);
         void onInvoicePdf();


    }
}

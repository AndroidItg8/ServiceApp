package itg8.com.serviceapp.warranty.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.common_method.Logs;
import itg8.com.serviceapp.warranty.WarrantyModel;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public class WarrantyPresenterImp extends BaseWeakPresenter<WarrantyMVP.WarrantyView> implements WarrantyMVP.WarrantyPresenter, WarrantyMVP.WarrantyListener {
    private final WarrantyMVP.WarrantyModule module;
    private int page=0;
    private int limit=10;
    private String url;
    private boolean isLoading=false;
    private boolean isFinished=false;

    public WarrantyPresenterImp(WarrantyMVP.WarrantyView view) {
        super(view);
        module = new WarrantyModuleImp();
    }


    @Override
    public void onGetList(String url) {
        this.url =url;
        getItems(0,limit);


    }

    @Override
    public void onDownloadInvoice(int invoiceNumber) {
///            module.onDownloadInvoiceFromServer((url,page,limit, invoiceNumber,this );


    }

    @Override
    public void onSuccess(List<WarrantyModel> list, int page) {
        if (hasView()) {
            if (page != 0)
                getView().onShowPaginationLoading(false,1);

            getView().onProgressHide(1);

            if (list.size() > 0)
                getView().onSuccess(list);
            else {
              isFinished= true;
            }
          isLoading= false;
        }
    }

    @Override
    public void onInvoicePdf() {
        if (hasView()) {
            getView().onProgressHide(1);
            getView().onDownloadPdf();
        }

    }

    @Override
    public void onError(String mesg, int from) {
        if (hasView()) {
            getView().onProgressHide(from);
            getView().onError(mesg, from);

        }

    }

    @Override
    public void onNoInternetConnection(boolean b, int from) {
        if (hasView()) {
            getView().onProgressHide(1);
            getView().onNoInternet(b,1);
        }
    }

    @Override
    public void onPaginationError(int from) {

    }

    @Override
    public void emptyList() {
        if (hasView()) {
            getView().onProgressHide(1);
            getView().emptyList(1);
        }
    }

    @Override
    public void onNoMoreList(int status) {

    }

    @Override
    public void onDestroyed() {
        module.onDestroy();
        if (hasView()) {
            detactView();
        }

    }

    @Override
    public void onShowPaginationLoading(boolean show, int from) {

    }



    private void getItems(int page, int limit) {
        if(hasView()){
            if(page!=0)
                getView().onShowPaginationLoading(true,1);
            else
                getView().onProgressShow(1);
            isLoading=true;
            module.onGetListFromServer(url,page,limit, this);



        }
    }

    @Override
    public RecyclerView.OnScrollListener recyclerViewScrllListener(final LinearLayoutManager linearLayoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                Logs.d("visibleItemCount:"+visibleItemCount);

                int totalItemCount = linearLayoutManager.getItemCount();
                Logs.d("totalItemCount:"+totalItemCount);

                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                Logs.d("firstVisibleItemPosition:"+firstVisibleItemPosition);

                if (!isLoading && !isFinished)
                {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0)
                    {

                        page++;
                        Logs.d("Page:"+page);

                        getItems(page,limit);
                    }
                }

            }
        };
    }
}

package itg8.com.serviceapp.ticket.mvp;

import java.util.List;

import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public class TicketPresenterImp extends BaseWeakPresenter<TicketMVP.TicketView> implements TicketMVP.TicketPresenter, TicketMVP.TicketListener {
    private final TicketMVP.TicketModule module;
    private String loadUrl;
    private int limit=10;

    public TicketPresenterImp(TicketMVP.TicketView ticketView) {
        super(ticketView);
        module = new TicketModuleImp();
    }

    @Override
    public void onDestroyed() {
        module.onDestroy();
        if(hasView())
        {
            detactView();
        }

    }

    @Override
    public void onShowPaginationLoading(boolean show, int from) {

    }

    @Override
    public void onDwonloadTicketList(String url, int from) {
        getItems(0,from);

    }

    @Override
    public void onDownloadedOpenTicketList(List<TicketModel> list, int page, int status) {
        if (hasView()) {
            if (page != 0)
                getView().onShowPaginationLoading(false, status);



            getView().onProgressHide(status);

            if (list.size() > 0)
                getView().onSuccess(list, status);
            else {
                getView().onFinished(status, true);
            }
            getView().isLoading(status, false);
        }
    }

    @Override
    public void onDownloadedCloseTicketList(List<TicketModel> list, int page, int status) {
        if (hasView()) {
            getView().onProgressHide(status);

            if (page != 0)
                getView().onShowPaginationLoading(false, status);


            if (list.size() > 0)
                getView().onSuccess(list, status);
            else {
                getView().onFinished(status, true);
            }
            getView().isLoading(status, false);
        }
    }

    @Override
    public void onDownloadedAcceptTicketList(List<TicketModel> list, int page, int status) {
        if (hasView()) {
            if (page != 0)
                getView().onShowPaginationLoading(false, status);

            getView().onProgressHide(status);

            if (list.size() > 0)
                getView().onSuccess(list, status);
            else {
                getView().onFinished(status, true);
            }
            getView().isLoading(status, false);
        }
    }

    @Override
    public void onNoMoreList(int from) {
        if (hasView()) {
            getView().onShowPaginationLoading(false, from);
            getView().onProgressHide(from);
        }
    }

    @Override
    public void onError(String mesg, int from) {

    }

    @Override
    public void onNoInternetConnection(boolean b, int from) {

    }

    @Override
    public void onPaginationError(int from) {
        if (hasView()) {
            getView().onShowPaginationLoading(false, from);
            getView().onPaginationError(true, from);
        }
    }

    @Override
    public void emptyList() {

    }




    @Override
    public void getItems(int page, int from) {
        if (hasView()) {
            if (page != 0)
                getView().onShowPaginationLoading(true, from);
            else
                getView().onProgressShow(from);


            switch (from) {
                case CommonMethod.TICKET_STATUS_OPEN:
                    module.onDownloadedOpenTicketList(loadUrl, page, limit, from, this);
                    break;
                case CommonMethod.TICKET_STATUS_ASSIGN:
                    module.onDownloadedAcceptTicketList(loadUrl, page, limit, from, this);
                    break;
                case CommonMethod.TICKET_STATUS_CLOSE:
                    module.onDownloadedCloseTicketList(loadUrl, page, limit, from, this);
                    break;
            }


        }
    }

    @Override
    public void emptyList(int from) {
        if (hasView()) {
            getView().onProgressHide(from);
            getView().emptyList(from);
        }
    }


}

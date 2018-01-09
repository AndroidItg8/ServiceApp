package itg8.com.serviceapp.ticket.mvp;



import java.util.List;

import itg8.com.serviceapp.ticket.model.TicketModel;
import itg8.com.serviceapp.warranty.mvp.BaseListener;
import itg8.com.serviceapp.warranty.mvp.BaseModule;
import itg8.com.serviceapp.warranty.mvp.BasePresenter;
import itg8.com.serviceapp.warranty.mvp.BaseView;


/**
 * Created by Android itg 8 on 10/13/2017.
 */

public interface TicketMVP {



    public  interface TicketView extends BaseView
    {

        void onSuccess(List<TicketModel> list, int status);
        void onFinished(int from, boolean b);
        void isLoading(int from, boolean b);

    }


    public interface TicketPresenter extends BasePresenter
    {
        void getItems(int page,  int from);


        void onDwonloadTicketList(String url, int from);
    }

    public interface TicketModule extends BaseModule
    {
        void onDownloadedOpenTicketList(String url,int page, int limit, int status,TicketListener listner);
        void onDownloadedCloseTicketList(String url,int page, int limit, int status,TicketListener listner);
        void onDownloadedAcceptTicketList(String url,int page, int limit, int status,TicketListener listner);
}

    public interface TicketListener extends BaseListener{
        void onDownloadedOpenTicketList(List<TicketModel> list,int page,int status);
        void onDownloadedCloseTicketList(List<TicketModel> list,int page,int status);
        void onDownloadedAcceptTicketList(List<TicketModel> list,int page,int status);
        void emptyList(int from);

    }
}

package itg8.com.serviceapp.warranty.mvp;

/**
 * Created by Android itg 8 on 1/8/2018.
 */

public  interface BaseView {

    void onProgressHide(int from);
    void onProgressShow(int from);
    void onNoInternet(boolean b, int from);
    void onError(String mesg, int from);
    void onShowPaginationLoading(boolean show, int from);
    void onPaginationError(boolean show, int from);

    void emptyList(int from);
}

package itg8.com.serviceapp.warranty.mvp;

/**
 * Created by Android itg 8 on 1/8/2018.
 */

 public interface BaseListener {

    void onError(String mesg, int from);
    void onNoInternetConnection(boolean b, int from);
    void onPaginationError(int from);

    void emptyList();

    void onNoMoreList(int status);
}

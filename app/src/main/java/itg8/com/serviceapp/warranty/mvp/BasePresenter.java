package itg8.com.serviceapp.warranty.mvp;

/**
 * Created by Android itg 8 on 1/8/2018.
 */

 public interface BasePresenter {
    void onDestroyed();
    void onShowPaginationLoading(boolean show, int from);
}

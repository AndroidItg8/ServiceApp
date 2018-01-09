package itg8.com.serviceapp.showcase.mvp;


import java.util.HashMap;
import java.util.List;

import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.showcase.model.ShowCaseModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;


/**
 * Created by Android itg 8 on 10/13/2017.
 */

public interface ShowCaseMVP {


    public  interface ShowcaseView
    {
        void onShowCaseSuccess(List<ProductModel> list);
        void onFail(String message);
        void onError(Object t);
        void showProgress();
        void hideProgress();
        void onCategorySuccess(List<CategoryModel> categoryList);
         void onBannerlist(List<BannerModel> list);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

        String getProductListUrl();

        void onProductWithCatLoaded(HashMap<Integer, List<ProductModel>> data);
    }
    public interface ShowcasePresenter
    {
        void onDestroy();
        void onGetCategoryList(String url);
        void onDownloadShowCase(String url);
        void onGetBannerList(String url);
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

        void onGetProblemList(String url);
    }

    public interface ShowcaseModule
    {
        void onDestroy();
        void onFail(String message);
        void onGetCategoryListFromServer(RetroController controller, String url, ShowcasePresenterImp listener);
        void onDownloadShowCaseListFromServer(RetroController controller, String url, ShowcasePresenterImp listener);
        void onBannerList(RetroController controller, String url, ShowcasePresenterImp listener);

        void downloadProductList(String productUrl, ShowcaseListener showcasePresenterImp);
    }

    public interface ShowcaseListener{
        void onSuccess(List<ProductModel> list);
        void onCategoryList(List<CategoryModel> listCatgory);
        void onBannerList(List<BannerModel> body);
        void onFail(String message);
        void onError(Object t);
        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);
        void onInternetConnect(boolean b);

        void onProductList(List<ProductModel> body);

        void onProductWithCatLoaded(HashMap<Integer, List<ProductModel>> data);
    }
}

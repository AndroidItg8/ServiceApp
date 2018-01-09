package itg8.com.serviceapp.showcase.mvp;

import java.util.HashMap;
import java.util.List;

import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.BaseWeakPresenter;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public class ShowcasePresenterImp extends BaseWeakPresenter<ShowCaseMVP.ShowcaseView> implements ShowCaseMVP.ShowcasePresenter,ShowCaseMVP.ShowcaseListener {
    private final ShowCaseMVP.ShowcaseModule module;

    public ShowcasePresenterImp(ShowCaseMVP.ShowcaseView showcaseView) {
        super(showcaseView);
         module = new ShowCaseModuleImp();
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
    public void onGetCategoryList(String url) {
        if(hasView())
        {
            getView().showProgress();
        }
        module.onGetCategoryListFromServer(AppApplication.getInstance().getRetroController(),url, this);

    }

    @Override
    public void onDownloadShowCase(String url) {
        if(hasView())
        {
            getView().showProgress();
            module.onDownloadShowCaseListFromServer(AppApplication.getInstance().getRetroController(),url, this);
        }
    }

    @Override
    public void onGetBannerList(String url) {
        if(hasView())
        {
            getView().showProgress();
            module.onBannerList(AppApplication.getInstance().getRetroController(),url, this);
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
    public void onProductList(List<ProductModel> body) {
        AppApplication.getInstance().setProductListWithCategory(body , this);
    }

    @Override
    public void onProductWithCatLoaded(HashMap<Integer, List<ProductModel>> data) {
        if(hasView()){
            getView().hideProgress();
            getView().onProductWithCatLoaded(data);
        }
    }

    @Override
    public void onGetProblemList(String url) {

    }

    @Override
    public void onSuccess(List<ProductModel> list) {
         if(hasView())
         {
             getView().hideProgress();
             getView().onShowCaseSuccess(list);
         }

    }

    @Override
    public void onCategoryList(List<CategoryModel> listCatgory) {
        if(hasView())
        {
            AppApplication.getInstance().setCategoryList(listCatgory);
            getView().onCategorySuccess(listCatgory);
            String productUrl=getView().getProductListUrl();
            module.downloadProductList(productUrl,this);
        }

    }

    @Override
    public void onBannerList(List<BannerModel> body) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onBannerlist(body);
        }
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
}

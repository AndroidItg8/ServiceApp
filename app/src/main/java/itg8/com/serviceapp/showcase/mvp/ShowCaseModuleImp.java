package itg8.com.serviceapp.showcase.mvp;

import com.j256.ormlite.dao.Dao;

import java.util.List;

import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.database.BaseDatabaseHelper;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 10/13/2017.
 */

public class ShowCaseModuleImp   extends BaseDatabaseHelper implements ShowCaseMVP.ShowcaseModule {
    private Call<List<ProductModel>> call;
    private Call<List<CategoryModel>> callCategory;
    private Call<List<BannerModel>> callBanner;
    private Call<List<ProductModel>> callProduct;

    Dao<BannerModel,Integer> mBannerDAO=null;

    @Override
    public void onDestroy() {
         if(call != null) {
             if (!call.isCanceled()) {
                 call.cancel();
             }
         }
       else  if(callCategory != null) {
          if (!callCategory.isCanceled()) {
              callCategory.cancel();
            }
        }
         else  if(callBanner != null) {
             if (!callBanner.isCanceled()) {
                 callBanner.cancel();
             }
         }

    }

    @Override
    public void onFail(String message) {


    }

    @Override
    public void onGetCategoryListFromServer(RetroController controller, String url, final ShowcasePresenterImp listener) {
        callCategory = controller.getCategoryList(url);
        callCategory.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onCategoryList(response.body());
                    } else {
                        listener.onFail("Download Failed");
                    }
                } else
                {
                    listener.onFail("Download Failed");
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnect(true);
                }else
                {
                    listener.onError(t);
                }

            }
        });
    }

    @Override
    public void onDownloadShowCaseListFromServer(RetroController controller, String url, final ShowcasePresenterImp listener) {
          call = controller.getProductList(url);
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        listener.onSuccess(response.body());
                    }else
                    {
                        listener.onFail("Download Failed");
                    }
                }else
                {
                    listener.onFail("Download Failed");
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnect(true);
                }else
                {
                    listener.onError(t);
                }

            }
        });

    }

    @Override
    public void onBannerList(RetroController controller, String url, final ShowcasePresenterImp listener) {
      callBanner = controller.getBanner(url);
        callBanner.enqueue(new Callback<List<BannerModel>>() {
            @Override
            public void onResponse(Call<List<BannerModel>> call, Response<List<BannerModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!=null)
                    {
                       // listener.onBannerList(response.body());
                        saveIntoDatabse(response.body());
                    }else
                    {
                        listener.onFail("Download Failed");
                    }
                }
                else
                {
                    listener.onFail("Download Failed");
                }


            }

            @Override
            public void onFailure(Call<List<BannerModel>> call, Throwable t) {
                 if(t instanceof  NoConnectivityException)
                 {
                     listener.onNoInternetConnect(true);
                 }else {
                     listener.onError(t);
                 }

            }
        });
    }

    private void saveIntoDatabse(List<BannerModel> body) {
     //   mBannerDAO = getHelper(context).getmDAOBanner();
    }


    @Override
    public void downloadProductList(String productUrl, final ShowCaseMVP.ShowcaseListener listener) {
        callProduct= AppApplication.getInstance().getRetroController().getProductList(productUrl);
        callProduct.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!=null)
                    {
                        listener.onProductList(response.body());
                    }else
                    {
                        listener.onFail("Download Failed");
                    }
                }
                else
                {
                    listener.onFail("Download Failed");
                }

            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

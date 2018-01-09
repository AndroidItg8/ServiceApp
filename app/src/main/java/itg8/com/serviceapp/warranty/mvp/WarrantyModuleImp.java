package itg8.com.serviceapp.warranty.mvp;



import java.util.List;

import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.warranty.WarrantyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public class WarrantyModuleImp implements WarrantyMVP.WarrantyModule {
    private Call<List<WarrantyModel>> call;

    @Override
    public void onDestroy() {
         if(call != null)
         {
             if(!call.isCanceled())
             {
                 call.cancel();
             }
         }


    }








    @Override
    public void onGetListFromServer(String url, final int page, int limit, final WarrantyMVP.WarrantyListener listener) {
        call = AppApplication.getInstance().getRetroController().getWarrantyList(url,page,limit);
        call.enqueue(new Callback<List<WarrantyModel>>() {
            @Override
            public void onResponse(Call<List<WarrantyModel>> call, Response<List<WarrantyModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listener.onSuccess(response.body(), page);
                    }
                    else {
                        if (page == 0) {
                            listener.emptyList();

                        } else {
                            listener.onPaginationError(1);
                        }
                    }
                }else
                {
                    listener.onError("Download Failed",1);

                }

            }

            @Override
            public void onFailure(Call<List<WarrantyModel>> call, Throwable t) {
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnection(true, 1);

                }else
                {
                    listener.onError(t.getMessage(),1);
                }

            }
        });
    }

    @Override
    public void onDownloadInvoiceFromServer(RetroController controller, WarrantyMVP.WarrantyListener listener, int invoiceNumber) {

    }
}

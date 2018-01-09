package itg8.com.serviceapp.common_method;

import android.Manifest;
import android.app.Application;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.showcase.mvp.ShowCaseMVP;
import itg8.com.serviceapp.showcase.mvp.ShowcasePresenterImp;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@ReportsCrashes(formUri = "", mailTo = "app.itechgalaxy@gmail.com", mode = ReportingInteractionMode.SILENT)
public class AppApplication extends Application{

    private static final String PROFILE_MODEL = "PROFILE_MODEL";
    private static final String SHARED = "SHARED";
    private static final String TAG = AppApplication.class.getSimpleName();
    private static AppApplication mInstance;
    private RetroController retroController;
    private ProfileModel profileModel=null;
    private List<CategoryModel> categoryList;
    private List<ProductModel> productList;
    private HashMap<Integer,List<ProductModel>> productByCat=new HashMap<>();

    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //  ACRA.init(this);
        mInstance.initPreference();
        retroController = mInstance.buildRetrofit();

    }

    private void initPreference() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(MODE_PRIVATE)
                .setPrefsName(SHARED)
                .setUseDefaultSharedPreference(false)
                .build();
    }

    private RetroController buildRetrofit() {
//         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//         OkHttpClient.Builder builder = new OkHttpClient.Builder();
//         builder.connectTimeout(5, TimeUnit.MINUTES);
//         builder.addInterceptor(interceptor);
//         builder.readTimeout(5, TimeUnit.MINUTES);
//         if(header!=null)
//             builder.addInterceptor(getHeader(header));
//
//         OkHttpClient client=builder.build();
//         Gson gson = new GsonBuilder().setLenient().create();
//
//         Retrofit retrofit = new Retrofit.Builder()
//                 .baseUrl(CommonMethod.BASE_URL)
//                 .addConverterFactory(GsonConverterFactory.create(gson))
//                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                 .client(client)
//                 .build();

        return Retro.getInstance().getController(Prefs.getString(CommonMethod.HEADER, null), getApplicationContext());
    }


    public RetroController getRetroController() {
        if (retroController == null)
            retroController = buildRetrofit();

        return retroController;

    }

    public void resetRetroAfterLogin() {
        retroController = null;
    }

    public boolean checkEasyPermission(Context context) {
        return EasyPermissions.hasPermissions(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }




     public void  getProfileModel(final CommonMethod.GetProfileListener listener)
     {
        if(profileModel!= null)
           listener.onSuccess(profileModel);
         else
        {
            downloadProfile(listener);
        }


     }
      public void downloadProfile(final CommonMethod.GetProfileListener listener)
      {
         Call<List<ProfileModel>> cal = getRetroController().getProfile(getString(R.string.url_profile));
          cal.enqueue(new Callback<List<ProfileModel>>() {
              @Override
              public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                  if(response.isSuccessful())
                  {
                      if(response.body()!= null && response.body().size()>0)
                      {
                          profileModel = response.body().get(0);
                          listener.onSuccess(profileModel);


                      }else
                      {
                          listener.onFailure("Download Failed");
                      }
                  }else
                  {
                      listener.onFailure("Download Failed");
                  }

              }

              @Override
              public void onFailure(Call<List<ProfileModel>> call, Throwable t) {


                      listener.onFailure(t);



              }
          });

      }


    public void setCategoryList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    public List<CategoryModel> getCategoryList() {
        return categoryList;
    }

    public HashMap<Integer, List<ProductModel>> getProductByCat() {
        return productByCat;
    }

    public void setProductListWithCategory(final List<ProductModel> body, final ShowCaseMVP.ShowcaseListener listener) {
        this.productList=body;
        AsyncTaskLoader<HashMap<Integer, List<ProductModel>>> a = new AsyncTaskLoader<HashMap<Integer, List<ProductModel>>>(this) {
            @Override
            public HashMap<Integer, List<ProductModel>> loadInBackground() {
                productByCat.clear();
                for (CategoryModel m :
                        getCategoryList()) {
                    List<ProductModel> pModel=productByCat.get(m.getPkid());
                    if(pModel==null)
                        pModel=new ArrayList<>();
                    for (ProductModel pm :
                            body) {
                        if (Objects.equals(pm.getCategoryid(), m.getPkid())
                                && !pModel.contains(pm)){
                            pModel.add(pm);
                        }
                    }
                    productByCat.put(m.getPkid(),pModel);
                }
                return productByCat;
            }

            @Override
            public void deliverResult(HashMap<Integer, List<ProductModel>> data) {
                listener.onProductWithCatLoaded(data);
            }
        };

        a.forceLoad();
    }
}

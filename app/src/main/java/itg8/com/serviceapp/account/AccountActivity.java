package itg8.com.serviceapp.account;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.loopj.android.http.RequestParams;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.account.model.CustomerCareModel;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.account.model.VideoModel;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.common_method.UtilityHelper;
import itg8.com.serviceapp.database.BaseDatabaseHelper;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.widget.searchview.SearchAdapter;
import itg8.com.serviceapp.widget.searchview.SearchEditText;
import itg8.com.serviceapp.widget.searchview.SearchHistoryTable;
import itg8.com.serviceapp.widget.searchview.SearchItem;
import itg8.com.serviceapp.widget.searchview.SearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity implements SearchWordAdapter.OnSearchSelectListner {

    private static final String FROM_KNOWLEDGE = "FROM_KNOWLEDGE";
    private static final String FROM_CUSTOMER_CARE = "FROM_CUSTOMER_CARE";
    private static final String FROM_VIDEO = "FROM_VIDEO";
    private static final String TAG = AccountActivity.class.getSimpleName();
    public CommonMethod.AccountListner listner;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.searchView)
    itg8.com.serviceapp.widget.searchview.SearchView searchView;
    private SearchHistoryTable searchHistoryTable;
    private SearchAdapter searchAdater;
    private List<BannerModel> bannerList;
    private List<BannerModel> keywordBannerList = new ArrayList<>();
    private HashMap<Integer, BannerModel> hashMap = new HashMap<>();
    private HashMap<String, KnowledgeModel> hashMapApp = new HashMap<>();
    private HashMap<Integer, VideoModel> hashMapVideo = new HashMap<>();
    private String filterBannerName = null;
    private BannerModel filterBannerModel;
    private String from;
    private Call<List<KnowledgeModel>> call;
    private boolean isDestroyed = false;
    private List<KnowledgeModel> knowledgeList;
    private List<VideoModel> videoList;
    private List<CustomerCareModel> caustomerCareList;
    private Call<List<VideoModel>> callVideo;
    private Call<List<CustomerCareModel>> callCustomer;
    private Snackbar snackbar;
    private Fragment fragment;
    private Dao<BannerModel, Integer> mDAOBanner = null;
    private List<SearchItem> filters = new ArrayList<>();
    private SearchEditText editText;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_knowledge:
                    from = FROM_KNOWLEDGE;
                    filters.clear();
                    fragment = KnowlegeFragment.newInstance(knowledgeList);
                    toolbar.setTitle("Knowledge");
                    break;

                case R.id.nav_customer_care:
                    from = FROM_CUSTOMER_CARE;
                    fragment = CustomerCareFragment.newInstance(caustomerCareList);
                    toolbar.setTitle("Customer care");
                    break;
                case R.id.nav_video:
                    from = FROM_VIDEO;
                    fragment = VideoLibFragment.newInstance(videoList);
                    toolbar.setTitle("Video library");
                    break;
            }

            callsaerch();


            if (fragment != null) {
                UtilityHelper.callFragment(fragment, AccountActivity.this);
                return true;
            }

            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setUpSearchView();
        callWebService();


    }

    private void setUpSearchView() {
        searchHistoryTable = new SearchHistoryTable(this);
     editText= (SearchEditText) searchView.findViewById(R.id.searchEditText_input);


        searchView.setFocusable(true);
//        serachView.setIconified(false);
        searchView.setArrowOnly(true);

        searchView.requestFocusFromTouch();
        searchView.setVoice(false);

        searchAdater = new SearchAdapter(AccountActivity.this);
        searchView.setAdapter(searchAdater);


        searchAdater.addOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

             TextView   textView = (TextView) view.findViewById(R.id.textView_item_text);
                String query = textView.getText().toString();
                editText.setText(query);

                try {
                    searchHistoryTable.addItem(new SearchItem(R.drawable.ic_home_black_24dp, query));
//                    onSearchSelect(searchWord);
                    onSearchSelected(query);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                searchView.setVersion(SearchView.VERSION_TOOLBAR);
                searchView.setShouldHideOnKeyboardClose(true);
                searchView.close(true);

            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String word = s;
                if (!s.isEmpty()) {
                    RequestParams params = new RequestParams();
                    params.put("BrandName", s);
                    // callsearchService(params);
                    //callsaerch(s);
                    onSearchSelected(word);

                }
                return true;
            }
        });


        searchView.setOnMenuClickListener(new SearchView.OnMenuClickListener() {
            @Override
            public void onMenuClick() {
                finish();
            }
        });
    }


    private void onSearchSelected(String searchWord) {

        if (from.equalsIgnoreCase(FROM_VIDEO)) {
            List<VideoModel> tempVideoList = TepFilterVideo(searchWord);
            if (tempVideoList != null && tempVideoList.size()>0) {
                callFragment(tempVideoList);
            }else
            {
                callFragment(videoList);

            }
        } else if (from.equalsIgnoreCase(FROM_CUSTOMER_CARE)) {

            List<CustomerCareModel> tempCareList = setUpCustomerCareAdapter(searchWord);
            if (tempCareList != null && tempCareList.size()>0) {
                callCareFragment(tempCareList);
            }else
            {
                callCareFragment(caustomerCareList);
            }
        } else if (from.equalsIgnoreCase(FROM_KNOWLEDGE)) {

            List<KnowledgeModel> tempKnowledgeList = setUpKnowledgeFilter(searchWord);
            if (tempKnowledgeList != null && tempKnowledgeList.size()>0) {
                callKnowledgeFragment(tempKnowledgeList);
            }else
            {
                callKnowledgeFragment(knowledgeList);
            }
        }

    }

    private void callKnowledgeFragment(List<KnowledgeModel> tempKnowledgeList) {
        if (fragment instanceof CustomerCareFragment) {
            fragment= KnowlegeFragment.newInstance(tempKnowledgeList);
            UtilityHelper.callFragment(fragment, AccountActivity.this);

        }
    }

    private void callCareFragment(List<CustomerCareModel> tempCareList) {
         if(fragment instanceof CustomerCareFragment) {
             fragment = CustomerCareFragment.newInstance(tempCareList);
             UtilityHelper.callFragment(fragment, AccountActivity.this);
         }
    }

    private void callFragment(List<VideoModel> tempVideoList) {
        if (fragment instanceof VideoLibFragment) {
            fragment= VideoLibFragment.newInstance(tempVideoList);
            UtilityHelper.callFragment(fragment, AccountActivity.this);

        }
    }

    private List<KnowledgeModel> setUpKnowledgeFilter(String searchWord) {
        List<KnowledgeModel> tempList = new ArrayList<>();
        for (KnowledgeModel model : knowledgeList) {
            if (searchWord.equalsIgnoreCase(model.getAppName())) {
                tempList.add(model);

            }
            if (searchWord.equalsIgnoreCase(bannerList.get(bannerList.size() - 1).getBrandName())) {
                tempList.add(model);
            }

        }




        return tempList;
    }

    private void callsaerch() {


        if (from.equalsIgnoreCase(FROM_KNOWLEDGE)) {
            try {
                mDAOBanner = BaseDatabaseHelper.getBaseInstance().getHelper(this).getmDAOBanner();


            } catch (SQLException e) {
                e.printStackTrace();
            }

            setUpSearchAdapter();

        } else if (from.equalsIgnoreCase(FROM_VIDEO)) {
            editText.setText("");
            filters.clear();
            List<VideoModel> tempList = new ArrayList<>();
            if(videoList!= null) {
                for (VideoModel model : videoList) {
                    SearchItem searchItem = new SearchItem(R.drawable.ic_home_black_24dp, model.getDefination());
                    filters.add(searchItem);
                }
            }else
            {
                showToast("Download Failed");
            }

        } else if (from.equalsIgnoreCase(FROM_CUSTOMER_CARE)) {


            editText.setText("");

            filters.clear();
            if (caustomerCareList != null) {
                for (CustomerCareModel model : caustomerCareList) {
                    try {
                        bannerList = mDAOBanner
                                .queryBuilder()
                                .where()
                                .eq(BannerModel.FIELD_ID, model.getBrandfkid())
                                .query();


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }
            } else {
                showToast("Download Failed");
            }

            if (bannerList != null && bannerList.size() > 0) {
                HashMap<String, BannerModel> hashmap = new HashMap<>();
                for (BannerModel model : bannerList) {
                    hashmap.put(model.getBrandName(), model);
                }

                for (HashMap.Entry<String,BannerModel> bannerModel : hashmap.entrySet()) {
                    SearchItem searchItem = new SearchItem(R.drawable.ic_home_black_24dp, bannerModel.getKey());
                    filters.add(searchItem);
                }
                // String bannerNanme = bannerList.get(bannerList.size()-1).getBrandName();

            }




        }


        searchAdater.setSuggestionsList(filters);
        searchAdater.notifyDataSetChanged();

    }

    private void setUpSearchAdapter() {
        String bannerName = " ";
        filters.clear();
        editText.setText("");

        SearchItem searchItem = null;
        List<KnowledgeModel> tempList = new ArrayList<>();

if(knowledgeList!= null) {
    for (KnowledgeModel model : knowledgeList) {
        try {
            bannerList = mDAOBanner
                    .queryBuilder()
                    .where()
                    .eq(BannerModel.FIELD_ID, model.getBrandid())
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (bannerList != null && bannerList.size() > 0) {
            bannerName = bannerList.get(bannerList.size() - 1).getBrandName();
        } else {
            bannerName = " ";
        }
//                hashMapApp.put(model.getAppName(), model);
//                searchItem = new SearchItem(R.drawable.ic_home_black_24dp, bannerNanme, model.getAppName());
//                filters.add(searchItem);
        searchItem = new SearchItem();
        searchItem.setIcon(R.drawable.ic_home_black_24dp);
        searchItem.setText(bannerName);
        searchItem.setTextSecond(model.getAppName());
        tempList.add(model);
        searchItem.setList(tempList);
    }
}else
{
    showToast("Download Failed");
}




        filters.add(searchItem);
//        searchAdater.notifyDataSetChanged();
        Log.d(TAG, "Filter seachItem:" + new Gson().toJson(searchItem));


    }

    private List<VideoModel> TepFilterVideo(String searchWord) {

        List<VideoModel> tempList = new ArrayList<>();


        for (VideoModel model : videoList) {
            if (searchWord.equalsIgnoreCase(model.getDefination())) {
                tempList.add(model);
                Log.d(TAG, "VideoLists:" + new Gson().toJson(tempList));

            }


        }

        Log.d(TAG, "VideoList:" + new Gson().toJson(tempList));
        return tempList;

    }

    private void callWebService() {

        downloadAcknowldgeData(getString(R.string.url_acc_knowledge));
        downloadCustomerCateList(getString(R.string.url_acc_customer_care));
        downloadVideoList(getString(R.string.url_acc_video));
    }

    private void setFragment(List<KnowledgeModel> body) {
        fragment = KnowlegeFragment.newInstance(body);
        UtilityHelper.callFragment(fragment, AccountActivity.this);
        from = FROM_KNOWLEDGE;
        callsaerch();

    }

    private void downloadVideoList(String url) {
        RetroController api = AppApplication.getInstance().getRetroController();
        callVideo = api.getVideoList(url);
        callVideo.enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {

                hideProgress();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        videoList = response.body();
                        return;
                    }
                }

                showToast("Download  Failed");
            }

            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {

                     hideProgress();
                     t.printStackTrace();
                     showToast(t.getMessage());

            }
        });
    }

    private void downloadCustomerCateList(String url) {
        RetroController api = AppApplication.getInstance().getRetroController();
        callCustomer = api.getCustomerCareList(url);
        callCustomer.enqueue(new Callback<List<CustomerCareModel>>() {
            @Override
            public void onResponse(Call<List<CustomerCareModel>> call, Response<List<CustomerCareModel>> response) {
                hideProgress();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        caustomerCareList = response.body();
                        return;
                    }
                }

                showToast("Download  Failed");

            }


            @Override
            public void onFailure(Call<List<CustomerCareModel>> call, Throwable t) {
                hideProgress();
                t.printStackTrace();
                showToast(t.getMessage());
            }
        });


    }

    private void downloadAcknowldgeData(String url) {
        RetroController api = AppApplication.getInstance().getRetroController();
        showProgress();
        call = api.getAcckowledeAccountData(url);
        call.enqueue(new Callback<List<KnowledgeModel>>() {
            @Override
            public void onResponse(Call<List<KnowledgeModel>> call, Response<List<KnowledgeModel>> response) {
                hideProgress();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        knowledgeList = response.body();
                        setFragment(response.body());
                        return;
                    }
                }else {

                    showToast("Download  Failed");
                }

            }

            @Override
            public void onFailure(Call<List<KnowledgeModel>> call, Throwable t) {
                hideProgress();

                if (t instanceof NoConnectivityException) {
                    showSnackbar(true);
                } else {

                    showToast(t.getMessage());
                    t.printStackTrace();
                }


            }
        });


    }

    private void showSnackbar(boolean isConnected) {

        int color;
        String message;
        if (!isConnected) {

            message = "Connected to Internet";
            color = Color.WHITE;
            hideSnackbar();

        } else {
            message = " Not connected to internet...Please try again";
            color = Color.RED;
        }
        snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setMaxLines(2);
        snackbar.show();


        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSnackbarOkClicked();

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked() {
        downloadAcknowldgeData(getString(R.string.url_acc_knowledge));
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    private void showProgress() {
        if (!isDestroyed)
            progressView.setVisibility(View.VISIBLE);

    }

    private void hideProgress() {
        if (!isDestroyed)
            progressView.setVisibility(View.GONE);
    }

    private void showToast(String s) {
        if (!isDestroyed)
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        isDestroyed = true;
        if (call != null) {
            if (!call.isCanceled())
                call.cancel();
        }
        if (callCustomer != null) {
            if (!callCustomer.isCanceled())
                callCustomer.cancel();
        }
        if (callVideo != null) {
            if (!callVideo.isCanceled())
                callVideo.cancel();
        }

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSearchSelect(BannerModel model) {
//        filterBannerModel = model;
//        setFragment(knowledgeList);

    }


    private List<CustomerCareModel> setUpCustomerCareAdapter(String searchWord) {

        List<CustomerCareModel> tempList = new ArrayList<>();

        for (CustomerCareModel model : caustomerCareList) {
            try {
                bannerList = mDAOBanner
                        .queryBuilder()
                        .where()
                        .eq(BannerModel.FIELD_ID, model.getBrandfkid())
                        .query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (searchWord.equalsIgnoreCase(bannerList.get(bannerList.size() - 1).getBrandName())) {
                tempList.add(model);

            }
        }


        Log.d(TAG, "CustomerCareList:" + new Gson().toJson(tempList));


        return tempList;
    }
}

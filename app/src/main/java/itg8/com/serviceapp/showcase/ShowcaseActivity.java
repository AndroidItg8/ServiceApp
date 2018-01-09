package itg8.com.serviceapp.showcase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.HomeActivity;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.database.BaseDatabaseHelper;
import itg8.com.serviceapp.enquiry.EnquiryFormActivity;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.showcase.mvp.ShowCaseMVP;
import itg8.com.serviceapp.showcase.mvp.ShowcasePresenterImp;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import itg8.com.serviceapp.widget.AutoScrollViewPager;

public class ShowcaseActivity extends AppCompatActivity implements ShowcaseAdapter.ItemClickedListener, ShowCaseMVP.ShowcaseView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCatProductList)
    RecyclerView rvCatProductList;
    @BindView(R.id.viewPager)
    AutoScrollViewPager viewPager;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ShowCaseMVP.ShowcasePresenter presenter;
    private Snackbar snackbar;
    private Dao<BannerModel, Integer> mDAOBanner = null;


    private List<CategoryModel> categoryList;
    private List<ProductModel> productOneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Showcase");
        callPresenter();

    }

    private void callPresenter() {
        presenter = new ShowcasePresenterImp(this);
     //   presenter.onGetBannerList(getString(R.string.url_banner));
        presenter.onGetCategoryList(getString(R.string.url_category));
        //presenter.onGetProblemList(getString(R.string.url_problem));
        try {
            mDAOBanner = BaseDatabaseHelper.getBaseInstance().getHelper(ShowcaseActivity.this).getmDAOBanner();
            List<BannerModel> list = mDAOBanner.queryForAll();
            setBAnnerAdater(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void itemProductClicked(int position, ProductModel model) {
        //Intent intent = new Intent(getApplicationContext(), ShowCaseDetailsActivity.class);

        Intent intent = new Intent(getApplicationContext(), EnquiryFormActivity.class);
        intent.putExtra(CommonMethod.SHOWCASE, model);
        startActivity(intent);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowCaseSuccess(List<ProductModel> list) {
//         this.showCaseList = list;
        this.productOneList = list;
        // productOneList =  getListFiteredList();

    }


    @Override
    public void onFail(String message) {
        showToast(message);
    }

    @Override
    public void onError(Object t) {
        showToast(t.toString());

    }

    private void showToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void onCategorySuccess(final List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
//        presenter.onDownloadShowCase(getString(R.string.url_product_list));


    }

    private void setCategoryName(CategoryModel model, TextView... textview) {
        for (TextView t : textview
                ) {
            t.setText(model.getCategoryname());

        }
    }



    @Override
    public void onBannerlist(List<BannerModel> list) {
       setBAnnerAdater(list);

    }

    private void setBAnnerAdater(List<BannerModel> list) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, list);
        viewPager.setAdapter(adapter);
        viewPager.startAutoScroll(6000);
    }

    @Override
    public void onNoInternetConnect(boolean b) {
        showSnackbar(b);

    }

    @Override
    public void onInternetConnect(boolean b) {
        showSnackbar(b);
    }

    @Override
    public String getProductListUrl() {
        return getString(R.string.url_product_list);
    }

    @Override
    public void onProductWithCatLoaded(HashMap<Integer, List<ProductModel>> data) {
        rvCatProductList.setLayoutManager(new LinearLayoutManager(this));
        ShowcaseAdapter adapter=new ShowcaseAdapter(this,categoryList,this);
        rvCatProductList.setAdapter(adapter);
    }


    //    private List<? extends ProductModel> getListFiteredList() {
//      //  List<TicketModel> filterList = new FilterUtility.FilterBuilder().createBuilder(list).setFilter(type).build().getFilteredList();
////        List<ProductModel> filterListProductOne = new FilterUtility.FilterBuilder().createShowCaseBuilder(showCaseList).setShowFilter().build().getFilteredShowCaseList();
////        return filterListProductOne;
//    }

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
                onSnackbarOkClicked(view);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view) {
        presenter.onGetCategoryList(getString(R.string.url_category));
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         presenter.onDestroy();
    }
}

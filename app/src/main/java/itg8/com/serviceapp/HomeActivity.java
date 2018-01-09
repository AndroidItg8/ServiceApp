package itg8.com.serviceapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.account.AccountActivity;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.ConnectivityReceiver;
import itg8.com.serviceapp.common_method.NoConnectivityException;
import itg8.com.serviceapp.common_method.Prefs;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.database.BaseDatabaseHelper;
import itg8.com.serviceapp.device.DeviceActivity;
import itg8.com.serviceapp.feedback.FeebackTicketActivity;
import itg8.com.serviceapp.feedback.FeedbackActivity;
import itg8.com.serviceapp.login.LoginActivity;
import itg8.com.serviceapp.profile.ProfileActivity;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.registration.RegistrationActivity;
import itg8.com.serviceapp.showcase.ShowcaseActivity;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.ticket.TicketActivity;
import itg8.com.serviceapp.warranty.WarrantyActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_PROFILE = 234;
    private static final String TAG = HomeActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.img_sleep)
    ImageView imgSleep;
    @BindView(R.id.lbl_account)
    TextView lblAccount;
    @BindView(R.id.card_purchase)
    CardView cardPurchase;
    @BindView(R.id.img_posture)
    ImageView imgPosture;
    @BindView(R.id.lbl_warranty)
    TextView lblWarranty;
    @BindView(R.id.card_service)
    CardView cardService;
    @BindView(R.id.ll_first)
    LinearLayout llFirst;
    @BindView(R.id.img_activity)
    ImageView imgActivity;
    @BindView(R.id.lbl_ticket)
    TextView lblTicket;
    @BindView(R.id.card_ticket)
    CardView cardTicket;
    @BindView(R.id.lbl_app)
    TextView lblApp;
    @BindView(R.id.img_feedback)
    ImageView imgFeedback;
    @BindView(R.id.card_feedback)
    CardView cardFeedback;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_itech)
    ImageView imgItech;
    @BindView(R.id.img_show_case)
    ImageView imgShowCase;
    @BindView(R.id.lbl_show_case)
    TextView lblShowCase;
    @BindView(R.id.card_show_case)
    CardView cardShowCase;
    @BindView(R.id.ll_mid)
    LinearLayout llMid;
    @BindView(R.id.lbl_login)
    TextView lblLogin;
    @BindView(R.id.img_device)
    ImageView imgDevice;
    @BindView(R.id.lbl_Device)
    TextView lblDevice;
    @BindView(R.id.card_device)
    CardView cardDevice;
    @BindView(R.id.ll_last)
    LinearLayout llLast;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Snackbar snackbar;
    private ProfileModel profileModel;
    private Call<List<BannerModel>> callBanner;
    private boolean isDestroyed=false;
    private List<BannerModel> listBranner;
    private Dao<BannerModel, Integer> mDAOBanner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTitle.setText(getResources().getString(R.string.title_activity_home));
        GetBannerList(getString(R.string.url_banner));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Log.d("HomeActivity:","Firebase: "+FirebaseInstanceId.getInstance().getToken());
        if (!Prefs.getBoolean(CommonMethod.IS_LOGIN, false)) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        init();


    }

    private void GetBannerList(String url) {
        RetroController api = AppApplication.getInstance().getRetroController();
     //   showProgress();
        callBanner = api.getBanner(url);
        callBanner.enqueue(new Callback<List<BannerModel>>() {

            @Override

            public void onResponse(Call<List<BannerModel>> call, Response<List<BannerModel>> response) {
                //hideProgress();
                if(response.isSuccessful())
                {
                    if(response.body()!=null)
                    {
                       listBranner=response.body();
                         saveBrandToDatabase(response.body());

                    }else
                    {
                      showToast("Download Failed");
                    }
                }
                else
                {
                    showToast("Download Failed");
                }


            }

            @Override
            public void onFailure(Call<List<BannerModel>> call, Throwable t) {
               // hideProgress();
                if(t instanceof NoConnectivityException)
                {
                    showSnackbar(true);
                }else {
                    showToast(t.getMessage());
                }

            }
        });
    }

    private void saveBrandToDatabase(List<BannerModel> body) {
        try {

            mDAOBanner = BaseDatabaseHelper.getBaseInstance().getHelper(HomeActivity.this).getmDAOBanner();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(mDAOBanner!= null)
        {
            BaseDatabaseHelper.getBaseInstance().clearBannerTable();


        for (BannerModel model:body)
        {
            try {
               int id =  mDAOBanner.create(model);

                Log.d(TAG,"ID:"+id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }



    }


    private void showProgress() {
        //if (!isDestroyed)
           // progressView.setVisibility(View.VISIBLE);

    }

    private void hideProgress() {
        //if (!isDestroyed)
           // progressView.setVisibility(View.GONE);
    }



    @Override
    protected void onDestroy() {
        isDestroyed = true;
        if (callBanner != null) {
            if (!callBanner.isCanceled())
                callBanner.cancel();
        }


        super.onDestroy();
    }

    private void setFontrobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(this);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }
    private void init() {
        cardPurchase.setOnClickListener(this);
        cardService.setOnClickListener(this);
        cardTicket.setOnClickListener(this);
        cardFeedback.setOnClickListener(this);
        cardShowCase.setOnClickListener(this);
        cardDevice.setOnClickListener(this);
        setFontrobotoMedium(lblDevice, lblTicket,lblWarranty, lblShowCase, lblAccount, lblLogin, txtTitle);


    }

    private void showToast(String message) {
        if (!isDestroyed)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        String from;
        switch (v.getId()) {
            case R.id.card_purchase:
                //My Account
                from = CommonMethod.FROM_PURCHASE;
                intent = new Intent(getApplicationContext(), AccountActivity.class);
               // intent.putExtra(CommonMethod.FROM_PURCHASE, from);

                startServiceActivity(intent);
                break;
            case R.id.card_service:
                //Warranty
                from = CommonMethod.FROM_SERVICE;
                intent = new Intent(getApplicationContext(), WarrantyActivity.class);
              //  intent.putExtra(CommonMethod.FROM_SERVICE, from);
                startServiceActivity(intent);
                break;
            case R.id.card_ticket:
                startActivity(new Intent(getApplicationContext(), TicketActivity.class));
                break;
            case R.id.card_feedback:
                startActivity(new Intent(getApplicationContext(), FeebackTicketActivity.class));
                break;
            case R.id.card_show_case:
                startActivity(new Intent(getApplicationContext(),ShowcaseActivity.class));
                break;
            case R.id.card_device:
                startActivity(new Intent(getApplicationContext(), DeviceActivity.class));
        }


    }

    private void startServiceActivity(Intent intent) {
        startActivity(intent);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_PROFILE && resultCode == RESULT_OK)
        {
            Prefs.putBoolean(CommonMethod.IS_LOGIN, false);
            Prefs.remove(CommonMethod.HEADER);
            startActivity(new Intent(this, LoginActivity.class));
            this.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.action_profile)
        {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivityForResult(intent, RC_PROFILE);

           // startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }




    private void showSnackbar( boolean isConnected) {

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
        GetBannerList(getString(R.string.url_banner));

    }

    public void hideSnackbar(){
        if(snackbar!=null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }



}

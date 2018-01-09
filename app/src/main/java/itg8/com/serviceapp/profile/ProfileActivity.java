package itg8.com.serviceapp.profile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.changepassword.ChangePasswordActivity;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Prefs;
import itg8.com.serviceapp.login.LoginActivity;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.profile.mvp.ProfileMVp;
import itg8.com.serviceapp.profile.mvp.ProfilePresenterImp;

public class ProfileActivity extends AppCompatActivity implements ProfileMVp.ProfileView, View.OnClickListener {

    private static final int RC_PROFILE = 234;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_profile)
    TextView lblProfile;
    @BindView(R.id.lbl_email)
    TextView lblEmail;
    @BindView(R.id.text_email)
    TextView textEmail;
    @BindView(R.id.lbl_mobile)
    TextView lblMobile;
    @BindView(R.id.text_mobile)
    TextView textMobile;
    @BindView(R.id.lbl_address)
    TextView lblAddress;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    private ProfileMVp.ProfilePresenter presenter;
    private Snackbar snackbar;
    private ProfileModel profileModel = null;
    private CommonMethod.GetProfileListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new ProfilePresenterImp(this);
        btnOk.setOnClickListener(this);
         // presenter.onGetProfileList(getString(R.string.url_profile));
        if (!Prefs.getBoolean(CommonMethod.IS_LOGIN, false)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            AppApplication.getInstance().getProfileModel(new CommonMethod.GetProfileListener() {
                @Override
                public void onSuccess(ProfileModel profileModel) {
                    String address = (checkEmpty(profileModel.getAddressLine1()))+
                            (checkEmpty( profileModel.getAddressLine2()))+
                            ((checkEmpty( profileModel.getAddressLine3())));

                    if(TextUtils.isEmpty(address))
                        textAddress.setText("NOT AVAILABLE");
                    else
                        textAddress.setText((checkEmpty(profileModel.getAddressLine1()))+
                            "\n"+(checkEmpty( profileModel.getAddressLine2()))+
                            "\n"+((checkEmpty( profileModel.getAddressLine3())))
                            );

                    textEmail.setText( (checkNull(profileModel.getEmail())));
                    textMobile.setText((checkNull(profileModel.getMobileno())));
                    lblProfile.setText((checkNull(profileModel.getCustomerName())));
                }

                @Override
                public void onFailure(Throwable t) {
                    showToast(t.getMessage());

                }

                @Override
                public void onFailure(String s) {
                    showToast(s);

                }
            });


            //  presenter.onGetProfile(getString(R.string.url_profile));
            //Here are Database Statement for get Particular profile .
            // i thing we have to save userId also in Sharepresncess.

        }


        setFontRobotoMedium(textAddress, textEmail, textMobile, lblProfile);
        setFontrobotoregular(lblAddress, lblEmail, lblMobile);
    }

    private String checkNull(String s) {
        if (s != null)
            return s;
        return "NOT AVAILABLE";
    }
    private String checkEmpty(String s) {
        if (s != null && !TextUtils.isEmpty(s))
            return s;
        return "";
    }

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(getApplicationContext());
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }

    private void setFontRobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(getApplicationContext());
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }

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
                onSnackbarOkClicked(view);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view) {
        presenter.onGetProfileList(getString(R.string.url_profile));
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void onSuccess(List<ProfileModel> list) {


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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        llProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        llProgress.setVisibility(View.GONE);


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
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_password) {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            startActivity(intent);

            // startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

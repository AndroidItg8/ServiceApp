package itg8.com.serviceapp.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.HomeActivity;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.changepassword.ChangePasswordActivity;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Prefs;
import itg8.com.serviceapp.forgetpassword.ForgetActivity;
import itg8.com.serviceapp.mvp.LoginMvp;
import itg8.com.serviceapp.mvp.LoginPresenterImp;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CommonMethod.FragmentAttachListner, LoginMvp.LoginView {


    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_REGIS = 987;
    private static final int RC_FIRST_LOGIN = 234;
    private static final int RC_FORGET = 567;
    private static final int RC_CHANGE_FORGET = 789;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.txt_optLogin)
    TextView txtOptLogin;

    @BindView(R.id.lbl_foget)
    TextView lblFoget;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.edt_userName)
    EditText edtUserName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.btn_signUp)
    Button btnSignUp;
    private LoginMvp.LoginPresenter presenter;
    private Snackbar snackbar;
    private List<itg8.com.serviceapp.profile.model.ProfileModel> ProfileModel;
    private String fromSignUp= "fromSignUp";
    private String fromLogin="fromLogin";
    private String fromForget="fromForget";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setHomeButtonEnabled(true);
        presenter = new LoginPresenterImp(this);
        toolbar.setTitle("Profile");

        init();


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

    private void init() {

        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        btnSave.setOnClickListener(this);
        lblFoget.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);


        setFontRobotoMedium(lblFoget, edtPassword, edtUserName, btnSave);


    }


    @Override
    public void onClick(View v) {
        Intent intent= null;
        switch (v.getId()) {
            case R.id.btn_save:
                presenter.onLoginClicked(v);
                break;
            case R.id.lbl_foget:
              intent = new Intent(this, ForgetActivity.class);
               startActivityForResult(intent, RC_FORGET);
                break;
            case R.id.btn_signUp:
                 intent = new Intent(this, RegistrationActivity.class);
                startActivityForResult(intent,RC_REGIS);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_REGIS && resultCode ==RESULT_OK)
        {
            showTextSnackbar("We will  provide you , your authentication details through an email", fromSignUp);
        }

        if(requestCode == RC_FORGET && resultCode==RESULT_OK)
         {
             showTextSnackbar("We will  provide you, your  authentication details through an email", fromForget);

         }

         if(resultCode == RC_CHANGE_FORGET  &&  resultCode==RESULT_OK)
         {


         }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onAttachListner(Context mContext, String FragmentName) {

        // this.mContext=  mContext;
//        fragmentName = FragmentName;
//        Log.d(TAG, "OnATTACHED" + fragmentName);

    }


    @Override
    public String getUsername() {
        return edtUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString().trim();
    }

    @Override
    public void onSuccess() {
        Prefs.putBoolean(CommonMethod.IS_LOGIN, true);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFail(String message) {
        showTextSnackbar(message, fromLogin);

    }


    @Override
    public void onError(Object t) {
        showTextSnackbar(t.toString(), fromLogin);

    }

    @Override
    public void onUsernameInvalid(String err) {
        edtUserName.setError(err);


    }

    @Override
    public void onPasswordInvalid(String err) {
        edtPassword.setError(err);

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
    public void onNoInternetConnect(boolean b) {
        showSnackbar(b);

    }

    @Override
    public void onInternetConnect(boolean b) {
        showSnackbar(b);

    }

    @Override
    public void onGetProfileModel(List<ProfileModel> body) {
        this.ProfileModel = body;


    }

    @Override
    public void onFirstTimeLogin(String success) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        intent.putExtra(CommonMethod.FROM_LOGIN, success);
        startActivity(intent);
        finish();


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
        presenter.onLoginClicked(view);
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    private void showTextSnackbar(String s, String from) {
        snackbar = Snackbar
                .make(findViewById(R.id.fab), s, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setText(s);

        if(from.equalsIgnoreCase(fromSignUp) || from.equalsIgnoreCase(fromForget))
                textView.setMaxLines(4);
        else
                    textView.setMaxLines(2);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();

            }
        });
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

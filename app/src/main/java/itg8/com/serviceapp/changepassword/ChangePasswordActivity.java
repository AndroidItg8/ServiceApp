package itg8.com.serviceapp.changepassword;

import android.content.Intent;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.HomeActivity;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.changepassword.mvp.ChangePasswordMVP;
import itg8.com.serviceapp.changepassword.mvp.ChangePswdPresenterImp;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Prefs;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordMVP.ChangePswdView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input_oldPswd)
    EditText inputOldPswd;
    @BindView(R.id.input_layout_old_pswd)
    TextInputLayout inputLayoutOldPswd;
    @BindView(R.id.input_newPswd)
    EditText inputNewPswd;
    @BindView(R.id.input_layout_new_pswd)
    TextInputLayout inputLayoutNewPswd;
    @BindView(R.id.input_cirmPswd)
    EditText inputCirmPswd;
    @BindView(R.id.input_layout_cirm_pswd)
    TextInputLayout inputLayoutCirmPswd;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    private ChangePasswordMVP.ChangePswdPresenter presenter;
    private Snackbar snackbar;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSubmit.setOnClickListener(this);
        presenter = new ChangePswdPresenterImp(this);
         if(getIntent().hasExtra(CommonMethod.FROM_LOGIN))
         {
          from = getIntent().getStringExtra(CommonMethod.FROM_LOGIN);
             inputOldPswd.setVisibility(View.GONE);
             inputLayoutOldPswd.setVisibility(View.GONE);
             getSupportActionBar().setDisplayHomeAsUpEnabled(false);

         }
         else
         {
             inputOldPswd.setVisibility(View.VISIBLE);
              inputLayoutOldPswd.setVisibility(View.VISIBLE);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getOldPassword() {
        return inputOldPswd.getText().toString().trim();
    }

    @Override
    public String getNewPassword() {
        return inputNewPswd.getText().toString().trim();

    }

    @Override
    public String getConfirmPassword() {
        return inputCirmPswd.getText().toString().trim();

    }

    @Override
    public void onSuccess(String status) {
        Prefs.putBoolean(CommonMethod.IS_LOGIN, true);
      startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        finish();


    }

    @Override
    public void onFail(String message) {
        showTextSnackbar(message);

    }

    @Override
    public void onError(Object t) {
        showTextSnackbar(t.toString());

    }

    @Override
    public void onOldPswdInvalid(String err) {
        inputOldPswd.setError(err);

    }

    @Override
    public void onNewPswdInvalid(String err) {
        inputNewPswd.setError(err);


    }

    @Override
    public void onConfirmswdInvalid(String err) {
        inputCirmPswd.setError(err);


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
                onSnackbarOkClicked(view);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View v) {
        if (from != null)
            presenter.onSubmitButtonClicked(v, from,getString(R.string.url_change_pswd_after_login));
        else
            presenter.onSubmitButtonClicked(v, from,getString(R.string.url_change_password));
    }


    public void hideSnackbar(){
        if(snackbar!=null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }
    private void showTextSnackbar(String message) {
        snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setMaxLines(2);
        snackbar.show();
         onBackPressed();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btn_submit)
        {
            if(from != null)
                presenter.onSubmitButtonClicked(v, from, getString(R.string.url_change_pswd_after_login));
            else
                presenter. onSubmitButtonClicked(v, from, getString(R.string.url_change_password));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         presenter.onDestroy();
    }
}

package itg8.com.serviceapp.registration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
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
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.RetroController;
import itg8.com.serviceapp.feedback.model.FeedbackModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_registration)
    TextView lblRegistration;
    @BindView(R.id.input_company)
    EditText inputCompany;
    @BindView(R.id.input_layout_product)
    TextInputLayout inputLayoutProduct;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_layout_name)
    TextInputLayout inputLayoutName;
    @BindView(R.id.input_number)
    EditText inputNumber;
    @BindView(R.id.input_layout_number)
    TextInputLayout inputLayoutNumber;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    private Call<FeedbackModel> call;
    private boolean isDestroyed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSubmit.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onClick(View v) {
        senDataToserver();

    }

    private void senDataToserver() {
        if (validate()) {


            postDataIntoServer(inputCompany.getText().toString().trim(),
                    inputEmail.getText().toString().trim(),
                    inputNumber.getText().toString().trim(),
                    inputName.getText().toString().trim()
            );
        }
//

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null && call.isCanceled()) {
            call.cancel();
        }
    }

    private boolean validate() {
        String email = inputEmail.getText().toString();
        String name = inputName.getText().toString();
        String MobileNumber = inputNumber.getText().toString();
        String companyName = inputCompany.getText().toString();
        boolean validate = true;
        if (TextUtils.isEmpty(name)) {
            inputName.setError(getString(R.string.empty));
            validate = false;
        }
        if (TextUtils.isEmpty(companyName)) {
            inputCompany.setError(getString(R.string.empty));
            validate = false;
        }

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(getString(R.string.empty));
            validate = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            validate = false;
            inputEmail.setError(getString(R.string.invalid_email));

        }
        if (MobileNumber.length() != 10) {
            validate = false;
            inputNumber.setError(getString(R.string.invalid_number));
        } else if (TextUtils.isEmpty(MobileNumber)) {
            inputNumber.setError(getString(R.string.empty));
            validate = false;
        }


        return validate;
    }


    private void postDataIntoServer(String compnayName, String email, String mobile, String person) {
       String url =  getString(R.string.url_registration);
        showProgress();
        RetroController api = AppApplication.getInstance().getRetroController();
        call = api.sendRegistrationInfoToserver(url,compnayName, email, mobile, person);
        call.enqueue(new Callback<FeedbackModel>() {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {

                hideProgress();

                if (response.isSuccessful()) {
                    if (response.body().getFlag()) {
                        setResult(RESULT_OK);
                        finish();
                    }
                } else {
                    showToast("Download Failed");
                }


            }

            @Override
            public void onFailure(Call<FeedbackModel> call, Throwable t) {
                t.printStackTrace();
                showToast(t.getMessage());
            }
        });

    }

    private void showProgress() {
        if (!isDestroyed)
        llProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideProgress() {
        if (!isDestroyed)
            llProgress.setVisibility(View.GONE);
    }

    private void showToast(String s) {
        if (!isDestroyed)
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}

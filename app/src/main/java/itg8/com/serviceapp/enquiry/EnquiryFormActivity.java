package itg8.com.serviceapp.enquiry;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Prefs;
import itg8.com.serviceapp.enquiry.mvp.EnquiryMVP;
import itg8.com.serviceapp.enquiry.mvp.EnquiryPresenterImp;
import itg8.com.serviceapp.login.LoginActivity;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

public class EnquiryFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, EnquiryMVP.EnquiryView, CommonMethod.ProductItemSendToActivityListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_enquiry)
    TextView lblEnquiry;
    //    @BindView(R.id.spinner_product)
//    Spinner spinnerProduct;

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
    @BindView(R.id.input_number2)
    EditText inputNumber2;
    @BindView(R.id.input_layout_number2)
    TextInputLayout inputLayoutNumber2;
    @BindView(R.id.input_description)
    EditText inputDescription;
    @BindView(R.id.input_layout_description)
    TextInputLayout inputLayoutDescription;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private static final String[] Product = {"Product 1", "Product 2", "Product 3"};
    @BindView(R.id.input_product)
    EditText inputProduct;
    @BindView(R.id.input_layout_product)
    TextInputLayout inputLayoutProduct;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    private EnquiryMVP.EnquiryPresenter presenter;
    private Snackbar snackbar;
    private ProductModel productModel;
    private List<ProductModel> productList;
    private CommonMethod.GetProfileListener listener;
    private ProfileModel profileModel=null;
    private Integer productId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_form);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new EnquiryPresenterImp(this);
        init();


    }

    private void init() {
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
//                android.R.layout.simple_spinner_item, Product);
//
//        adapter.setDropDownViewResource(R.layout.spinner_row);
//        spinnerProduct.setAdapter(adapter);
        //  spinnerProduct.setOnItemSelectedListener(this);

      //  inputProduct.setOnClickListener(this);


         if(getIntent().hasExtra(CommonMethod.SHOWCASE))
         {
             productModel=getIntent().getParcelableExtra(CommonMethod.SHOWCASE);
             inputProduct.setText(productModel.getItemName());
             productId = productModel.getPkid();

         }
          if(!Prefs.getBoolean(CommonMethod.IS_LOGIN,false))
          {
              startActivity(new Intent(this, LoginActivity.class));
              finish();
          }else

              {
                  AppApplication.getInstance().getProfileModel(new CommonMethod.GetProfileListener() {
                  @Override
                  public void onSuccess(ProfileModel model) {
                       if(model!= null) {

                           inputEmail.setText(model.getEmail());
                           inputNumber.setText(model.getMobileno());
                           inputName.setText(model.getCustomerName());
                       }
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
        btnOk.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                presenter.onSubmitButtonClicked(v,productId);

                break;
            case R.id.input_product:
//                FragmentManager fm = getSupportFragmentManager();
//                ProductFragment productFragment = new ProductFragment();
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList(CommonMethod.PRODUCTLIST, (ArrayList<? extends Parcelable>) productList);
//                productFragment.setArguments(bundle);
//                productFragment.show(fm, "DialogCat");
                break;

        }


    }

    @Override
    public String getProductName() {
        return inputProduct.getText().toString().trim();
    }

    @Override
    public String getPersonName() {
        return inputName.getText().toString().trim();
    }

    @Override
    public String getEmailId() {
        return inputEmail.getText().toString().trim();
    }

    @Override
    public String getMobileNumber() {
        return inputNumber.getText().toString().trim();
    }

    @Override
    public String getAnotherMobileNumber() {
        return inputNumber2.getText().toString().trim();
    }

    @Override
    public String getQuery() {
        return inputDescription.getText().toString().trim();
    }

    @Override
    public void onSuccess() {
        showToast("Submit Successfully ");
        onBackPressed();
 }

    @Override
    public void onFail(String message) {
        showToast(message);


    }

    @Override
    public void onError(Object t) {
        showToast(t.toString());


    }

    @Override
    public void onProductNameInvalid(String err) {
        inputProduct.setError(err);

    }

    @Override
    public void onPersonNameInvalid(String err) {
        inputName.setError(err);


    }

    @Override
    public void onMobileInvalid(String err) {
        inputNumber.setError(err);


    }

    @Override
    public void onAnotherMobileInvalid(String err) {
        inputNumber2.setError(err);


    }

    @Override
    public void onEmailInvalid(String err) {
        inputEmail.setError(err);


    }

    @Override
    public void onQuerynvalid(String err) {
        inputDescription.setError(err);


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

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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

    private void onSnackbarOkClicked(View view) {
        presenter.onSubmitButtonClicked(view, productId);
    }

    public void hideSnackbar(){
        if(snackbar!=null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    @Override
    public void onProductList(List<ProductModel> list) {
        productList = list;
//        inputProduct.setOnClickListener(this);


    }

    @Override
    public void getProfile(List<ProfileModel> body) {
//         inputEmail.setText(body.get(0).getEmailAddress());
//         inputNumber.setText(body.get(0).getMobileNumber());
//         inputName.setText(body.get(0).getFullname());

    }

    @Override
    public void onSendProduct(ProductModel model) {
        productModel = model;
        inputProduct.setText(model.getItemName());
    }

}

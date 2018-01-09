package itg8.com.serviceapp.ticket;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Prefs;
import itg8.com.serviceapp.enquiry.ProductFragment;
import itg8.com.serviceapp.login.LoginActivity;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.ticket.adapter.ProblemAdapter;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import itg8.com.serviceapp.ticket.mvp.RaisedTicketMVP;
import itg8.com.serviceapp.ticket.mvp.RaisedTicketPresenterImp;

public class RaisedTicketActivity extends AppCompatActivity implements RaisedTicketMVP.RaisedTicketView, AdapterView.OnItemSelectedListener, View.OnClickListener , CommonMethod.ProductItemSendToActivityListener {


    private static final String[] Problem = {"Problem 1", "Problem 2", "Other"};
    @BindView(R.id.input_person_name)
    EditText inputPersonName;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input_product)
    EditText inputProduct;
    @BindView(R.id.input_layout_product)
    TextInputLayout inputLayoutProduct;
    @BindView(R.id.input_layout_product_name)
    TextInputLayout inputLayoutProductName;
    @BindView(R.id.input_invoice_name)
    EditText inputInvoiceName;
    @BindView(R.id.input_layout_invoice_name)
    TextInputLayout inputLayoutInvoiceName;
    @BindView(R.id.input_date)
    EditText inputDate;
    @BindView(R.id.input_layout_date)
    TextInputLayout inputLayoutDate;
    @BindView(R.id.input_deal)
    EditText inputDeal;
    @BindView(R.id.input_layout_deal)
    TextInputLayout inputLayoutDeal;
    @BindView(R.id.spinner_problem)
    Spinner spinnerProblem;
    @BindView(R.id.input_other)
    EditText inputOther;
    @BindView(R.id.input_layout_other)
    TextInputLayout inputLayoutOther;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    private Fragment fragment;
    private RaisedTicketMVP.RaisedTicketPresenter presenter;
    private List<ProductModel> productList;
    private List<ProblemModel> problemList;
    private Snackbar snackbar;
    private ProductModel productModel;
    private int problemId;
    private ProblemAdapter adapterProblem;
    private CommonMethod.GetProfileListener listener;
    private ProfileModel profileModel= null;
    private int productId= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raised_ticket);
        ButterKnife.bind(this);
         presenter = new RaisedTicketPresenterImp(this);
        presenter.onGetProductProblemList(getString(R.string.url_problem));
        presenter.onGetProductList(getString(R.string.url_product_list));
      checkUserProfile();

        init();

    }

    private void checkUserProfile() {
        if(!Prefs.getBoolean(CommonMethod.IS_LOGIN,false))
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else {
            {
                AppApplication.getInstance().getProfileModel(new CommonMethod.GetProfileListener() {
                    @Override
                    public void onSuccess(ProfileModel model) {
                        if(model!= null)
                            inputPersonName.setText(model.getCustomerName());

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


            }
        }

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        fragment = RaisedTicketFragment.newInstance("", "");
//        UtilityHelper.callFragment(fragment, RaisedTicketActivity.this);


        inputDate.setInputType(InputType.TYPE_NULL);
        inputDate.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        setFontrobotoregular(inputDate, inputDeal, inputInvoiceName, inputOther, inputPersonName);

    }

    private void setProblemAdapter(final List<ProblemModel> problemList) {
//        ArrayAdapter<ProblemModel> adapterProblem = new ArrayAdapter<ProblemModel>(getApplicationContext(),
//                android.R.layout.simple_spinner_item, problemList);
////        ArrayAdapter<String> adapterProblem = new ArrayAdapter<String>(getApplicationContext(),
////                android.R.layout.simple_spinner_item, productList.get().getProblem());
//
//        adapterProblem.setDropDownViewResource(R.layout.spinner_row);
//        spinnerProblem.setAdapter(adapterProblem);
//        spinnerProblem.setOnItemSelectedListener(this);
        adapterProblem=   new ProblemAdapter(this, R.layout.item_spinner_problem, problemList);
        adapterProblem.setDropDownViewResource(R.layout.item_spinner_problem);
        spinnerProblem.setAdapter(adapterProblem);
        spinnerProblem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("My POSITION", "" + position);

                if(problemList.get(position).getPkid()<0)
                {
                    inputOther.setVisibility(View.VISIBLE);
                    inputLayoutOther.setVisibility(View.VISIBLE);
                }else
                {
                    hideOtherInput();
                }
                ProblemModel problemModel = (ProblemModel) view.getTag();
                problemId= problemModel.getPkid();
               // listener.sendCategoryIdToServer(categoryList.get(position).getCategorymaster().getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRaisedTicketSubmitSuccess(String message) {
        showToast(message);
       setResult(RESULT_OK);
        finish();
    }


    @Override
    public String getProductName()
    {
        return inputProduct.getText().toString().trim();
    }

    @Override
    public String getPersonName() {
        return inputPersonName.getText().toString().trim();
    }

    @Override
    public String getInvoiceNumber() {
        return inputInvoiceName.getText().toString().trim();
    }

    @Override
    public String getInvoiceDate() {
        return inputDate.getText().toString().trim();
    }

    @Override
    public String getProductProblem() {
        return inputProduct.getText().toString().trim();
    }

    @Override
    public String getProductDeal() {
        return inputDeal.getText().toString().trim();
    }

    @Override
    public String getPreblemDescription() {
        return inputOther.getText().toString().trim();
    }

    @Override
    public void onProductNameInvalid(String err) {
        showError(err, inputProduct);


    }


    private void showError(String err, EditText view) {
        view.setError(err);
    }

    @Override
    public void onPersonNameInvalid(String err) {
        showError(err, inputPersonName);


    }

    @Override
    public void onInvoiceNumberInvalid(String err) {
        showError(err, inputInvoiceName);

    }

    @Override
    public void onInvoiceDateInvalid(String err) {
        showError(err, inputDate);
    }

    @Override
    public void onProductProblemInvalid(String err) {
        //showError(err, input);
    }

    @Override
    public void onPreblemDescriptionInvalid(String err) {
        showError(err, inputOther);


    }

    @Override
    public void onProductDealInvalid(String err) {
        showError(err, inputDeal);


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
    public void showProgress() {
        llProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        llProgress.setVisibility(View.GONE);

    }

    @Override
    public void onProductList(List<ProductModel> list) {
        productList = list;
        inputProduct.setOnClickListener(this);


    }

    @Override
    public void onProblemList(List<ProblemModel> list) {
       this.problemList= list;
         ProblemModel tmps =new ProblemModel();
        tmps.setProblem("Other");
        tmps.setPkid(0);
        this.problemList.add(tmps);
        setProblemAdapter(problemList);



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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                hideOtherInput();
                break;
            case 1:
                hideOtherInput();
                break;
            case 2:
                inputOther.setVisibility(View.VISIBLE);
                inputLayoutOther.setVisibility(View.VISIBLE);
                //hideOtherInput();
                break;
            case 3:
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void hideOtherInput() {
        inputOther.setVisibility(View.GONE);
        inputLayoutOther.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_date:
                openDateTimeDialogue();

                break;
            case R.id.input_product:
                FragmentManager fm = getSupportFragmentManager();
                ProductFragment productFragment = new ProductFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(CommonMethod.PRODUCTLIST, (ArrayList<? extends Parcelable>) productList);
                productFragment.setArguments(bundle);
                productFragment.show(fm, "DialogCat");
                break;
            case R.id.btn_submit:
                 presenter.onSubmitButtonClicked(v, productId, problemId);
                break;

 }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

//    private void openDateTimeDialogue() {
//
//        // Get Current Time
//        int mYear = mcurrentDate.get(Calendar.YEAR);
//        int mMonth = mcurrentDate.get(Calendar.MONTH);
//        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog mDatePicker = new DatePickerDialog(RaisedTicketActivity.this, new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                // TODO Auto-generated method stub
//
//                mcurrentDate.set(Calendar.YEAR, selectedyear);
//                mcurrentDate.set(Calendar.MONTH, selectedmonth);
//                mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
////                String myFormat = "dd/MMM/yyyy"; //In which you need put here
////                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
////
////                String[] entireDate = sdf.format(mcurrentDate.getTime()).split("/");
////                String day = entireDate[0];
////                String month = entireDate[1];
////                String year = entireDate[2];
////                edtDay.setText(day);
////                edtMonth.setText(month);
////                edtYear.setText(year);
//                inputDate.setText( String.valueOf(mcurrentDate.getTime()));
//                Log.d(getClass().getSimpleName(), "DatePicker:" + mcurrentDate.getTime());
//
//
//            }
//        }, mYear, mMonth, mDay);
//        mDatePicker.setTitle("Select date");
//        mDatePicker.show();
//    }

    private void openDateTimeDialogue() {

        // Get Current Time
        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub

                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

                String[] entireDate = sdf.format(mcurrentDate.getTime()).split("/");
                String day = entireDate[0];
                String month = entireDate[1];
                String year = entireDate[2];
                inputDate.setText(day+"-"+month+"-"+year);


                Log.d(getClass().getSimpleName(), "DatePicker:" + mcurrentDate.getTime());


            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
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
                snackbar.dismiss();

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view) {
       // presenter.onSubmitButtonClicked(view, productModel);
    }

    public void hideSnackbar(){
        if(snackbar!=null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }



    @Override
    public void onSendProduct(ProductModel model) {
         productModel =model;
        inputProduct.setText(model.getItemName());
    }

    public void sendModelDataToActivity(ProductModel model) {
        productModel =model;
        productId = model.getPkid();
        inputProduct.setText(model.getItemName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

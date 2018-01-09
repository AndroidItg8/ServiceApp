package itg8.com.serviceapp.feedback;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.feedback.mvp.FeedbackMVP;
import itg8.com.serviceapp.feedback.mvp.FeedbackPresenterImp;
import itg8.com.serviceapp.ticket.model.TicketModel;

public class FeedbackActivity extends AppCompatActivity implements FeedbackMVP.FeedbackView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ratin_dailoue)
    RatingBar ratinDailoue;
    @BindView(R.id.txt_title)
    EditText txtTitle;
    @BindView(R.id.txt_rating_name)
    TextView txtRatingName;
    @BindView(R.id.txt_rating_desc)
    EditText txtRatingDesc;
    @BindView(R.id.btn_rating_submit)
    Button btnRatingSubmit;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    private FeedbackMVP.FeedbackPresenter presenter;
    private Snackbar snackbar;
    private int ticketId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        btnRatingSubmit.setOnClickListener(this);
        toolbar.setTitle("Feedback");

        init();


    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getIntent().hasExtra(CommonMethod.TICKET_CLOSE_FEEDBACK))
        {
            TicketModel ticketModel = getIntent().getParcelableExtra(CommonMethod.TICKET_CLOSE_FEEDBACK);
             ticketId = ticketModel.getPkid();

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setFontrobotoregular(txtRatingDesc, txtRatingName);
        presenter = new FeedbackPresenterImp(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getFeedbackTitle() {
        return txtTitle.getText().toString().trim();
    }

    @Override
    public String getDescription() {
        return  txtRatingDesc.getText().toString().trim();
    }

    @Override
    public int getRating() {
        return (int) ratinDailoue.getRating();
    }

    @Override
    public void onSuccess(String status) {
        showToast(status);
        setResult(RESULT_OK);
        finish();


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
    public void onTitleInvalid(String err) {
        txtTitle.setError(err);


    }

    @Override
    public void onDescriptionInvalid(String err) {
        txtRatingDesc.setError(err);


    }

    @Override
    public void onRatingBarInvalid(String err) {
        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();


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

    public void showToast(String msg)
     {
         //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
         showTextSnackbar(msg);

     }

    @Override
    public void onClick(View v) {
         presenter.onSubmitButtonClicked(v, ticketId);

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
        presenter.onSubmitButtonClicked(view, ticketId);
    }

    public void hideSnackbar(){
        if(snackbar!=null && snackbar.isShown()){
            snackbar.dismiss();
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

    private void showTextSnackbar(String message) {
        snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setMaxLines(2);
        snackbar.show();
    }



}

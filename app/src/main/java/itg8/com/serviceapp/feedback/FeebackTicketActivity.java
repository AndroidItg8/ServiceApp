package itg8.com.serviceapp.feedback;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.feedback.model.FeedbackListModel;
import itg8.com.serviceapp.feedback.mvp.FeedbackTicketMVP;
import itg8.com.serviceapp.feedback.mvp.FeedbackTicketPresenterImp;
import itg8.com.serviceapp.ticket.model.TicketModel;

public class FeebackTicketActivity extends AppCompatActivity implements FeedbackTicketMVP.FeedbackTicketView, TicketFeedbackClosedAdapter.ItemClickedListener {

    private static final String TAG = FeebackTicketActivity.class.getSimpleName();
    private static final int RC_FEEDBACK = 123;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewWarranty)
    RecyclerView recyclerViewWarranty;
    @BindView(R.id.progressView)
    CircularProgressView progressView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.lbl_message)
    TextView lblMessage;
    @BindView(R.id.rl_hide)
    RelativeLayout rlHide;
    private FeedbackTicketMVP.FeedbackTicketPresenter presenter;
    private Snackbar snackbar;
    private List<TicketModel> tempCloseTicket;
    private List<FeedbackListModel> feedbacklist;
    private TicketFeedbackClosedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeback_ticket);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new FeedbackTicketPresenterImp(this);
        presenter.getTicketList(getString(R.string.url_ticket_list));


        toolbar.setTitle("Feedback");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(List<TicketModel> models) {
        presenter.getFeedbackList(getString(R.string.url_feedback_list));
        if (models != null) {
            this.tempCloseTicket = filterList(models);
        }


    }

    private List<TicketModel> filterList(List<TicketModel> models) {
        tempCloseTicket = new ArrayList<>();
        for (TicketModel model : models) {
            if (model.getStatus() == null || model.getStatus().equalsIgnoreCase(CommonMethod.STATUS_CLOSE)) {
                tempCloseTicket.add(model);
            }

        }
        return tempCloseTicket;


    }

    private void setRecyclerView(List<TicketModel> tempCloseTicket) {
        recyclerViewWarranty.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       adapter= new TicketFeedbackClosedAdapter(getApplicationContext(), tempCloseTicket, this);
        recyclerViewWarranty.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showHideView(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);

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
    public void getFeedbackList(List<FeedbackListModel> body) {
        Log.d(TAG, new Gson().toJson(body));
         this.feedbacklist = body;
        filterFeedbackList(body);


    }

    private void filterFeedbackList(List<FeedbackListModel> body) {
        List<TicketModel> templList= new ArrayList<>();
        templList.addAll(tempCloseTicket);
        if (tempCloseTicket != null && body != null) {
            for (FeedbackListModel feedbackListModel : body) {
                for (TicketModel model : tempCloseTicket) {
                    if (feedbackListModel.getTicketFkid() == model.getPkid()) {
                        templList.remove(model);
                    }
                }
            }
            Log.d(TAG + "newFilterList ", new Gson().toJson(templList));

        }
        if (templList.size() > 0) {

             setRecyclerView(templList);
        } else {
            showHideView(rlHide, recyclerViewWarranty);
        }


    }



    public void showToast(String msg) {
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        showTextSnackbar(msg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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
        presenter.getTicketList(getString(R.string.url_ticket_list));
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
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

    @Override
    public void onItemClicked(int position, TicketModel model) {
        Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
        intent.putExtra(CommonMethod.TICKET_CLOSE_FEEDBACK, model);
        startActivityForResult(intent, RC_FEEDBACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_FEEDBACK && resultCode==RESULT_OK)
        {
            filterFeedbackList(feedbacklist);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

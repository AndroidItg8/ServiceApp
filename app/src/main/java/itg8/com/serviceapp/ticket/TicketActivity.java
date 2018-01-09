package itg8.com.serviceapp.ticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.FilterUtility;
import itg8.com.serviceapp.common_method.TicketState;
import itg8.com.serviceapp.common_method.UtilityHelper;
import itg8.com.serviceapp.ticket.model.TicketModel;
import itg8.com.serviceapp.ticket.mvp.TicketMVP;
import itg8.com.serviceapp.ticket.mvp.TicketPresenterImp;

public class TicketActivity extends AppCompatActivity implements View.OnClickListener, TicketMVP.TicketView {

    private static final String ASSIGN_TICKET = "ASSIGN TICKET";
    private static final String OPEN_TICKET = "OPEN TICKET";
    private static final String CLOSED_TICKET = "CLOSED TICKET";
    private static final String THEATER_TICKET = "THEATER TICKET";
    private static final int RC_RAISED_TICKET = 123;
    private Snackbar snackbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.progressView)
    CircularProgressView progressView;
    private RadioGroup rgbTicket;
    private FrameLayout frameContainer;
    Fragment fragment = null;
    private List<TicketModel> list;
    private List<? extends TicketModel> fiterList;
    private List<? extends TicketModel> fiterCloseList;
    private List<? extends TicketModel> fiterAssignList;


    private String from = null;
    private TicketState type;
    private TicketMVP.TicketPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_ticket_assign:
                    type = TicketState.TICKET_ASSIGN;
//                    fiterAssignList =getListFromType(type);
                   // fiterAssignList =getListFilterAssign(type);
                    fragment = AssignTicketFragment.newInstance(fiterAssignList, "");
                    from = ASSIGN_TICKET;

                    break;
                case R.id.nav_ticket_open:
                    type = TicketState.TICKET_OPEN;
                  //  fiterList =getListFromType(type);
                    fragment = OpenTicketFragment.newInstance(fiterList, "");
                    toolbar.setTitle("Open Ticket");
                    from = OPEN_TICKET;
                    break;

                case R.id.nav_ticket_close:
                    type = TicketState.TICKET_CLOSE;
//                    fiterCloseList =getListFromType(type);
                   // fiterCloseList =FilterList(type);
                    fragment = ClosedTicketFragment.newInstance(fiterCloseList, "");
                    from = CLOSED_TICKET;
                    break;

                case R.id.nav_ticket_theater:
                    type = TicketState.TICKET_THEATER;
                   // from = THEATER_TICKET;
                    fragment = TicketTheaterFragment.newInstance(list, "");
                    break;
            }
            if (fragment != null) {
                UtilityHelper.callFragment(fragment, TicketActivity.this);
                toolbar.setTitle(from);

                return true;
            }

            return false;
        }

    };
    private List<TicketModel> listTemp;
    private TicketPendingListListener ticketPendingListListener;
    private TicketCloseListListner ticketCloseListListner;
    private TicketAssigntListner ticketAcceptListListner;

    private List<? extends TicketModel> getListFilterAssign(TicketState type) {
        List<TicketModel> tempAssignTicket= new ArrayList<>();

        for (TicketModel model : list) {

            if (model.getStatus()== null|| model.getStatus().equalsIgnoreCase(CommonMethod.STATUS_ASSIGN)) {
                tempAssignTicket.add(model);
            }
        }
        return tempAssignTicket;
    }

    private List<? extends TicketModel> FilterList(TicketState type) {
         List<TicketModel> tempCloseTicket = new ArrayList<>();
        for (TicketModel model : list) {
            if(model.getStatus()== null|| model.getStatus().equalsIgnoreCase(CommonMethod.STATUS_CLOSE))
            {
                tempCloseTicket.add(model);
                 return tempCloseTicket;
            }


        }

        return tempCloseTicket;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_raised);
        ButterKnife.bind(this);
        presenter =  new TicketPresenterImp(this);
        init();


    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bottomNavigationView();
        fab.setOnClickListener(this);
        if(getIntent().hasExtra(CommonMethod.FROM_NOTIFICATION))
        {
            fiterAssignList = getIntent().getParcelableArrayListExtra(CommonMethod.FROM_NOTIFICATION);

        }


    }



    private void callFragment(List<TicketModel> list) {
        fragment = TicketTheaterFragment.newInstance(list, "");
        UtilityHelper.callFragment(fragment, TicketActivity.this);

    }

    private void bottomNavigationView() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(this, RaisedTicketActivity.class),RC_RAISED_TICKET);
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_RAISED_TICKET && resultCode== RESULT_OK)
        {
//            presenter.onGetTicketList(getString(R.string.url_ticket_list),from);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




    @Override
    public void onShowPaginationLoading(boolean show, int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onShowPaginationLoading(show);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onShowPaginationLoading(show);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onShowPaginationLoading(show);
                break;
        }

    }

    @Override
    public void onPaginationError(boolean show, int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onPaginationError(show);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onPaginationError(show);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onPaginationError(show);
                break;
        }
    }


    @Override
    public void onSuccess(List<TicketModel> list, int status) {
//        this.list = list;
//        callFragment(this.list);
//        type = TicketState.TICKET_OPEN;
//        fiterList =getListFromType(type);
//        hideProgress();

        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onPendingTicketList(list);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onCloseTicketList(list);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onAssigntTicketList(list);
                break;
        }


    }

    @Override
    public void onProgressHide(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onProgressHide();
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onProgressHide();

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onProgressHide();
                break;
        }
    }

    @Override
    public void onProgressShow(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onProgressShow();
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onProgressShow();

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onProgressShow();
                break;
        }

    }

    @Override
    public void onNoInternet(boolean b, int from) {
        showSnackbar(b, CommonMethod.FROM_ERROR, getString(R.string.no_internet));

    }

    @Override
    public void onError(String mesg, int from) {
        showSnackbar(false, CommonMethod.FROM_ERROR, mesg);
    }

    @Override
    public void onFinished(int status, boolean b) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onFinished(b);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onFinished(b);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onFinished(b);
                break;
        }
    }

    @Override
    public void isLoading(int status, boolean b) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.isLoading(b);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.isLoading(b);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.isLoading(b);
                break;
        }
    }

    @Override
    public void emptyList(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onEmptyList();
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onEmptyList();

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onEmptyList();
                break;
        }
    }


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private List<? extends TicketModel> getListFromType(TicketState type) {
        List<TicketModel> filterList;

        if(list!= null) {
             if(type.equals(TicketState.TICKET_OPEN))
             {
           filterList = new FilterUtility.FilterBuilder().createBuilder(list).setFilter().build().getFilteredList();
                 return filterList;
             }if(type.equals(TicketState.TICKET_CLOSE))
             {
                 filterList = new FilterUtility.FilterBuilder().createBuilder(list).setFilterClose().build().getFilteredList();
                 return filterList;
             }if(type.equals(TicketState.TICKET_ASSIGN))
             {
                 filterList = new FilterUtility.FilterBuilder().createBuilder(list).setFilterAssign().build().getFilteredList();
                 return filterList;
             }


         }
         return null ;
    }

    private void showSnackbar(boolean isConnected, String fromError, String string) {

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
    }

    public void hideSnackbar(){
        if(snackbar!=null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroyed();
    }

    public void AssignTicketFragmentAttach(TicketAssigntListner ticketAcceptListListner) {
        this.ticketAcceptListListner = ticketAcceptListListner;
        presenter.onDwonloadTicketList(getString(R.string.url_ticket_list),CommonMethod.TICKET_STATUS_ASSIGN);

    }

    public void OpenTicketFragmentAttach(TicketPendingListListener listListener) {
        this.ticketPendingListListener = listListener;
        presenter.onDwonloadTicketList(getString(R.string.url_ticket_list),CommonMethod.TICKET_STATUS_OPEN);

    }

    public void ClosedTicketFragmentAttach(TicketCloseListListner ticketCloseListListner) {
        this.ticketCloseListListner = ticketCloseListListner;
        presenter.onDwonloadTicketList(getString(R.string.url_ticket_list),CommonMethod.TICKET_STATUS_CLOSE);

    }

    public void onLoadMoreItem(int page, int from) {

    }


    public interface TicketPendingListListener {
        void onPendingTicketList(List<TicketModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
    }

    public interface TicketAssigntListner {
        void onAssigntTicketList(List<TicketModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
    }

    public interface TicketCloseListListner {
        void onCloseTicketList(List<TicketModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();

    }


}

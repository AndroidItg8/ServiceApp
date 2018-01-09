package itg8.com.serviceapp.ticket;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.BaseFragment;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.Logs;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClosedTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClosedTicketFragment extends BaseFragment implements TicketActivity.TicketCloseListListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerViewTicketClosed)
    RecyclerView recyclerViewTicketClosed;
    Unbinder unbinder;
    @BindView(R.id.lbl_message)
    TextView lblMessage;
    @BindView(R.id.rl_hide)
    RelativeLayout rlHide;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    boolean isLoading = false;
    boolean isFinished = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private List<TicketModel> list;
    private Context context;
    private boolean noList = false;
    private int page = 0;
    private boolean isViewVisible = false;
    private int isProgressShow = -1;
    private TicketClosedAdapter adapter;


    public ClosedTicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClosedTicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClosedTicketFragment newInstance(List<? extends TicketModel> param1, String param2) {
        ClosedTicketFragment fragment = new ClosedTicketFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = getArguments().getParcelableArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        adapter = new TicketClosedAdapter(mContext);
    }


    @Override
    public void getItems(int page, int from) {
        this.page = page;
        isLoading = true;
        ((TicketActivity) context).onLoadMoreItem(page, from);
    }

    @Override
    public int getPage() {
        Logs.d("OnRejectedTenderList: Page" + page);
        return page;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_closed_ticket, container, false);
        unbinder = ButterKnife.bind(this, view);
        isViewVisible = true;
        if (isProgressShow > 0) {
            onProgressShow();
        } else {
            onProgressHide();
        }
        init();

        return view;
    }

    private void showHideView(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);
    }

    private void init() {
         LinearLayoutManager linearLayout=new LinearLayoutManager(mContext);
        recyclerViewTicketClosed.setLayoutManager(linearLayout);
        recyclerViewTicketClosed.addOnScrollListener(getRecyclerViewScroll(linearLayout, CommonMethod.TICKET_STATUS_CLOSE));
        recyclerViewTicketClosed.setAdapter(adapter);
        checkNoList();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        ((TicketActivity) this.mContext).ClosedTicketFragmentAttach(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null)
            mContext = null;
    }


    @Override
    public void onCloseTicketList(List<TicketModel> list) {
        adapter.addItems(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isViewVisible = false;
        noList = false;
        ((TicketActivity) this.context).onDestroy();
    }

    @Override
    public void onPaginationError(boolean show) {
        adapter.removeFooter();

    }


    @Override
    public void onEmptyList() {
        noDataAvailableToSetInAdapterInPageOne();
    }

    public void noDataAvailableToSetInAdapterInPageOne() {
        noList = true;
        if (isViewVisible)
            checkNoList();
    }

    @Override
    public void onShowPaginationLoading(boolean show) {
        if (adapter == null) {
            return;
        }
        if (show) {
            adapter.addFooter();

        } else {
            adapter.removeFooter();
        }
    }


    private void checkNoList() {
        if (noList)
            CommonMethod.showHideItem(rlHide, recyclerViewTicketClosed);
        else
            CommonMethod.showHideItem(recyclerViewTicketClosed, rlHide);

    }

    @Override
    public void onFinished(boolean b) {
        isFinished = b;
    }

    @Override
    public void isLoading(boolean b) {
        isLoading = b;
    }

    @Override
    public void onProgressHide() {
        if (isViewVisible)
            progressView.setVisibility(View.GONE);
        else
            isProgressShow = 0;
    }

    @Override
    public void onProgressShow() {
        if (isViewVisible)
            progressView.setVisibility(View.VISIBLE);
        else
            isProgressShow = 1;


    }
}

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketTheaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketTheaterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerViewTicketTheater)
    RecyclerView recyclerViewTicketTheater;

    Unbinder unbinder;
    @BindView(R.id.lbl_message)
    TextView lblMessage;
    @BindView(R.id.rl_hide)
    RelativeLayout rlHide;

    // TODO: Rename and change types of parameters
    private ArrayList<Parcelable> mParam1;
    private String mParam2;
    private Context mContext;
    private List<TicketModel> list;


    public TicketTheaterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1    Parameter 1.
     * @param fiterList
     * @param param2    Parameter 2.  @return A new instance of fragment TicketTheaterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketTheaterFragment newInstance(List<? extends TicketModel> fiterList, String param2) {
        TicketTheaterFragment fragment = new TicketTheaterFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) fiterList);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_theater, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (list.size() == 0) {
            showHideView(rlHide, recyclerViewTicketTheater);
        } else {
            init();
        }
        return view;
    }

    private void showHideView(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        recyclerViewTicketTheater.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewTicketTheater.setAdapter(new TicketTheaterAdapter(mContext, list));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null)
            mContext = null;
    }

}

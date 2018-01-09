package itg8.com.serviceapp.account;


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

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.account.model.CustomerCareModel;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.account.model.VideoModel;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerCareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerCareFragment extends Fragment implements CommonMethod.AccountListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.rl_hide)
    RelativeLayout rlHide;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private BannerModel mParam2;
    private Context mContext;
    private List<CustomerCareModel> list;
    private Dao<BannerModel, Integer> mDAOBanner = null;
    private CustomerCareAdapter adapter;
    private CommonMethod.AccountListner listner;


    public CustomerCareFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1      Parameter 1.
     * @param bannerModel Parameter 2.
     * @return A new instance of fragment CustomerCareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerCareFragment newInstance(List<CustomerCareModel> param1) {
        CustomerCareFragment fragment = new CustomerCareFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_care, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        if (list != null && list.size() > 0) {
            showHideView( recyclerView,rlHide);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            adapter = new CustomerCareAdapter(mContext, list);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.invalidate();
        } else {
            showHideView(rlHide, recyclerView);
        }


    }

    private void showHideView(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        // listner = (CommonMethod.AccountListner) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onKnowledgeListener(List<KnowledgeModel> list) {

    }

    @Override
    public void onCareCustomerListener(List<CustomerCareModel> list) {
        this.list = list;
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onVideoListener(List<VideoModel> list) {

    }
}

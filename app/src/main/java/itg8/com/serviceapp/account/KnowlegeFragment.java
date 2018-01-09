package itg8.com.serviceapp.account;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KnowlegeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KnowlegeFragment extends Fragment implements KnowledgeAdapter.ItemClickedListner {
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
    private BannerModel bannerModel;
    private Context mContext;
    private List<KnowledgeModel> list;
    private KnowledgeAdapter adapter;


    public KnowlegeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment KnowlegeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KnowlegeFragment newInstance(List<KnowledgeModel> param1) {
        KnowlegeFragment fragment = new KnowlegeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
        //args.putParcelable(ARG_PARAM2, bannerModel);
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
        View view = inflater.inflate(R.layout.fragment_knowlege, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {


        if (list != null && list.size() > 0) {
            showHideView(recyclerView, rlHide);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            adapter = new KnowledgeAdapter(mContext, list, this);
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }


    @Override
    public void onItemClicked(int position, KnowledgeModel model) {
        if (model.getAppLink() != null) {
            String url = model.getAppLink();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else {
            Toast.makeText(mContext, " App link not  available", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}

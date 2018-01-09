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
import itg8.com.serviceapp.account.model.CustomerCareModel;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.account.model.VideoModel;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoLibFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoLibFragment extends Fragment implements VideoLibAdapter.VideoItemLiatener, CommonMethod.AccountListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_hide)
    RelativeLayout rlHide;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private BannerModel mParam2;
    private Context mContext;
    private List<VideoModel> list;
    private CommonMethod.AccountListner listner;
    private VideoLibAdapter adapter;


    public VideoLibFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment VideoLibFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoLibFragment newInstance(List<VideoModel> param1) {
        VideoLibFragment fragment = new VideoLibFragment();
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
        View view = inflater.inflate(R.layout.fragment_video_lib, container, false);
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
            adapter = new VideoLibAdapter(mContext, list, this);
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


    private void spliteUrl() {


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        // listner = (CommonMethod.AccountListner) mContext;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }

    @Override
    public void onItemClickec(int position, VideoModel model) {
        if (model.getPath() != null) {
            String url = model.getPath();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else {
            Toast.makeText(mContext, " App link not  available", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    public void onKnowledgeListener(List<KnowledgeModel> list) {

    }

    @Override
    public void onCareCustomerListener(List<CustomerCareModel> list) {

    }

    @Override
    public void onVideoListener(List<VideoModel> list) {
        this.list = list;
        adapter.notifyDataSetChanged();

    }
}

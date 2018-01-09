package itg8.com.serviceapp.enquiry;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.RaisedTicketActivity;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProductFragment extends DialogFragment implements View.OnClickListener, ProductListAdapter.ProductItemSelectedListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Unbinder unbinder;
    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.recyclerViewProduct)
    RecyclerView recyclerViewProduct;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.lnear_layout)
    LinearLayout lnearLayout;
    @BindView(R.id.rel_layout)
    RelativeLayout relLayout;

    // TODO: Rename and change types of parameters
    private String mParam2;
    private android.content.Context mContext;
    private ArrayList<ProductModel> list;
    CommonMethod.ProductItemSendToActivityListener listener;
    private ProductListAdapter adapter;


    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static ProductFragment newInstance(List<ProblemModel> param1, String param2) {
////        ProductFragment fragment = new ProductFragment();
////        Bundle args = new Bundle();
////        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
////        args.putString(ARG_PARAM2, param2);
////        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           list = getArguments().getParcelableArrayList(CommonMethod.PRODUCTLIST);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        unbinder = ButterKnife.bind(this, view);
        init(list);
        return view;
    }

    private void init(List<ProductModel> list) {
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new ProductListAdapter(mContext, this.list, this);
        recyclerViewProduct.setAdapter(adapter);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onQueryTextChange(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private boolean onQueryTextChange(String s) {
        final List<ProductModel> filteredModelList = filter(list, s);

        init(filteredModelList);

        adapter.replaceAll(filteredModelList);
        adapter.notifyDataSetChanged();
       // list_subproductselect.scrollToPosition(0);

        return true;

    }

    private List<ProductModel> filter(ArrayList<ProductModel> list, String s) {
         List<ProductModel> tempList = new ArrayList<>();
         for(ProductModel model:list)
         {
             if(model.getItemName().equalsIgnoreCase(s))
             {
                 tempList.add(model);
             }
         }
        return tempList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mContext!= null)
        {
            mContext= null;
        }
    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case R.id.btn_cancel:
                 getDialog().dismiss();
                 break;
             case R.id.btn_ok:
                  getDialog().dismiss();
                 break;
         }

    }

    @Override
    public void onProductItemSelected(int position, ProductModel model) {
        //listener.onSendProduct(model);

        ((RaisedTicketActivity)mContext).sendModelDataToActivity(model);
         getDialog().dismiss();

    }

    @Override
    public void onAttach(Activity activity) {
        mContext = activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        if(mContext!= null)
        {
            mContext= null;
        }
        super.onDetach();
    }
}

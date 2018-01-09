package itg8.com.serviceapp.ticket;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.enquiry.ProductFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RaisedTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RaisedTicketFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinerProduct;
    private Spinner spinerProblem;
    public TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutInvoice;
    private TextInputLayout inputLayoutDate;
    private EditText inputProduct;
    private EditText inputDate;
    private EditText inputInvoice;
    private Button btnOk;
    private static final String[]Product = {"item 1", "item 2", "item 3"};
    private static final String[]Problem = {"Problem 1", "Problem 2","Other"};
    private TextInputLayout inputLayoutOther;
    private TextInputLayout inputLayoutDeal;
    private EditText inputOther;


    public RaisedTicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RaisedTicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RaisedTicketFragment newInstance(String param1, String param2) {
        RaisedTicketFragment fragment = new RaisedTicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_raised_ticket, container, false);
         initView(view);


         return view;
    }

    private void initView(View view) {

//        spinerProduct = (Spinner) view.findViewById(R.id.spinner_product);
        spinerProblem = (Spinner) view.findViewById(R.id.spinner_problem);
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_product);
        inputLayoutInvoice = (TextInputLayout)view. findViewById(R.id.input_layout_invoice_name);
        inputLayoutDate = (TextInputLayout) view.findViewById(R.id.input_layout_date);
        inputLayoutOther = (TextInputLayout) view.findViewById(R.id.input_layout_other);
        inputLayoutDeal = (TextInputLayout) view.findViewById(R.id.input_layout_deal);
        inputProduct = (EditText) view.findViewById(R.id.input_product);
        inputDate = (EditText)view. findViewById(R.id.input_date);
        inputInvoice = (EditText)view. findViewById(R.id.input_invoice_name);
        inputOther = (EditText)view. findViewById(R.id.input_other);
        btnOk = (Button) view.findViewById(R.id.btn_ok);


        inputDate.setInputType(InputType.TYPE_NULL);
        inputDate.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        inputProduct.setOnClickListener(this);

//        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item,Product);
//
//        adapter.setDropDownViewResource(R.layout.spinner_row);
//        spinerProduct.setAdapter(adapter);


        ArrayAdapter<String>adapterProblem = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,Problem);

        adapterProblem.setDropDownViewResource(R.layout.spinner_row);
        spinerProblem.setAdapter(adapterProblem);
        spinerProblem.setOnItemSelectedListener(this);


    }

    private void hideOtherInput() {
        inputOther.setVisibility(View.GONE);
        inputLayoutOther.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
            case  R.id.input_date:
                break;
             case R.id.input_product:
                 FragmentManager fm = getActivity().getSupportFragmentManager();
                 ProductFragment productFragment = new ProductFragment();
                 // businessCategoryFragment.setListener(this);
                 //  bundle.putParcelableArrayList(CommanMethod.SELECTCATEGORY,tempselect);
                 // businessCategoryFragment.setArguments(bundle);
                 productFragment.show(fm, "DialogCat");
                 break;


         }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
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


}

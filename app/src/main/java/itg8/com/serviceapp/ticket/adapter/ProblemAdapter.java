package itg8.com.serviceapp.ticket.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;

/**
 * Created by Android itg 8 on 10/16/2017.
 */


public class ProblemAdapter  extends ArrayAdapter<ProblemModel> {
    private Context context;
    private final java.util.List<ProblemModel> List;
    private TextView label;



    public ProblemAdapter(Context context,int resoures , List<ProblemModel> catgoryList) {
        super(context,resoures,catgoryList);
        this.context = context;
        this.List = catgoryList;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = getCustomView(position, convertView, parent);
        return view;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        View view;

        //  if (convertView == null) {
        view = LayoutInflater.from(parent.getContext()).inflate((R.layout.item_spinner_problem), parent, false);

        label = (TextView) view.findViewById(R.id.txt_label);
//            view.setTag(view);
//        } else {
//            view = convertView;
//            view.getTag();
//        }

        view.setTag(List.get(position));

        label.setTextColor(Color.BLACK);
        label.setText(List.get(position).getProblem());
        return view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    @Override
    public int getCount() {
        return List.size();
    }
}

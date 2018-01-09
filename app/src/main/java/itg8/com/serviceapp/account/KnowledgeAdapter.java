package itg8.com.serviceapp.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.UtilityHelper;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder>  {

    private List<KnowledgeModel> filterList;
    private Context mContext;
    private List<KnowledgeModel> list;
    private ItemClickedListner listener;

    private int position;




    public interface ItemClickedListner {
        void onItemClicked(int position, KnowledgeModel model);

    }

    public KnowledgeAdapter(Context mContext, List<KnowledgeModel> list
                           , ItemClickedListner listener) {

        this.mContext = mContext;
        this.list = list;
        this.filterList = list;
        this.listener = listener;
    }

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(mContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }

    private void setFontrobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(mContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_acc_knowledge, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        setFontrobotoregular(holder.llAppName, holder.llCompanyName,holder.lblDescription);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
         position = i;
        holder.lblDescription.setText(CheckEmpty(list.get(i).getDescription()));
        holder.llAppNameValue.setText(CheckEmpty(list.get(i).getAppName()));




    }

    private String CheckEmpty(String appName) {
        if(appName!= null)
            return appName;
        return "NOT AVAILABLE";
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_app_name)
        TextView llAppName;
        @BindView(R.id.ll_app_name_value)
        TextView llAppNameValue;
        @BindView(R.id.ll_company_name_value)
        TextView llCompanyNameValue;
        @BindView(R.id.ll_company_name)
        TextView llCompanyName;
        @BindView(R.id.img_download)
        ImageButton imgDownload;
        @BindView(R.id.lbl_description)
        TextView lblDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imgDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onItemClicked(getAdapterPosition(), list.get(getAdapterPosition()));

                }
            });
        }
    }


}

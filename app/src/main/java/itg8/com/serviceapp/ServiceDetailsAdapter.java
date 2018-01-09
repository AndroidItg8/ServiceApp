package itg8.com.serviceapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.common_method.CommonMethod;

/**
 * Created by Android itg 8 on 10/7/2017.
 */

public class ServiceDetailsAdapter extends RecyclerView.Adapter<ServiceDetailsAdapter.ServiceViewHolder> {



    private Context applicationContext;
    private String stringExtra;

    public ServiceDetailsAdapter(Context applicationContext, String stringExtra) {
        this.applicationContext = applicationContext;
        this.stringExtra = stringExtra;
    }

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(applicationContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }

    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.item_reyclerview_service, parent, false);
        ServiceViewHolder holder = new ServiceViewHolder(view);
        setFontrobotoregular(holder.lblDate, holder.lblInvoice,holder.lblServiceName,holder.lblStatus,holder.lblYear,holder.lblProductName);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {
        switch (stringExtra) {
            case CommonMethod.FROM_PURCHASE:
                break;
            case CommonMethod.FROM_SERVICE:
                holder.lblInvoice.setVisibility(View.VISIBLE);
                break;
            case CommonMethod.FROM_TICKET:
                holder.lblStatus.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_date)
        TextView lblDate;
        @BindView(R.id.lbl_otp)
        TextView lblOtp;
        @BindView(R.id.lbl_year)
        TextView lblYear;
        @BindView(R.id.lbl_invoice)
        TextView lblInvoice;
        @BindView(R.id.lbl_productName)
        TextView lblProductName;
        @BindView(R.id.lbl_serviceName)
        TextView lblServiceName;
        @BindView(R.id.lbl_status)
        TextView lblStatus;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

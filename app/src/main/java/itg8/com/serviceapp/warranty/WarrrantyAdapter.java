package itg8.com.serviceapp.warranty;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.common_method.UtilityHelper;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class WarrrantyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final String TAG = WarrrantyAdapter.class.getSimpleName();
    private static final int LOADING_VIEW = 0;
    private static final int NORMAL_VIEW = 1;

    private Context applicationContext;
    private List<WarrantyModel> list;
    Date currentDate;
    long remainingDays = 0;
    private String status = "";



    public void downloadSuccessfull(WarrantyModel model) {
        list.remove((model.getPkid()));
        list.add((model.getPkid()),model);
    }


    public interface WarrantyItemClick {
        void onItemWarrantyClicked(int position, WarrantyModel model);
         void onOpenPdf(int position, WarrantyModel moldel);
    }

    WarrantyItemClick listener;

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(applicationContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }

    }



    public WarrrantyAdapter(Context context, WarrantyActivity listener) {
        this.listener = listener;
        this.applicationContext = context;
        this.list = new ArrayList<>();
        currentDate = Calendar.getInstance().getTime();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        RecyclerView.ViewHolder holder;
        if (i == NORMAL_VIEW) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_warranty, viewGroup, false);
            holder = new WarrantyViewHolder(view);
                   // setFontrobotoregular(holder.lblDate, holder.lblMonths, holder.lblRemainDays, holder.lblStatus, holder.lblYear, holder.lblRemainDaysValue, holder.btnInvoice);

        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_progress, viewGroup, false);
            holder = new ProgressHolder(view);
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
         if(holder instanceof WarrantyViewHolder)
         {
             WarrantyViewHolder warrantyViewHolder = (WarrantyViewHolder) holder;

             warrantyViewHolder.lblInvoiceNumber.setText("Invoice Number "+list.get(i).getInvoiceNumber());
             String str_date  = list.get(i).getDate();

             Date date = UtilityHelper.getInstance().convertStringToDate(str_date, list.get(i).getWarrantyInMonth());

             long diff = UtilityHelper.daysBetween(currentDate, date);

             if (diff <= 0) {
                 warrantyViewHolder.lblStatusValue.setText("In Warranty");
                 remainingDays = Math.abs(diff);
                 warrantyViewHolder.lblStatusValue.setTextColor(Color.parseColor("#fa115906"));
             } else {
                 warrantyViewHolder.lblStatusValue.setText("Expired");
                 warrantyViewHolder.lblStatusValue.setTextColor(Color.parseColor("#FF511108"));
                 remainingDays = 0;
             }

             if(list.get(i).getInvoiceNumber()!= null && !TextUtils.isEmpty(String.valueOf(list.get(i).getInvoiceNumber())))
             {
                 if(list.get(i).isProgress()) {
                     showHideView(warrantyViewHolder.progressView, warrantyViewHolder.btnInvoice);
                 }
                 else
                 {
                     status = "Open";
                     showHideView(warrantyViewHolder.btnInvoice, warrantyViewHolder.progressView);
                 }
             }else
             {
                 status = "Invoice";
                 showHideView(warrantyViewHolder.btnInvoice,warrantyViewHolder.progressView);
             }

             warrantyViewHolder.lblRemainDaysValue.setText(String.valueOf(remainingDays));
             warrantyViewHolder.btnInvoice.setText(status);

         }

    }



    private void showHideView(View show, View  hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<WarrantyModel> o) {
        list.addAll(o);
        notifyDataSetChanged();

    }

    public void addFooter() {
        list.add(null);
        notifyItemInserted(list.size() - 1);
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? LOADING_VIEW : NORMAL_VIEW;
    }

    public void removeFooter() {
//        Logs.d("RemoveFooterB4:" + list.size());

        final int itemRemoved = list.size() - 1;
        list.remove(itemRemoved);
        notifyItemRemoved(itemRemoved);
        notifyItemRangeChanged(itemRemoved, list.size());
//        Logs.d("RemoveFooterAfter:" + list.size());
    }

    public class WarrantyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_date)
        TextView lblDate;
        @BindView(R.id.lbl_otp)
        TextView lblOtp;
        @BindView(R.id.lbl_year)
        TextView lblYear;
        @BindView(R.id.lbl_status)
        TextView lblStatus;
        @BindView(R.id.lbl_status_value)
        TextView lblStatusValue;
        @BindView(R.id.lbl_remain_days)
        TextView lblRemainDays;
        @BindView(R.id.lbl_remain_days_value)
        TextView lblRemainDaysValue;
        @BindView(R.id.lbl_months)
        TextView lblMonths;
        @BindView(R.id.lbl_invoiceNumber)
        TextView lblInvoiceNumber;
        @BindView(R.id.btn_invoice)
        Button btnInvoice;
        @BindView(R.id.progressView)
        CircularProgressView progressView;

        public WarrantyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnInvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!list.get(getAdapterPosition()).isDownload()) {
                        listener.onItemWarrantyClicked(getAdapterPosition(), list.get(getAdapterPosition()));
                    } else {

                        listener.onOpenPdf(getAdapterPosition(), list.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}

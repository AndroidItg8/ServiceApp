package itg8.com.serviceapp.ticket;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class TicketTheaterAdapter extends RecyclerView.Adapter<TicketTheaterAdapter.TheaterViewHolder> {
    private Context mContext;
    private List<TicketModel> list;
    private Calendar date;

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(mContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }
    private void setFontRobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(mContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }

    }

    public TicketTheaterAdapter(Context mContext, List<TicketModel> list) {

        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public TheaterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_open_ticket, viewGroup, false);
        TicketTheaterAdapter.TheaterViewHolder holder = new TicketTheaterAdapter.TheaterViewHolder(view);
        setFontRobotoMedium(holder.lblDate, holder.lblYear,holder.lblInvoiceNumber,holder.lblStatus,holder.lblYear,holder.lblStatusValue, holder.lblProblem,holder.lblOtp);

        return holder;
    }

    @Override
    public void onBindViewHolder(TheaterViewHolder holder, int i) {
        date=(CommonMethod.ConvertStringToDate(list.get(i).getLastModifieddate()));
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = String.valueOf(date.get(Calendar.MONTH)+1);
        String day = String.valueOf(date.get(Calendar.DATE));
        holder.lblOtp.setText(day);
        holder.lblYear.setText(month+"-"+year);


        if(list.get(i).getStatus()== null||list.get(i).getStatus().equals(CommonMethod.STATUS_OPEN) )
        {
            holder.lblStatusValue.setBackground(ContextCompat.getDrawable(mContext,R.drawable.custom_img_button));
            holder.lblStatusValue.setText("OPEN");
        }
        else if(list.get(i).getStatus().equals(CommonMethod.STATUS_CLOSE))
        {
            holder.lblStatusValue.setBackground(ContextCompat.getDrawable(mContext,R.drawable.custom_img_closed));
            holder.lblStatusValue.setText("CLOSED");

        }
        else if(list.get(i).getStatus().equals(CommonMethod.STATUS_ASSIGN))
        {
            holder.lblStatusValue.setBackground(ContextCompat.getDrawable(mContext,R.drawable.custom_bg_assign));
            holder.lblStatusValue.setText("ASSIGN");

        }
        holder.lblProblem.setText(checkTextEmtpy(list.get(i).getDescription()));
        holder.lblInvoiceNumber.setText(checkTextEmtpy(String.valueOf(list.get(i).getInvoiceFkid())));


    }

    private String  checkTextEmtpy(String description) {
        if(description!= null)
        {
            return description;
        }else
        {

            return "NOT AVAILABLE";
        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TheaterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_date)
        TextView lblDate;
        @BindView(R.id.lbl_otp)
        TextView lblOtp;
        @BindView(R.id.lbl_year)
        TextView lblYear;
        @BindView(R.id.lbl_invoiceNumber)
        TextView lblInvoiceNumber;
        @BindView(R.id.lbl_problem)
        TextView lblProblem;
        @BindView(R.id.lbl_status)
        TextView lblStatus;
        @BindView(R.id.lbl_status_value)
        TextView lblStatusValue;
        public TheaterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

package itg8.com.serviceapp.feedback;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * Created by USER-pc on 10/17/2017.
 */

class TicketFeedbackClosedAdapter extends RecyclerView.Adapter<TicketFeedbackClosedAdapter.ClosedViewHolder>  {

    private Context mContext;
    private List<TicketModel> list;
    private Calendar date;

    public  interface ItemClickedListener{
        void onItemClicked(int position,TicketModel model);
    }
    ItemClickedListener listener;

    public TicketFeedbackClosedAdapter(Context mContext, List<TicketModel> list, ItemClickedListener listener) {

        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
    }

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

    @Override
    public ClosedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_closed_feedback_ticket, viewGroup, false);
        ClosedViewHolder holder = new ClosedViewHolder(view);
         setFontRobotoMedium(holder.lblDate, holder.lblInvoiceNumber,holder.lblStatus,holder.lblStatusValue,holder.lblYear,holder.lblProblem, holder.lblInvoiceNumber);
//        setFontrobotoregular(holder.lblDate, holder.lblInvoiceNumber,holder.lblStatus,holder.lblStatusValue,holder.lblYear,holder.lblProblem, holder.lblInvoiceNumber);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClosedViewHolder holder, int i) {
        date=(CommonMethod.ConvertStringToDate(list.get(i).getLastModifieddate()));
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = String.valueOf(date.get(Calendar.MONTH)+1);
        String day = String.valueOf(date.get(Calendar.DATE));
        holder.lblOtp.setText(day);
        holder.lblYear.setText(month+"-"+year);
        holder.lblInvoiceNumber.setText((checkTextEmtpy(String.valueOf(list.get(i).getInvoiceFkid()))));
        holder.lblProblem.setText((checkTextEmtpy(list.get(i).getOtherProblem())));

    }

    private String  checkTextEmtpy(String s) {
         if(s != null)
         {
             return s;
         }
        return "NOT AVAILBlE";
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ClosedViewHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.btn_feedback)
        Button btnFeedback;

        public ClosedViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                listener.onItemClicked(getAdapterPosition(),list.get(getAdapterPosition()));
                }
            });

        }
    }
}

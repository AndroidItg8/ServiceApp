package itg8.com.serviceapp.ticket;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
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
import itg8.com.serviceapp.common_method.Logs;
import itg8.com.serviceapp.ticket.model.TicketModel;
import itg8.com.serviceapp.warranty.ProgressHolder;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class TicketAssigndAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_VIEW = 0;
    private int LOADING_VIEW=1;

    private Context mContext;
    private List<TicketModel> list;
    private Calendar date;

    public TicketAssigndAdapter(Context mContext, List<TicketModel> list) {

        this.mContext = mContext;
        this.list = list;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_assign_ticket, viewGroup, false);
//        AssignViewHolder holder = new AssignViewHolder(view);
//        setFontRobotoMedium(holder.lblDate,holder.lblInvoiceNumber,holder.lblOtp,holder.lblProblem, holder.lblYear,holder.txtPersonName, holder.lblDate, holder.txtContact);

        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_assign_ticket, parent, false);
            holder = new TicketAssigndAdapter.AssignViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof TicketAssigndAdapter.AssignViewHolder) {
            TicketAssigndAdapter.AssignViewHolder assignViewHolder = (TicketAssigndAdapter.AssignViewHolder) holder;

            date=(CommonMethod.ConvertStringToDate(list.get(i).getLastModifieddate()));
            String year = String.valueOf(date.get(Calendar.YEAR));
            String month = String.valueOf(date.get(Calendar.MONTH)+1);
            String day = String.valueOf(date.get(Calendar.DATE));
            assignViewHolder.lblOtp.setText(day);
            assignViewHolder.lblYear.setText(month+"-"+year);

            assignViewHolder.lblProblem.setText(checkTextEmtpy(list.get(i).getDescription()));
            assignViewHolder.txtContact.setText(checkTextEmtpy(list.get(i).getAssignedContactno()));
            assignViewHolder.txtPersonName.setText(checkTextEmtpy(list.get(i).getAssignedpersonname()));
            assignViewHolder.lblInvoiceNumber.setText(checkTextEmtpy(String.valueOf(list.get(i).getInvoiceFkid())));
        }
    }




    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addItems(List<TicketModel> o) {
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
        Logs.d("RemoveFooterB4:" + list.size());

        final int itemRemoved = list.size() - 1;
        list.remove(itemRemoved);
        notifyItemRemoved(itemRemoved);
        notifyItemRangeChanged(itemRemoved, list.size());
        Logs.d("RemoveFooterAfter:" + list.size());
    }

    public class AssignViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.lbl_personName)
        TextView lblPersonName;
        @BindView(R.id.txt_personName)
        TextView txtPersonName;
        @BindView(R.id.lbl_contact)
        TextView lblContact;
        @BindView(R.id.txt_contact)
        TextView txtContact;

        public AssignViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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
}

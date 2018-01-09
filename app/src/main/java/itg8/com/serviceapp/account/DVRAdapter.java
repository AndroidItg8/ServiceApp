package itg8.com.serviceapp.account;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itg8.com.serviceapp.R;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public class DVRAdapter extends RecyclerView.Adapter<DVRAdapter.ViewHolder> {
    private Context mContext;

    public DVRAdapter(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public DVRAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_acc_dvr, viewGroup, false);
        DVRAdapter.ViewHolder holder = new DVRAdapter.ViewHolder(view);
        //setFontrobotoregular(holder.lblDate, holder.lblMonths,holder.lblRemainDays,holder.lblStatus,holder.lblYear,holder.lblRemainDaysValue, holder.btnInvoice);
        return holder;
    }

    @Override
    public void onBindViewHolder(DVRAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

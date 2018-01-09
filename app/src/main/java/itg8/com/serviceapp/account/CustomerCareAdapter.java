package itg8.com.serviceapp.account;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.account.model.CustomerCareModel;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.database.BaseDatabaseHelper;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public class CustomerCareAdapter extends RecyclerView.Adapter<CustomerCareAdapter.ViewHolder> {


    private Context mContext;
    private List<CustomerCareModel> list;
    private BannerModel bannerId;

    public CustomerCareAdapter(Context mContext, List<CustomerCareModel> list) {

        this.mContext = mContext;
        this.list = list;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_acc_customer_care, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        setFontrobotoMedium(holder.lblService, holder.lblEmail,holder.lblCustomer,holder.lblNumber,holder.lblServiceName, holder.lblServiceName);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.lblNumber.setText(checkNullText(list.get(i).getPhone1()) + "  , " +(checkNullText( list.get(i).getPhone2())));
        holder.lblEmail.setText(checkNullText(list.get(i).getEmail1()) + " , " + checkNullText(list.get(i).getEmail2()));
        holder.lblService.setText(checkNullText(list.get(i).getDescription()));
        bannerId=getBannerModel(list.get(i).getBrandfkid());
        if(bannerId!= null)
        holder.lblCustomer.setText(bannerId.getBrandName());



    }

    private BannerModel getBannerModel(int brandfkid) {
        try {
            Dao<BannerModel, Integer> mDAOBanner = BaseDatabaseHelper.getBaseInstance().getHelper(mContext).getmDAOBanner();
            return mDAOBanner
                       .queryBuilder()
                       .where()
                       .eq(BannerModel.FIELD_ID,brandfkid)
                       .queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return  null;

    }

    private String checkNullText(String feild) {
        if(feild!= null)
        {
            return feild;
        }else
            return "NOT AVAILABLE";
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_customer)
        TextView llCustomer;
        @BindView(R.id.lbl_customer)
        TextView lblCustomer;


        @BindView(R.id.lbl_serviceName)
        TextView lblServiceName;
        @BindView(R.id.lbl_service)
        TextView lblService;
        @BindView(R.id.lbl_number)
        TextView lblNumber;
        @BindView(R.id.img_download)
        TextView imgDownload;
        @BindView(R.id.lbl_email)
        TextView lblEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

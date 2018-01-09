package itg8.com.serviceapp.enquiry;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ProductModel> list;

    public void replaceAll(List<ProductModel> filteredModelList) {

    }

    public interface ProductItemSelectedListner {
        void onProductItemSelected(int position, ProductModel model);
    }

    ProductItemSelectedListner listner;

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

    public ProductListAdapter(Context mContext, ArrayList<ProductModel> list, ProductItemSelectedListner listner) {

        this.mContext = mContext;
        this.list = list;
        this.listner = listner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_product, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        setFontRobotoMedium(holder.lblProductName);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.lblProductName.setText(list.get(i).getItemName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_productName)
        TextView lblProductName;
        @BindView(R.id.checkbox)
        CheckBox checkbox;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listner.onProductItemSelected(
                             getAdapterPosition(),list.get(getAdapterPosition())
                     );
                 }
             });
        }
    }
}

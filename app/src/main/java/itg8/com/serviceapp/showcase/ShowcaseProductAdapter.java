package itg8.com.serviceapp.showcase;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/10/2017.
 */

public class ShowcaseProductAdapter extends RecyclerView.Adapter<ShowcaseProductAdapter.ShowCaseViewHolder> {

    private final Context context;
    private final List<ProductModel> categoryList;
    String[] images = {"http://4.imimg.com/data4/SI/TU/MY-2780686/cctv-dvr-system-250x250.jpg",
            "http://4.imimg.com/data4/DA/SA/MY-2780686/cctv-and-dvr-specials-kits-250x250.jpg",
            "http://4.imimg.com/data4/DA/SA/MY-2780686/cctv-and-dvr-specials-kits-250x250.jpg"};



    public ShowcaseProductAdapter(Context context, List<ProductModel> categoryList, ShowcaseAdapter.ItemClickedListener listener) {

        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }


    public interface ItemClickedListener {
        void itemProductClicked(int position, ProductModel model);
    }

    private ShowcaseAdapter.ItemClickedListener listener;

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(context);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }

    private void setFontrobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(context);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }


    @Override
    public ShowCaseViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_showcase, parent, false);
        ShowCaseViewHolder holder = new ShowCaseViewHolder(view);
        //setFontrobotoregular(holder.txtProductName);
//        setFontrobotoMedium(holder.txtProductName);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShowCaseViewHolder holder, int i) {
        String randomStr = images[new Random().nextInt(images.length)];
        Picasso.with(context)
                .load(randomStr)
                .placeholder(R.drawable.itech_logo)
                .error(R.drawable.itech_logo)
                .into(holder.img);


        holder.txtProductName.setText(categoryList.get(i).getItemName());


    }

    @Override
    public int getItemCount() {
        //return productOneList.size();
        return categoryList.size();
    }

    public class ShowCaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.txt_product_name)
        TextView txtProductName;

        public ShowCaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemProductClicked(getAdapterPosition(), categoryList.get(getAdapterPosition()));

                }
            });
        }
    }
}

package itg8.com.serviceapp.showcase;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
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
import itg8.com.serviceapp.common_method.AppApplication;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;

/**
 * Created by Android itg 8 on 10/10/2017.
 */

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.ShowCaseViewHolder> {

    private final Context context;
    private final List<CategoryModel> categoryList;
    String[] images = {"http://4.imimg.com/data4/SI/TU/MY-2780686/cctv-dvr-system-250x250.jpg",
            "http://4.imimg.com/data4/DA/SA/MY-2780686/cctv-and-dvr-specials-kits-250x250.jpg",
            "http://4.imimg.com/data4/DA/SA/MY-2780686/cctv-and-dvr-specials-kits-250x250.jpg"};


    public ShowcaseAdapter(Context context, List<CategoryModel> categoryList, ItemClickedListener listener) {

        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }


    public interface ItemClickedListener {
        void itemProductClicked(int position, ProductModel model);
    }

    private ItemClickedListener listener;
    private List<ProductModel> productOneList;
    private Context applicationContext;

    private void setFontrobotoregular(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoRegular(applicationContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }

    private void setFontrobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(applicationContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }


    @Override
    public ShowCaseViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_showcase_category, parent, false);
        ShowCaseViewHolder holder = new ShowCaseViewHolder(view);
        //setFontrobotoregular(holder.txtProductName);
//        setFontrobotoMedium(holder.txtProductName);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShowCaseViewHolder holder, int i) {

        ShowcaseProductAdapter adapter=new ShowcaseProductAdapter(context, AppApplication.getInstance().getProductByCat().get(categoryList.get(i).getPkid()),listener);
        holder.recyclerViewProduct.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerViewProduct.setAdapter(adapter);
        holder.lblApp.setText(categoryList.get(i).getCategoryname());
    }

    @Override
    public int getItemCount() {
        //return productOneList.size();
        return categoryList.size();
    }

    public class ShowCaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_app)
        TextView lblApp;
        @BindView(R.id.lbl_see)
        TextView lblSee;
        @BindView(R.id.ll_category_name)
        RelativeLayout llCategoryName;
        @BindView(R.id.recyclerViewProduct)
        RecyclerView recyclerViewProduct;
        @BindView(R.id.rl_category_first)
        RelativeLayout rlCategoryFirst;

        public ShowCaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

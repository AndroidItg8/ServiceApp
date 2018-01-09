package itg8.com.serviceapp.showcase;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.common_method.CommonMethod;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * Created by Android itg 8 on 10/10/2017.
 */

public class ViewPagerAdapter extends android.support.v4.view.PagerAdapter {


    //    private final NGODescriptionActivity context;
    private String from;
    Context mContext;
    private List<BannerModel> list;


    public ViewPagerAdapter(Context context, List<BannerModel> list) {
        this.mContext = context;

        this.list = list;
    }


    @Override
    public int getCount() {
       // return list.size();
        return list.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
       // imageView.setImageResource(R.drawable.itech_logo);
//        Picasso.with(mContext)
//                .load(String.valueOf(list.get(position).getBrandImage()))
//                .placeholder(R.drawable.itech_logo)
//                .error(R.drawable.itech_logo)
//                .into(imageView);


        Picasso.with(mContext)
                .load(Uri.parse(CommonMethod.BASE_URL + list.get(position).getBrandImage()))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(mContext)
                                .load((CommonMethod.BASE_URL + list.get(position).getBrandImage()))
                                        .placeholder(R.drawable.itech_logo)
                                        .error(R.drawable.itech_logo)
                                        .into(imageView);
                    }

                });
        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}

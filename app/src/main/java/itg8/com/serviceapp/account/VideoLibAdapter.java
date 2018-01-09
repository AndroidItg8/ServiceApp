package itg8.com.serviceapp.account;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import itg8.com.serviceapp.R;
import itg8.com.serviceapp.account.model.VideoModel;
import itg8.com.serviceapp.common_method.CommonMethod;

/**
 * Created by Android itg 8 on 10/12/2017.
 */

public class VideoLibAdapter extends RecyclerView.Adapter<VideoLibAdapter.ViewHolder> {
    private static final String TAG =VideoLibAdapter.class.getSimpleName() ;
    private Context mContext;
    private List<VideoModel> list;
    private VideoItemLiatener listener;

    private static final   String mainUrl="http://img.youtube.com/vi/";
    private static final String endUrl="/mqdefault.jpg";
     public interface VideoItemLiatener{
         void onItemClickec(int position, VideoModel model);
     }


    public VideoLibAdapter(Context mContext, List<VideoModel> list, VideoItemLiatener listener) {

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

    private void setFontrobotoMedium(TextView... textViews) {
        Typeface typeface = CommonMethod.setFontRobotoMedium(mContext);
        for (TextView txt : textViews) {
            txt.setTypeface(typeface);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_acc_video, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        setFontrobotoMedium(holder.lblDescription,holder.llVideoName);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {

         holder.lblDescription.setText(list.get(i).getPath());
        holder.llVideoName.setText(list.get(i).getDefination());

      final String imageThumb =  mainUrl+splitePath(list.get(i).getPath())+endUrl;
//         holder.lblDescription.setText(list.get(i).getDefination());



        Picasso.with(mContext)
                .load(Uri.parse(imageThumb))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.img, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(mContext)
                                .load(imageThumb)
                                        .placeholder(R.drawable.itech_logo)
                                        .error(R.drawable.itech_logo)
                                        .into(holder.img);
                    }

                });
    }

    private String splitePath(String path) {
        String string = path;
        String[] parts = string.split("=");
        String part1 = parts[0];
        String part2 = parts[1];
        Log.d(TAG,"SplitURl:"+part2);
        return part2;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.ll_video_name)
        TextView llVideoName;
        @BindView(R.id.lbl_description)
        TextView lblDescription;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                      listener.onItemClickec(getAdapterPosition(), list.get(getAdapterPosition()));

                 }
             });
        }
    }
}

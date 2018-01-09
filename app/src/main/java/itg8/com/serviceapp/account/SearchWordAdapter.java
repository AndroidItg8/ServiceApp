package itg8.com.serviceapp.account;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import itg8.com.serviceapp.R;
import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * Created by Android itg 8 on 10/27/2017.
 */

public class SearchWordAdapter extends RecyclerView.Adapter<SearchWordAdapter.ViewHolder> {
    private Context context;
    private List<BannerModel> listqword;
    private Activity activity;
    OnSearchSelectListner listner;

    public SearchWordAdapter(Activity activity, List<BannerModel> word) {

        this.activity = activity;
        this.listqword = word;
        this.listner = (OnSearchSelectListner) activity;

    }

    public interface OnSearchSelectListner {
        void onSearchSelect(BannerModel model);
    }

    //    public SearchWordAdapter(Activity activity, List<SearchWord> listword)
//    {
//        this.activity = activity;
//         this.listqword = listword;
//        this.listner= (OnSearchSelectListner) activity;
//
//    }
    @Override
    public SearchWordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchWordAdapter.ViewHolder holder, int position) {
        holder.textView.setText(listqword.get(position).getBrandName());
    }

    @Override
    public int getItemCount() {
        return listqword.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtSearchWord);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listner.onSearchSelect(listqword.get(getAdapterPosition()));
        }
    }


}

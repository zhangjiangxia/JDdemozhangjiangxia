package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchShowAdapter extends RecyclerView.Adapter<SearchShowAdapter.VooHodel> {
    Context  context;
    List<SearchBean.DataBean> data;

    public SearchShowAdapter(Context context, List<SearchBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VooHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sousuoshow, parent, false);
        VooHodel vooHodel = new VooHodel(view);
        return vooHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHodel holder, int position) {

        holder.showtext.setText(data.get(position).getTitle());
        String images = data.get(position).getImages();
        String[] split = images.split("\\|");
        Picasso.with(context).load(split[0]).into(holder.showimag);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class VooHodel extends RecyclerView.ViewHolder implements View.OnClickListener {

        private  ImageView showimag;
        private  TextView showtext;

        public VooHodel(View itemView) {
            super(itemView);
            showimag = itemView.findViewById(R.id.show_ss_imag);
            showtext = itemView.findViewById(R.id.show_ss_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            onitmeclickLentener.onitmeclick(layoutPosition);

        }
    }

    //接口回调
    onitmeclickLentener onitmeclickLentener;


    public interface onitmeclickLentener {
        void onitmeclick(int itemCount);
    }

    public void setOnitmeclickLentener(SearchShowAdapter.onitmeclickLentener onitmeclickLentener) {
        this.onitmeclickLentener = onitmeclickLentener;
    }
}

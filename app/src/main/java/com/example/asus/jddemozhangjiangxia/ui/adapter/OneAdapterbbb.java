package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OneAdapterbbb extends RecyclerView.Adapter<OneAdapterbbb.VooHdeol> {
    Context context;
    List<InfoBean.DataBean.TuijianBean.ListBeanX> tlist;

    public OneAdapterbbb(Context context, List<InfoBean.DataBean.TuijianBean.ListBeanX> tlist) {
        this.context = context;
        this.tlist = tlist;
    }

    //接口回调
    onitmeclickLentener onitmeclickLentener;


    public interface onitmeclickLentener {
        void onitmeclick(int itemCount);
    }

    public void setOnitmeclickLentener(OneAdapterbbb.onitmeclickLentener onitmeclickLentener) {
        this.onitmeclickLentener = onitmeclickLentener;
    }

    @NonNull
    @Override
    public VooHdeol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oueitme3, parent, false);
        VooHdeol vooHdeol = new VooHdeol(view);
        return vooHdeol;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHdeol holder, int position) {
        holder.text3.setText(tlist.get(position).getTitle());
        String images = tlist.get(position).getImages();
        String[] split = images.split("\\|");
        Picasso.with(context).load(split[0]).into(holder.imag3);
        double price = tlist.get(position).getPrice();
        holder.text4.setText("¥" + price);

    }

    @Override
    public int getItemCount() {
        return tlist.size();
    }


    class VooHdeol extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imag3;
        private TextView text3;
        private TextView text4;

        public VooHdeol(View itemView) {
            super(itemView);
            imag3 = itemView.findViewById(R.id.one_image3);
            text3 = itemView.findViewById(R.id.one_text3);
            text4 = itemView.findViewById(R.id.one_text4);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            onitmeclickLentener.onitmeclick(layoutPosition);

        }

    }

}

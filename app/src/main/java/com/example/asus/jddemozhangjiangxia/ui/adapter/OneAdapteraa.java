package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class OneAdapteraa extends RecyclerView.Adapter<OneAdapteraa.VooHdeol> {
    Context  context;
    List<InfoBean.DataBean.MiaoshaBean.ListBean> mslist;

    public OneAdapteraa(Context context, List<InfoBean.DataBean.MiaoshaBean.ListBean> mslist) {
        this.context = context;
        this.mslist = mslist;
    }

    @NonNull
    @Override
    public VooHdeol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oueitme2, parent, false);
        VooHdeol vooHdeol = new VooHdeol(view);
        return vooHdeol;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHdeol holder, int position) {
        double price = mslist.get(position).getPrice();
        double bargainPrice = mslist.get(position).getBargainPrice();
        String images = mslist.get(position).getImages();
        String[] split = images.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.imag2.setImageURI(uri);
        holder.text2.setText("¥"+ price);
        holder.textView3.setText("¥"+ bargainPrice);
    }

    @Override
    public int getItemCount() {
        return mslist.size();
    }


    class  VooHdeol extends RecyclerView.ViewHolder implements View.OnClickListener {

        private  SimpleDraweeView imag2;
        private TextView text2;
        private final TextView textView3;

        public VooHdeol(View itemView) {
            super(itemView);
            imag2 = itemView.findViewById(R.id.one_image);
            text2 = itemView.findViewById(R.id.one_text);
            textView3 = itemView.findViewById(R.id.one_text2);
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

    public void setOnitmeclickLentener(OneAdapteraa.onitmeclickLentener onitmeclickLentener) {
        this.onitmeclickLentener = onitmeclickLentener;
    }
}

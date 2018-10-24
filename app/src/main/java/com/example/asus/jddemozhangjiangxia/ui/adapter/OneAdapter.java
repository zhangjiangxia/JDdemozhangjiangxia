package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Date;
import java.util.List;

public class OneAdapter extends RecyclerView.Adapter<OneAdapter.Voohodel>{
    Context  context;
    List<InfoBean.DataBean.FenleiBean> fenlei;

    public OneAdapter(Context context, List<InfoBean.DataBean.FenleiBean> fenlei) {
        this.context = context;
        this.fenlei = fenlei;
    }

    @NonNull
    @Override
    public Voohodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oueitme, parent, false);
        Voohodel voohodel = new Voohodel(view);
        return voohodel;
    }

    @Override
    public void onBindViewHolder(@NonNull Voohodel holder, int position) {
        String icon = fenlei.get(position).getIcon();
        String[] split = icon.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.one_imag1.setImageURI(uri);
        holder.one_text1.setText(fenlei.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return fenlei.size();
    }

    class   Voohodel extends RecyclerView.ViewHolder  {

        private SimpleDraweeView one_imag1;
        private  TextView one_text1;

        public Voohodel(View itemView) {
           super(itemView);
            one_imag1 = itemView.findViewById(R.id.one_image);
            one_text1 = itemView.findViewById(R.id.one_text);

        }
    }
}

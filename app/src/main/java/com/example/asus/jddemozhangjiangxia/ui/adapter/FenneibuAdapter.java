package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

public class FenneibuAdapter extends RecyclerView.Adapter<FenneibuAdapter.FenHodel> {
    List<TwoBeanTwo.DataBean.ListBean> list;
    Context context;

    public FenneibuAdapter(List<TwoBeanTwo.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FenHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenneibu, parent, false);
        FenHodel fenHodel = new FenHodel(view);
        return fenHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull FenHodel holder, int position) {
        String icon = list.get(position).getIcon();
        Picasso.with(context).load(icon).into(holder.imag2);
        holder.textcc.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FenHodel extends RecyclerView.ViewHolder {
        private final ImageView imag2;
        private final TextView textcc;

        public FenHodel(View itemView) {
            super(itemView);
            imag2 = itemView.findViewById(R.id.fen_imag);
            textcc = itemView.findViewById(R.id.fen_textcc);
        }
    }
}

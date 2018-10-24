package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;

import java.util.List;

public class FenyeAdapter extends RecyclerView.Adapter<FenyeAdapter.FenHodel>  {
    List<TwoBeanTwo.DataBean> data;
    Context context;

    public FenyeAdapter(List<TwoBeanTwo.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FenHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenyouitme, parent, false);
        FenHodel fenHodel = new FenHodel(view);
        return fenHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull FenHodel holder, int position) {
        List<TwoBeanTwo.DataBean.ListBean> list = data.get(position).getList();
        holder.rcview.setLayoutManager(new GridLayoutManager(context,3));
        holder.fentext.setText(data.get(position).getName());
        holder.rcview.setAdapter(new FenneibuAdapter(list,context));

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class FenHodel extends RecyclerView.ViewHolder {

        private  TextView fentext;
        private  RecyclerView rcview;

        public FenHodel(View itemView) {
            super(itemView);
            fentext = itemView.findViewById(R.id.fen_you_text);
            rcview = itemView.findViewById(R.id.fen_nei);
        }
    }
}

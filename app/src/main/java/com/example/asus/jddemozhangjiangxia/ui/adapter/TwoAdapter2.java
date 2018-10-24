package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanOne;

import java.util.List;

public class TwoAdapter2 extends RecyclerView.Adapter<TwoAdapter2.VooHdeol>{
    List<TwoBeanOne.DataBean> data;
    Context  context;

    OnItemClickLisentener onItemClick;

    //1.声明接口对象
    public interface OnItemClickLisentener {
        //2.声明条目点击方法
        void ondianji(int layoutPosition);
    }

    //3.声明方法，进行接口对象的传入
    public void setOnItemClick(OnItemClickLisentener onItemClick) {
        this.onItemClick = onItemClick;
    }



    public TwoAdapter2(List<TwoBeanOne.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public VooHdeol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.twoitme1, parent,false);
        VooHdeol vooHdeol = new VooHdeol(view);
        return vooHdeol;
    }

    @Override
    public void onBindViewHolder(@NonNull VooHdeol holder, int position) {
      holder.textView1.setText(data.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class  VooHdeol extends RecyclerView.ViewHolder implements View.OnClickListener {

        private  TextView textView1;

        public VooHdeol(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tow_text1);
            textView1.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            onItemClick.ondianji(layoutPosition);
        }
    }


}



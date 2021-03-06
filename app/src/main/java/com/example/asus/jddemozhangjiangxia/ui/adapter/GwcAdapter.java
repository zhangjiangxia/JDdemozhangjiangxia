package com.example.asus.jddemozhangjiangxia.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.GwcBean;
import com.example.asus.jddemozhangjiangxia.ui.widget.MyView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GwcAdapter extends BaseExpandableListAdapter {


   public List<GwcBean.DataBean> data;
   public   Context context;

    public GwcAdapter(List<GwcBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {

        GroupViewHolder groupHolder = null;
        if (view == null) {
            groupHolder = new GroupViewHolder();
            view = View.inflate(context, R.layout.grouplayout, null);
            groupHolder.group_checkbox = view.findViewById(R.id.g_check);
            groupHolder.group_textview = view.findViewById(R.id.g_text);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) view.getTag();
        }

        //赋值商家名称
        groupHolder.group_textview.setText(data.get(i).getSellerName());
        boolean shop = isShop(i);
        groupHolder.group_checkbox.setChecked(shop);

        //点击  接口
        groupHolder.group_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onChangeLinsenter != null) {
                    onChangeLinsenter.shopChang(i);
                }
            }
        });
        return view;
    }

    //判断商家的选中状态 进行判断
    public boolean isShop(int i) {
        List<GwcBean.DataBean.ListBean> list = data.get(i).getList();
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childHolder = null;
        if (view == null) {
            childHolder = new ChildViewHolder();
            view = View.inflate(context, R.layout.childlayout, null);
            childHolder.child_checkbox = view.findViewById(R.id.c_check);
            childHolder.child_image = view.findViewById(R.id.c_image);
            childHolder.child_name = view.findViewById(R.id.c_name);
            childHolder.child_price = view.findViewById(R.id.c_price);
            childHolder.myView = view.findViewById(R.id.c_myview);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) view.getTag();
        }
        childHolder.child_name.setText(data.get(i).getList().get(i1).getTitle());
        double price1 = data.get(i).getList().get(i1).getPrice();
        String price = String.valueOf(price1);
        childHolder.child_price.setText("¥" + price);
        String images = data.get(i).getList().get(i1).getImages();
        if (images.contains("|")) {
            String[] split = images.split("\\|");
            Picasso.with(context).load(split[0]).into(childHolder.child_image);
        } else {
            Picasso.with(context).load(images).into(childHolder.child_image);
        }
        List<GwcBean.DataBean.ListBean> list = data.get(i).getList();
        childHolder.child_checkbox.setChecked(list.get(i1).getSelected() == 1);
        childHolder.child_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeLinsenter.goodChang(i, i1);
            }
        });

        childHolder.myView.setOnNumChange(new MyView.onNumChange() {
            @Override
            public void onNumberChange(int number) {
                onChangeLinsenter.goodChang(i, i1, number);
            }
        });
        return view;


    }
    //在接口回调后商家使用的方法
    public void shopall(int i, boolean b) {
        List<GwcBean.DataBean.ListBean> list = data.get(i).getList();
        for (int j = 0; j < list.size(); j++) {
            list.get(j).setSelected(b ? 1 : 0);
        }
    }
    //在接口回调后商品使用的方法
    public void goodselect(int i, int i1) {
        GwcBean.DataBean.ListBean listBean = data.get(i).getList().get(i1);
        listBean.setSelected(listBean.getSelected() == 0 ? 1 : 0);

    }
    //在接口回调后数量改变使用的方法
    public void numshange(int i, int i1, int number) {
        GwcBean.DataBean.ListBean listBean = data.get(i).getList().get(i1);
        listBean.setNum(number);

    }
    //得到所有商品数量的方法
    public int allNum() {
        int numa = 0;
        for (int i = 0; i <data.size() ; i++) {
            List<GwcBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                if (list.get(j).getSelected()==1){
                    int num = list.get(j).getNum();
                    numa+=num;
                }
            }
        }
        return numa;
    }
    //得到所有价格的方法
    public double allPrice() {
        double allpro = 0;
        for (int i = 0; i <data.size() ; i++) {
            List<GwcBean.DataBean.ListBean> list = data.get(i).getList();

            for (int j = 0; j <list.size(); j++) {
                if (list.get(j).getSelected()==1){
                    double price = list.get(j).getPrice();
                    int num = list.get(j).getNum();
                    allpro+=price*num;
                }
            }
        }

        return allpro;
    }
    //判断全选按钮并设置对应的处理的方法
    public  boolean isAllChacked() {
        for (int i = 0; i < data.size(); i++) {
            List<GwcBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    //全局全选按钮状态改变之后调取的方法
    public void chageAll(boolean b) {
        for (int i = 0; i < data.size(); i++) {
            List<GwcBean.DataBean.ListBean> list = data.get(i).getList();

            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(b ? 1 : 0);
            }
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    //设置外层ViewHolder
    public static  class GroupViewHolder {
        CheckBox group_checkbox;
        TextView group_textview;
    }

    //设置子层ViewHolder
    public static class ChildViewHolder {
       CheckBox  child_checkbox;
        TextView child_name, child_price;
        ImageView child_image;
        MyView myView;

    }

    onChangeLinsenter onChangeLinsenter;
    //对外开放 调节口

    public void setOnChangeLinsenter(GwcAdapter.onChangeLinsenter onChangeLinsenter) {
        this.onChangeLinsenter = onChangeLinsenter;
    }

    //接口 控制商家商品数量
    public interface onChangeLinsenter {
        void shopChang(int i);

        void goodChang(int i, int i1);

        void goodChang(int i, int i1, int num);
    }

}

package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.SystemData;

import java.util.ArrayList;
import java.util.List;

public class SystemRightItemAdapter extends RecyclerView.Adapter<SystemRightItemAdapter.MyViewHolder> {
    private Context mContext;
    private List<SystemData.DataBean.ChildrenBean> childrenBeanList = new ArrayList<>();

    public SystemRightItemAdapter(Context context ,List<SystemData.DataBean.ChildrenBean> childrenBeanList){
        this.mContext=context;
        this.childrenBeanList=childrenBeanList;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.lv_tv_text);
            linearLayout=itemView.findViewById(R.id.right_item_layout);
        }
    }
    //更新适配器
    public void setData(List<SystemData.DataBean.ChildrenBean> childrenBeanList){
        this.childrenBeanList=childrenBeanList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SystemRightItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lv_item2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemRightItemAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(childrenBeanList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return childrenBeanList.size();
    }
}

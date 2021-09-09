package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.SystemData;

import java.util.ArrayList;
import java.util.List;

public class SystemRightAdapter extends RecyclerView.Adapter<SystemRightAdapter.MyRightViewHolder> {
    private Context mContext;
    private List<SystemData.DataBean> dataBeanList = new ArrayList<>();
    private SystemRightItemAdapter systemRightItemAdapter;

    public SystemRightAdapter(Context context,List<SystemData.DataBean> dataBeanList){
        this.mContext=context;
        this.dataBeanList=dataBeanList;
    }

    //更新
    public void update(List<SystemData.DataBean> dataBeanList){
        this.dataBeanList=dataBeanList;
        notifyDataSetChanged();
    }
    static class MyRightViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        RecyclerView recyclerView;

        public MyRightViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.right_text_title);
            recyclerView=itemView.findViewById(R.id.right_rv);
        }
    }
    @NonNull
    @Override
    public MyRightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.right_layout_view,parent,false);
        return new MyRightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRightViewHolder holder, int position) {
        holder.textView.setText(dataBeanList.get(position).getName());
        List<SystemData.DataBean.ChildrenBean> list = dataBeanList.get(position).getChildren();
        systemRightItemAdapter = new SystemRightItemAdapter(mContext,list);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        holder.recyclerView.setAdapter(systemRightItemAdapter);
        systemRightItemAdapter.setData(list);
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }
}

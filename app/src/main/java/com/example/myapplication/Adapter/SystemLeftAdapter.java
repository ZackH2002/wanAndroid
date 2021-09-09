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

public class SystemLeftAdapter extends RecyclerView.Adapter<SystemLeftAdapter.ViewHolder> {

    private Context mContext;
    private List<SystemData.DataBean> dataBeanList = new ArrayList<>();

    public SystemLeftAdapter(Context context, List<SystemData.DataBean> dataBeanList) {
        this.dataBeanList=dataBeanList;
        this.mContext=context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.left_text);
            linearLayout = itemView.findViewById(R.id.left_layout);
        }
    }

    //更新
    public void update(List<SystemData.DataBean>dataBeanList){
        this.dataBeanList=dataBeanList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.left_layout_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(dataBeanList.get(position).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftCheckListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }
    private LeftCheckListener mLeftCheckListener;

    public void setLeftCheckListener(LeftCheckListener leftCheckListener) {
        mLeftCheckListener = leftCheckListener;
    }

    //自定义接口
    public interface LeftCheckListener {
        void onItemClick(int position);
    }
}

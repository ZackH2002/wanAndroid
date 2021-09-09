package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.view.activity.WebActivity;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    public Context context;
    private List<ArticleData.DataBean.DatasBean> mArticleList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textAuthor,textTime,textTitle,textDesc,textTag;
        View articleView;

        public ViewHolder(View view){
            super(view);
            articleView=view;
            textAuthor=(TextView)view.findViewById(R.id.lv_tv_author);
            textTime=(TextView)view.findViewById(R.id.lv_tv_time);
            textTitle=(TextView)view.findViewById(R.id.lv_tv_title);
            textDesc=(TextView)view.findViewById(R.id.lv_tv_desc);
            textTag=(TextView)view.findViewById(R.id.lv_tv_tag);
        }
    }
    public ArticleAdapter(Context context,List<ArticleData.DataBean.DatasBean> articleList){
            mArticleList=articleList;
            this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.articleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ArticleData.DataBean.DatasBean data=mArticleList.get(position);
                Intent intent = new Intent(context, WebActivity.class);
                String url = data.getLink();
                intent.putExtra("url",url);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleData.DataBean.DatasBean dataBean = mArticleList.get(position);
        if (dataBean.getAuthor().isEmpty()){
            holder.textAuthor.setText(dataBean.getShareUser());
        }else {
            holder.textAuthor.setText(dataBean.getAuthor());
        }
        holder.textTime.setText(dataBean.getNiceShareDate());
        holder.textTag.setText(dataBean.getSuperChapterName());
        holder.textTitle.setText(dataBean.getTitle());
        if (dataBean.getDesc().isEmpty()){
            holder.textDesc.setVisibility(View.INVISIBLE);

        }else {
            holder.textDesc.setText(dataBean.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

}

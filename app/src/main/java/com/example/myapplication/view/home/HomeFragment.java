package com.example.myapplication.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.ArticleAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.bean.BannerData;
import com.example.myapplication.presenter.HomePresenter;
import com.example.myapplication.view.activity.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeFragment, OnBannerListener {
    private Banner banner;
    private List<BannerData.DataBean> bannerList = new ArrayList<>();
    private List<ArticleData.DataBean.DatasBean> articleList = new ArrayList<>();
    ArrayList<String>list_path = new ArrayList<>();
    ArrayList<String> list_title = new ArrayList<>();
    ArrayList<String>list_url = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    HomePresenter homePresenter;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        context = this.getActivity();
        homePresenter= new HomePresenter(HomeFragment.this);
        homePresenter.getArticleData();
        homePresenter.getBannerData();
        articleAdapter = new ArticleAdapter(context,articleList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(articleAdapter);
        return view;
    }

    private void initView(View view){
        banner=view.findViewById(R.id.home_banner);
        recyclerView=view.findViewById(R.id.home_recyclerview);
    }

    @Override
    public void setBanner(List<String> list_path, List<String> list_title) {
        banner.setImageLoader(new MyLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setBannerTitles(list_title);
        banner.setImages(list_path).setOnBannerListener(this).start();
    }

    @Override
    public void success(BannerData bannerData) {
        bannerList.addAll(bannerData.getData());
        for (int i = 0; i <bannerList.size() ; i++) {
            list_title.add(bannerList.get(i).getTitle());
            list_path.add(bannerList.get(i).getImagePath());
            list_url.add(bannerList.get(i).getUrl());
            setBanner(list_path,list_title);
        }
    }
    @Override
    public void success1(ArticleData articleData){
        articleList.addAll(articleData.getData().getDatas());
        articleAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        String url = list_url.get(position);
        intent.putExtra("url",url);
        startActivity(intent);

    }


    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
}

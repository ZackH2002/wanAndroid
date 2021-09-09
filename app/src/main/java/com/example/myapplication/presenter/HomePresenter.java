package com.example.myapplication.presenter;

import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.bean.BannerData;
import com.example.myapplication.model.home.HomeModel;
import com.example.myapplication.model.OnNetListener;
import com.example.myapplication.view.home.HomeFragment;


public class HomePresenter {
    private final HomeFragment homeFragment;
    private final HomeModel homeModel;
    public HomePresenter(HomeFragment fragment){
        this.homeFragment=fragment;
        homeModel = new HomeModel();
//        getBannerData();
//        getArticleData();
    }
    public void getBannerData(){
        homeModel.getBannerData(new OnNetListener<BannerData>() {
            @Override
            public void success(BannerData bannerData) {
                homeFragment.success(bannerData);

            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

    public void getArticleData() {
        homeModel.getArticleData(new OnNetListener<ArticleData>() {
            @Override
            public void success(ArticleData articleData) {
                homeFragment.success1(articleData);
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

}

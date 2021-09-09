package com.example.myapplication.model.home;

import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.bean.BannerData;
import com.example.myapplication.model.OnNetListener;

public interface IHomeModel {
     void getBannerData(OnNetListener<BannerData> onNetListener);
     void getArticleData(OnNetListener<ArticleData> onNetListener);
}

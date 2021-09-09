package com.example.myapplication.view.home;

import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.bean.BannerData;

import java.util.List;

public interface IHomeFragment {
    void setBanner(List<String>list_path,List<String>list_title);
    void success(BannerData bannerData);
    void success1(ArticleData articleData);
}

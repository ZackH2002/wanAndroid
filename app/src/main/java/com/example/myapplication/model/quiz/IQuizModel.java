package com.example.myapplication.model.quiz;

import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.model.OnNetListener;

public interface IQuizModel {
    void getArticleData(OnNetListener<ArticleData> onNetListener);
}

package com.example.myapplication.presenter;

import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.model.OnNetListener;
import com.example.myapplication.model.quiz.QuizModel;
import com.example.myapplication.view.quiz.QuizFragment;

public class QuizPresenter {
    private final QuizFragment quizFragment;
    private final QuizModel quizModel;
    public QuizPresenter(QuizFragment quizFragment){
        this.quizFragment=quizFragment;
        this.quizModel= new QuizModel();
    }
    public void getArticleData() {
        quizModel.getArticleData(new OnNetListener<ArticleData>() {
            @Override
            public void success(ArticleData articleData) {
                quizFragment.success(articleData);
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

}

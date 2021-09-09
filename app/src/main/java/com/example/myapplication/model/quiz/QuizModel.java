package com.example.myapplication.model.quiz;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.bean.ArticleData;
import com.example.myapplication.model.HttpUtil;
import com.example.myapplication.model.OnNetListener;
import com.example.myapplication.model.quiz.IQuizModel;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class QuizModel implements IQuizModel {
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getArticleData(OnNetListener<ArticleData> onNetListener) {
        HttpUtil.sendGetOkHttpRequest("https://wanandroid.com/wenda/list/0/json ", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNetListener.failure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.e("tag1",responseData);
                Gson gson = new Gson();
                final ArticleData articleData = gson.fromJson(responseData,ArticleData.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.success(articleData);
                    }
                });
            }
        });
    }
}

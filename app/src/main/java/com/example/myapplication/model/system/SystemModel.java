package com.example.myapplication.model.system;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.bean.SystemData;
import com.example.myapplication.model.HttpUtil;
import com.example.myapplication.model.system.ISystemCallBack;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class SystemModel {
    Handler handler = new Handler(Looper.getMainLooper());
    public void getSystemData(ISystemCallBack systemCallBack){
        HttpUtil.sendGetOkHttpRequest("https://www.wanandroid.com/tree/json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = e.getMessage();
                systemCallBack.onFailed(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.e("System",responseData);
                Gson gson = new Gson();
                final SystemData data = gson.fromJson(responseData,SystemData.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        systemCallBack.onSuccess(data);
                    }
                });
            }
        });
    }

}

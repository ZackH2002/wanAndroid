package com.example.myapplication.model;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static void sendGetOkHttpRequest(String address,Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).get().build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendPostOkHttpRequest(String address,RequestBody requestBody,Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new CookieInterceptor())
                .build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
}

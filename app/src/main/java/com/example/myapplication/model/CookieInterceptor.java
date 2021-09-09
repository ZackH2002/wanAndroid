package com.example.myapplication.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {
    public CookieInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.header("Set-Cookie").isEmpty()) {
            for (String header : response.headers("Set-Cookie")) {
                if (header.contains("JSESSIONID")) {
                    String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
                    Log.e("登录", cookie);
                }
            }

        }
        return response;
    }
}

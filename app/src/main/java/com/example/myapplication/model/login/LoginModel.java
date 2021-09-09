package com.example.myapplication.model.login;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.bean.User;
import com.example.myapplication.bean.UserInformation;
import com.example.myapplication.model.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginModel implements ILoginModel {
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void login(User user, ILoginCallBack loginCallBack) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", user.getUserName())
                .add("password", user.getPassword()).build();
        HttpUtil.sendPostOkHttpRequest("https://www.wanandroid.com/user/login", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = e.getMessage();
                loginCallBack.failed(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String header = response.headers().toString();

                String responseData = response.body().string();

                Log.e("tag2", header);
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String errorCode = jsonObject.getString("errorCode");
                    if (!errorCode.equals("0")) {
                        String errorMsg = jsonObject.getString("errorMsg");
                        Log.e("tag5", errorMsg);
                        loginCallBack.failed(errorMsg);
                    } else {
                        JSONObject data = jsonObject.getJSONObject("data");
                        String coinCount = data.getString("coinCount");
                        String nickname = data.getString("nickname");
                        String icon = data.getString("icon");
                        Log.e("tag3", coinCount);
                        Log.e("tag4", nickname);
                        Log.e("tag5", icon);
                        UserInformation userInformation = new UserInformation();
                        userInformation.setCoinCount(coinCount);
                        userInformation.setNickname(nickname);
                        userInformation.setIcon(icon);
                        userInformation.save();
                        loginCallBack.success();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

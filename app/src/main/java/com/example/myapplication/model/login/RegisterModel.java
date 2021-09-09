package com.example.myapplication.model.login;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.bean.UserInformation;
import com.example.myapplication.model.HttpUtil;
import com.example.myapplication.model.login.ILoginCallBack;
import com.example.myapplication.model.login.IRegisterModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterModel implements IRegisterModel {

    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void create(String name, String password, String rePassword, ILoginCallBack loginCallBack) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", name)
                .add("password", password)
                .add("repassword", rePassword).build();
        HttpUtil.sendPostOkHttpRequest("https://www.wanandroid.com/user/register", requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = e.getMessage();
                loginCallBack.failed(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
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

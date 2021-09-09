package com.example.myapplication.model.login;

import com.example.myapplication.bean.User;

public interface ILoginModel {
    //登录
    void login(User user, ILoginCallBack loginCallBack);

}


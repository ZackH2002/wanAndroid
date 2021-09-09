package com.example.myapplication.model.login;

import com.example.myapplication.model.login.ILoginCallBack;

public interface IRegisterModel {
    //注册
    void create(String name, String password, String rePassword, ILoginCallBack loginCallBack);
}

package com.example.myapplication.presenter;

import com.example.myapplication.model.login.ILoginCallBack;
import com.example.myapplication.model.login.RegisterModel;
import com.example.myapplication.view.login.RegisterFragment;

import java.util.HashMap;

public class RegisterPresenter {
    private final RegisterFragment registerFragment;
    private final RegisterModel registerModel;
    public RegisterPresenter(RegisterFragment registerFragment){
        this.registerFragment=registerFragment;
        this.registerModel=new RegisterModel();
    }
    public void create(String name,String password,String rePassword){
        registerModel.create(name, password, rePassword, new ILoginCallBack() {
            @Override
            public void success() {
                registerFragment.onRegisterSuccess();
            }

            @Override
            public void failed(String s) {
                registerFragment.onRegisterFailed(s);
            }
        });
    }




}

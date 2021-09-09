package com.example.myapplication.presenter;

import com.example.myapplication.model.login.ILoginCallBack;
import com.example.myapplication.model.login.LoginModel;
import com.example.myapplication.bean.User;
import com.example.myapplication.view.login.LoginFragment;

import java.util.HashMap;

public class LoginPresenter {
    private final LoginFragment loginFragment;
    private final LoginModel loginModel;
    public LoginPresenter(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        this.loginModel = new LoginModel();
    }
    public void login(final User user){
        loginModel.login(user, new ILoginCallBack() {
            @Override
            public void success() {
                loginFragment.onLoginSuccess();
            }

            @Override
            public void failed(String s) {
                loginFragment.onLoginFailed(s);
            }
        });
    }



}

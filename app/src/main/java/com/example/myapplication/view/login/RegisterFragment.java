package com.example.myapplication.view.login;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.presenter.RegisterPresenter;
import com.example.myapplication.view.activity.LoginActivity;

public class RegisterFragment extends Fragment implements IRegisterFragment {
    private LoginActivity loginActivity;
    private Button btn_login, btn_register;
    private EditText edit_name, edit_password, edit_rePassword;
    public RegisterPresenter registerPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        loginActivity = (LoginActivity) getActivity();
        registerPresenter = new RegisterPresenter(RegisterFragment.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity.onLoginFragment();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString().trim();
                String password = edit_password.getText().toString().trim();
                String rePassword = edit_rePassword.getText().toString().trim();
                registerPresenter.create(name,password,rePassword);
            }
        });
        return view;
    }

    private void initView(View view) {
        btn_login = view.findViewById(R.id.register_btn_login);
        btn_register = view.findViewById(R.id.register_btn_create);
        edit_name = view.findViewById(R.id.register_edit_name);
        edit_password = view.findViewById(R.id.register_edit_password);
        edit_rePassword = view.findViewById(R.id.register_edit_repassword);
    }

    @Override
    public void onRegisterSuccess() {
        Looper.prepare();
        Toast.makeText(loginActivity,"注册成功",Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    @Override
    public void onRegisterFailed(String s) {
        Looper.prepare();
        Toast.makeText(loginActivity,s,Toast.LENGTH_LONG).show();
        Looper.loop();
    }
}

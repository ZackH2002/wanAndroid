package com.example.myapplication.view.login;

import android.content.Intent;
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
import com.example.myapplication.bean.User;
import com.example.myapplication.presenter.LoginPresenter;
import com.example.myapplication.view.activity.LoginActivity;
import com.example.myapplication.view.activity.MainActivity;

import java.util.HashMap;

public class LoginFragment extends Fragment implements ILoginFragment {
    private Button btn_login,but_create;
    private EditText edit_name,edit_password;
    private LoginActivity loginActivity;
   // private MainActivity mainActivity;
    private LoginPresenter loginPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        loginActivity=(LoginActivity)getActivity();
       // mainActivity = (MainActivity)getActivity();
        loginPresenter=new LoginPresenter(LoginFragment.this);
        but_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity.onRegisterFragment();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edit_name.getText().toString().trim();
                String password = edit_password.getText().toString().trim();
                loginPresenter.login(new User(userName,password));
            }
        });

        return view;
    }

    private void initView(View view){
        btn_login=view.findViewById(R.id.login_btn_login1);
        but_create=view.findViewById(R.id.login_btn_create);
        edit_name=view.findViewById(R.id.login_edit_name);
        edit_password=view.findViewById(R.id.login_edit_password);

    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
       // mainActivity.setMyFragment();
    }

    @Override
    public void onLoginFailed(String s) {
        Looper.prepare();
        Toast.makeText(loginActivity,s,Toast.LENGTH_LONG).show();
        Looper.loop();
    }
}

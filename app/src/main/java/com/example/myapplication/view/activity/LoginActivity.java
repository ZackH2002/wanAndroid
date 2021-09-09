package com.example.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myapplication.Adapter.FraAdapter;
import com.example.myapplication.R;
import com.example.myapplication.view.login.LoginFragment;
import com.example.myapplication.view.login.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private List<String> titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView(){
        viewPager=findViewById(R.id.login_viewpager);
        titles=new ArrayList<>();
        titles.add("login");
        titles.add("register");
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new LoginFragment());
        fragmentList.add(new RegisterFragment());
        FraAdapter fraAdapter = new FraAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(fraAdapter);
        viewPager.setOffscreenPageLimit(1);

    }
    public void onRegisterFragment(){
        viewPager.setCurrentItem(1);
    }
    public void onLoginFragment(){
        viewPager.setCurrentItem(0);
    }
}
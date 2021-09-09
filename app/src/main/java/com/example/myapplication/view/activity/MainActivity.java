package com.example.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.myapplication.Adapter.FraAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.UserInformation;
import com.example.myapplication.view.todo.TodoFragment;
import com.example.myapplication.view.home.HomeFragment;
import com.example.myapplication.view.my.MyFragment;
import com.example.myapplication.view.quiz.QuizFragment;
import com.example.myapplication.view.system.SystemFragment;
import com.google.android.material.tabs.TabLayout;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;
    private List<String> titles;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.initialize(this);
        List<UserInformation> list = LitePal.findAll(UserInformation.class);
        if (list.size()==0){
            UserInformation userInformation = new UserInformation();
            userInformation.setNickname("去登陆");
            userInformation.setCoinCount(" ");
            userInformation.save();
        }

        setContentView(R.layout.activity_main);
        initView();

    }


    public void setMyFragment(){
        viewPager.setCurrentItem(3);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initView() {
        viewPager = findViewById(R.id.main_viewpager);
        tabLayout = findViewById(R.id.main_tab);
        //添加tab的文字
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("问答");
        titles.add("todo");
        titles.add("体系");
        titles.add("我的");
        //添加fragment
        fragmentList = new ArrayList<Fragment>();
        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new QuizFragment());
        fragmentList.add(new TodoFragment());
        fragmentList.add(new SystemFragment());
        fragmentList.add(new MyFragment());
        FraAdapter fraAdapter = new FraAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(fraAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager, false);
        //给tab添加图标
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_quiz);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_todo);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_system);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(R.drawable.ic_my);
    }
}
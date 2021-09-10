package com.example.myapplication.view.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.bean.UserInformation;
import com.example.myapplication.view.activity.LoginActivity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.HashMap;
import java.util.List;

public class MyFragment extends Fragment {
    private ImageView imageView;
    private TextView nameTv, coinCountTv;
    private Button logoutBtn;
    static boolean update = false;
    Context context;

    @Override
    public void onPause() {
        super.onPause();
        update = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (update) {
            updateName();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        context = getContext();
        List<UserInformation> list = LitePal.findAll(UserInformation.class);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 1) {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(1).getId();
                LitePal.delete(UserInformation.class, id);
                Toast.makeText(context, "退出登录", Toast.LENGTH_LONG).show();

                updateName();
            }
        });
        updateName();
        return view;
    }


    private void initView(View view) {
        imageView = view.findViewById(R.id.my_image_icon);
        nameTv = view.findViewById(R.id.my_tv_name);
        coinCountTv = view.findViewById(R.id.my_tv_coinCount);
        logoutBtn = view.findViewById(R.id.my_btn_LogOut);
    }

    private void updateName() {
        List<UserInformation> list = LitePal.findAll(UserInformation.class);
        if (list.size() == 2) {
            String name = list.get(1).getNickname();
            String coinCount = list.get(1).getCoinCount();
            String icon = list.get(1).getIcon();
            nameTv.setText(name);
            coinCountTv.setText(coinCount);
        }else {
            nameTv.setText("去登录");
            coinCountTv.setText(" ");
        }
    }

}

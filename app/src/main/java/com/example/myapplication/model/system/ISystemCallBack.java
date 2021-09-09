package com.example.myapplication.model.system;

import com.example.myapplication.bean.SystemData;

public interface ISystemCallBack {
    void onSuccess(SystemData data);
    void onFailed(String error);
}

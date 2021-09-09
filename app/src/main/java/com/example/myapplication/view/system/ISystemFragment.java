package com.example.myapplication.view.system;

import com.example.myapplication.bean.SystemData;

public interface ISystemFragment {
    void onSystemSuccess(SystemData systemData);
    void onSystemFailed(String error);
}

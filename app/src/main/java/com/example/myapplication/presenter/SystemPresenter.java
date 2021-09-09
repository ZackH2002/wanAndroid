package com.example.myapplication.presenter;

import com.example.myapplication.model.system.ISystemCallBack;
import com.example.myapplication.bean.SystemData;
import com.example.myapplication.model.system.SystemModel;
import com.example.myapplication.view.system.SystemFragment;

public class SystemPresenter {
    private final SystemFragment systemFragment;
    private final SystemModel systemModel;

    public SystemPresenter(SystemFragment systemFragment){
        this.systemFragment=systemFragment;
        this.systemModel= new SystemModel();
    }
    public void getData(){
        systemModel.getSystemData(new ISystemCallBack() {
            @Override
            public void onSuccess(SystemData data) {
                systemFragment.onSystemSuccess(data);
            }

            @Override
            public void onFailed(String error) {
                systemFragment.onSystemFailed(error);
            }
        });
    }
}

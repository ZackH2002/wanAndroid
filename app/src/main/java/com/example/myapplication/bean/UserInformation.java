package com.example.myapplication.bean;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class UserInformation extends LitePalSupport {
    private String coinCount;
    private String nickname;
    private String icon;
    private int id;
    public String getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

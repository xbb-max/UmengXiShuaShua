package com.ming.xposed.xishuashua.bean;


import android.text.TextUtils;

public class RemoteData {
    public String event;
    public String channel;
    public String type;
    public int num;
    public String device;
    public String proxy;

    public boolean isVaild() {
        if (num == 0) {
            return false;
        }
        if (TextUtils.isEmpty(channel)) {
            return false;
        }
        if (TextUtils.isEmpty(device)) {
            return false;
        }
        return true;
    }
}

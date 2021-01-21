package com.mdsoftware.trackingsystemsdk;

import android.content.Context;

public class TSConfOption {

    public Context context;
    public String ts_app;
    public String ts_ext;

    /**
     * 设置app_key
     */
    public String appKey;

    /**
     * 设置模式
     */
    public boolean isDebug;

    public TSConfOption(Context mContext, String mAppKey,  boolean isDebug) {
        this.context = mContext;
        this.appKey = mAppKey;
        this.isDebug = isDebug;
    }

    public TSConfOption(Context mContext, String mAppKey, String ts_ext,  boolean isDebug) {
        this.context = mContext;
        this.appKey = mAppKey;
        this.isDebug = isDebug;
        this.ts_ext = ts_ext;
    }

    public TSConfOption(Context mContext, String mAppKey, String ts_ext, String ts_app, boolean isDebug) {
        this.context = mContext;
        this.appKey = mAppKey;
        this.isDebug = isDebug;
        this.ts_ext = ts_ext;
        this.ts_app = ts_app;
    }

    public String getAppKey() {
        return appKey;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public Context getContext() {
        return this.context;
    }

    public String getTs_app() {
        return ts_app;
    }

    public String getTs_ext() {
        return ts_ext;
    }
}

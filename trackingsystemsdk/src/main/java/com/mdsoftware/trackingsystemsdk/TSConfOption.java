package com.mdsoftware.trackingsystemsdk;

import android.app.Application;
import android.content.Context;

public class TSConfOption {

    public Context context;

    public String ts_app;
    public String ts_ext;

    /**
     * 设置app_key
     */
    public String appKey;

    private TSAnalyticsSDK options;

    /**
     * 设置模式
     */
    public boolean isDebug;

    public TSConfOption(Context mContext, String mAppKey, boolean isDebug) {
        this.context = mContext;
        this.appKey = mAppKey;
        this.isDebug = isDebug;
        options = new TSAnalyticsSDK();
        final Application app = (Application) mContext.getApplicationContext();
        final SwitchBackgroundCallbacks lifecycleCallbacks =
                new SwitchBackgroundCallbacks();
        app.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String mAppKey) {
        this.appKey = mAppKey;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTs_app() {
        return ts_app;
    }

    public void setTs_app(String ts_app) {
        this.ts_app = ts_app;
    }

    public String getTs_ext() {
        return ts_ext;
    }

    public void setTs_ext(String ts_ext) {
        this.ts_ext = ts_ext;
    }
}

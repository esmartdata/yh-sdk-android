package com.mdsoftware.trackingsystemsdk;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TSAnalyticsSDK {

    private static TSAnalyticsSDK instance;

    private static TSConfOption option;

    public static synchronized TSAnalyticsSDK sharedInstance() {
        return instance;
    }

    private TSAnalyticsSDK(TSConfOption option) {
        this.option = option;
    }

    public TSConfOption getOption() {
        return option;
    }

    /**
     * 初始化神策 SDK
     */
    public static void startWithConfigOptions(TSConfOption confOption) {
        if (StringUtils.isEmpty(confOption.getAppKey())) {
            Log.v("ts", "AppKey 不能为空");
            return;
        }
        instance = new TSAnalyticsSDK(confOption);
        final Application app = (Application) confOption.getContext().getApplicationContext();
        final SwitchBackgroundCallbacks lifecycleCallbacks =
                new SwitchBackgroundCallbacks();
        app.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public static void setUserInfo(TSUser user) {
        if (StringUtils.isEmpty(option.getAppKey())) {
            Log.v("ts", "AppKey 不能为空");
            return;
        }
        if (StringUtils.isEmpty(user.getGuid())) {
            Log.v("ts", "guid 不能为空");
            return;
        }
        JSONArray jArray = new JSONArray();
        jArray.add(BodyUtils.getUserInfo(user));
        String str = jArray.toString();
        Log.v("Lifecycle_api", " user: " + str);
        AsyncHttpUtils.Post(BodyUtils.jsonToBase64(BodyUtils.getUserInfo(user)));
    }

    public static void setPageView() {
        AsyncHttpUtils.Post(BodyUtils.jsonToBase64(BodyUtils.getPageViewMap()));
    }

    public static void setStartSession() {
        JSONArray jArray = new JSONArray();
        jArray.add(BodyUtils.getStartSessionMap());
        String str = jArray.toString();
        Log.v("Lifecycle_api", " start: " + str);
        AsyncHttpUtils.Post(BodyUtils.jsonToBase64(BodyUtils.getStartSessionMap()));
    }

    public static void setEndSession() {
        JSONArray jArray = new JSONArray();
        jArray.add(BodyUtils.getEndSessionMap());
        String str = jArray.toString();
        Log.v("Lifecycle_api", " end: " + str);
        AsyncHttpUtils.Post(BodyUtils.jsonToBase64(BodyUtils.getEndSessionMap()));
    }

    public static void event(String event_name, JSONObject event_param) {
        JSONArray jArray = new JSONArray();
        jArray.add(BodyUtils.getEventMap(event_name, event_param));
        String str = jArray.toString();
        Log.v("Lifecycle_api", " event: " + str);
        AsyncHttpUtils.Post(BodyUtils.jsonToBase64(BodyUtils.getEventMap(event_name, event_param)));
    }

    public static void setPageName(String pageName) {
        Constants.PAGE_NAME = pageName;
    }

    public static void setPageTitle(String pageTitle) {
        Constants.PAGE_TITLE = pageTitle;
    }

    public static String getAppKey() {
        return sharedInstance().getOption().getAppKey();
    }

    public static boolean isDebug() {
        return sharedInstance().getOption().isDebug();
    }

    public static Context getContext() {
        return sharedInstance().getOption().getContext();
    }

    public static String getTs_app() {
        return sharedInstance().getOption().getTs_app();
    }

    public static String getTs_ext() {
        return sharedInstance().getOption().getTs_ext();
    }

}
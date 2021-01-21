package com.mdsoftware.trackingsystemsdk;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

public class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

    //记录Activity的总个数
    public static int count = 0;

    private TSAnalyticsSDK sdk = TSAnalyticsSDK.sharedInstance();

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (count == 0) { //后台切换到前台
            Constants.PREV_PATH = "";
            Constants.setPrevSessionId("");
            //初始化事件
            JSONObject eventInfo = new JSONObject();
            //设置事件名称
            eventInfo.put("eventName", "应用启动");//事件名称 必须
            //设置事件参数
            eventInfo.put("eventParam", null);//事件名称 必须
            sdk.event(eventInfo);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Intent intent = activity.getIntent();
        BodyUtils.getValue(intent);
        count++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Constants.SESSION_ID = UUID.randomUUID().toString();
        Constants.setCurrentPath(activity.getClass().getName());
        Constants.setPageTitle(activity.getLocalClassName());
        Constants.START_SESSION_TIME = System.currentTimeMillis() + "";

        sdk.setPageView();

        sdk.setStartSession();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Constants.END_SESSION_TIME = System.currentTimeMillis() + "";

        sdk.setEndSession();

        Constants.setPageQuery("");
        Constants.setPrevSessionId(Constants.getSessionId());
        Constants.setPrevPath(Constants.getCurrentPath());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        count--;
        if (count == 0) { //前台切换到后台
            //初始化事件
            JSONObject eventInfo = new JSONObject();
            //设置事件名称
            eventInfo.put("eventName", "应用进入后台");//事件名称 必须
            //设置事件参数
            eventInfo.put("eventParam", null);//事件名称 必须
            sdk.event(eventInfo);
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.v("Lifecycle_api", activity.getLocalClassName() + " was SaveInstanceState");

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.v("Lifecycle_api", activity.getLocalClassName() + " was Destroyed");
    }
}
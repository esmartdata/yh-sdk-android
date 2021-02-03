package com.example.myapplication;

import android.app.Application;

import com.mdsoftware.trackingsystemsdk.TSAnalyticsSDK;
import com.mdsoftware.trackingsystemsdk.TSConfOption;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TSConfOption confOption = new TSConfOption(this, "qa1609986349356", "", "", false);
        TSAnalyticsSDK.startWithConfigOptions(confOption);
    }
}
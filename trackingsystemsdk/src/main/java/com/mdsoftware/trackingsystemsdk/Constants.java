package com.mdsoftware.trackingsystemsdk;

import android.content.Context;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {

    public static String app_key;
    public static String ts_app;
    public static String ts_ext;
    public static String PREV_PATH = "";
    public static String CURRENT_PATH;
    public static String PAGE_NAME;
    public static String PAGE_TITLE;
    public static String PAGE_URL;
    public static String PAGE_QUERY = "";
    public static String START_SESSION_TIME;
    public static String END_SESSION_TIME;
    public static String PREV_SESSION_ID = "";
    public static boolean DEBUG;
    public static Context context = TSAnalyticsSDK.getContext();
    public static String SESSION_ID = "";
    public static String ELEMENT_POSITION;
    public static String ELEMENT_SELECTOR;

    public enum event {
        pageview, session, event, user
    }

    public static String getCurrentPath() {
        return CURRENT_PATH;
    }

    public static void setCurrentPath(String currentPath) {
        CURRENT_PATH = currentPath;
    }

    public static String getPageQuery() {
        return PAGE_QUERY;
    }

    public static void setPageQuery(String pageQuery) {
        PAGE_QUERY = pageQuery;
    }

    public static String getPageName() {
        return PAGE_NAME;
    }

    public static String getPageTitle() {
        return PAGE_TITLE;
    }

    public static void setPageName(String pageName) {
        PAGE_NAME = pageName;
    }

    public static void setPageTitle(String pageTitle) {
        PAGE_TITLE = pageTitle;
    }

    public static String getGuid() {
        return StringUtils.isEmpty(TSUser.getGuid()) ? DeviceInfo.getInstance().getDeviceId(context) : TSUser.getGuid();
    }

    public static String getPrevSessionId() {
        return PREV_SESSION_ID;
    }

    public static void setPrevSessionId(String prevSessionId) {
        PREV_SESSION_ID = prevSessionId;
    }

    public static String getSessionId() {
        return SESSION_ID;
    }

    public static void setSessionId(String sessionId) {
        SESSION_ID = sessionId;
    }

    public static String getDeviceId() {
        return DeviceInfo.getInstance().getDeviceId(context);
    }

    public static String getDeviceWidth() {
        return DeviceInfo.getInstance().deviceWidth(context);
    }

    public static String getDeviceHeight() {
        return DeviceInfo.getInstance().deviceHeight(context);
    }

    public static String getNetworkType() {
        return DeviceInfo.getInstance().getAPNType(context);
    }

    public static String getOs() {
        return DeviceInfo.getInstance().getOS();
    }

    public static String getBuildVersion() {
        return DeviceInfo.getInstance().getBuildVersion();
    }

    public static String getPrevPath() {
        return PREV_PATH;
    }

    public static void setPrevPath(String prevPath) {
        PREV_PATH = prevPath;
    }

    public static String getBrand() {
        return DeviceInfo.getInstance().getBrand();
    }

    public static String getModel() {
        return DeviceInfo.getInstance().getModel();
    }

    public static String getSessionDuration() {
        double session_duration = Double.parseDouble(END_SESSION_TIME) - Double.parseDouble(START_SESSION_TIME);
        BigDecimal df = new BigDecimal(session_duration / 1000);
        BigDecimal b = df.setScale(2, RoundingMode.HALF_UP);
        return b + "";
    }

    public static String getPageUrl() {
        return StringUtils.isEmpty(PAGE_URL) ? CURRENT_PATH : CURRENT_PATH + PAGE_URL;
    }

    public static String getSessionStartTime() {
        return START_SESSION_TIME;
    }

    public static String getSessionEndTime() {
        return END_SESSION_TIME;
    }
}

package com.mdsoftware.trackingsystemsdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class BodyUtils {

    //1公共参数集
    public static Map getHashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("app_key", TSAnalyticsSDK.getAppKey());
        map.put("debug", TSAnalyticsSDK.isDebug());
        map.put("ts_app", StringUtils.isEmpty(TSAnalyticsSDK.getTs_app()) ? "TS_V1" : TSAnalyticsSDK.getTs_app());
        map.put("ts_ext", StringUtils.isEmpty(TSAnalyticsSDK.getTs_ext()) ? "可扩展" : TSAnalyticsSDK.getTs_ext());
        map.put("platform", "Android");
        map.put("page_name", StringUtils.isEmpty(Constants.getPageName()) ? Constants.getPageTitle() : Constants.getPageName());
        map.put("network_type", Constants.getNetworkType());
        map.put("guid", Constants.getGuid());
        map.put("device_id", Constants.getDeviceId());
        map.put("device_system", "Android");
        map.put("device_system_version", Constants.getBuildVersion());
        map.put("device_brand", Constants.getBrand());
        map.put("device_model", Constants.getModel());
        map.put("device_screenWidth", Constants.getDeviceWidth());
        map.put("device_screenHeight", Constants.getDeviceHeight());
        map.put("sdk_name", "app_track");
        map.put("sdk_type", "Android");
        map.put("sdk_version", "2.0.0");
        map.put("business_channel", "default");
        map.put("timestamp", System.currentTimeMillis() + "");
        return map;
    }

    //2页面参数集
    public static Map getPageViewMap() {
        //获取sign的参数集
        Map<String, Object> map = getHashMap();
        map.put("key", Constants.event.pageview);
        map.put("session_id", Constants.getSessionId());
        map.put("current_path", Constants.CURRENT_PATH);
        map.put("page_id", Constants.CURRENT_PATH);
        map.put("page_title", StringUtils.isEmpty(Constants.getPageTitle()) ? Constants.getPageName() : Constants.getPageTitle());
        map.put("page_url", "");
        map.put("page_query", StringUtils.isEmpty(Constants.getPageQuery()) ? "" : Constants.getPageQuery());
        map.put("prev_path", Constants.PREV_PATH);
        map.put("prev_session_id", StringUtils.isEmpty(Constants.getPrevSessionId()) ? "" : Constants.getPrevSessionId());
        return map;
    }

    //3页面会话开始参数集
    public static Map getStartSessionMap() {
        //获取sign的参数集
        Map<String, Object> map = getHashMap();
        map.put("key", Constants.event.session);
        map.put("session_type", "start");
        map.put("start_session_time", Constants.getSessionStartTime());
        map.put("session_id", Constants.getSessionId());
        map.put("prev_path", Constants.PREV_PATH);
        map.put("current_path", Constants.CURRENT_PATH);
        map.put("page_id", Constants.CURRENT_PATH);
        map.put("page_title", StringUtils.isEmpty(Constants.getPageTitle()) ? Constants.getPageName() : Constants.getPageTitle());
        map.put("page_url", "");
        map.put("page_query", StringUtils.isEmpty(Constants.getPageQuery()) ? "" : Constants.getPageQuery());
        map.put("prev_session_id", StringUtils.isEmpty(Constants.getPrevSessionId()) ? "" : Constants.getPrevSessionId());
        return map;
    }

    //4页面会话结束参数集
    public static Map getEndSessionMap() {
        //获取sign的参数集
        Map<String, Object> map = getHashMap();
        map.put("key", Constants.event.session);
        map.put("session_type", "end");
        map.put("start_session_time", Constants.getSessionStartTime());
        map.put("end_session_time", Constants.getSessionEndTime());
        map.put("session_duration", Constants.getSessionDuration());
        map.put("session_id", Constants.getSessionId());
        map.put("prev_path", Constants.PREV_PATH);
        map.put("current_path", Constants.CURRENT_PATH);
        map.put("page_id", Constants.CURRENT_PATH);
        map.put("page_title", StringUtils.isEmpty(Constants.getPageTitle()) ? Constants.getPageName() : Constants.getPageTitle());
        map.put("page_url", "");
        map.put("page_query", StringUtils.isEmpty(Constants.getPageQuery()) ? "" : Constants.getPageQuery());
        map.put("prev_session_id", StringUtils.isEmpty(Constants.getPrevSessionId()) ? "" : Constants.getPrevSessionId());
        return map;
    }

    //5事件参数集
    public static Map getEventMap(com.alibaba.fastjson.JSONObject jsonObject) {
        String event_name = jsonObject.getString("eventName");
        com.alibaba.fastjson.JSONObject event_param = jsonObject.getJSONObject("eventParam");
        //获取sign的参数集
        Map<String, Object> map = getHashMap();
        map.put("key", Constants.event.event);
        map.put("page_name", StringUtils.isEmpty(Constants.getPageName()) ? Constants.getPageTitle() : Constants.getPageName());
        map.put("event_name", StringUtils.isEmpty(event_name) ? "AppClick" : event_name);
        map.put("event_param", getEventParamValue(event_param));
        map.put("element_position", Constants.ELEMENT_POSITION);
        map.put("element_selector", Constants.ELEMENT_SELECTOR);
        map.put("session_id", Constants.getSessionId());
        map.put("current_path", Constants.CURRENT_PATH);
        map.put("page_id", Constants.CURRENT_PATH);
        map.put("page_title", StringUtils.isEmpty(Constants.getPageTitle()) ? Constants.getPageName() : Constants.getPageTitle());
        map.put("page_url", "");
        map.put("page_query", StringUtils.isEmpty(Constants.getPageQuery()) ? "" : Constants.getPageQuery());
        map.put("prev_path", Constants.PREV_PATH);
        map.put("prev_session_id", StringUtils.isEmpty(Constants.getPrevSessionId()) ? "" : Constants.getPrevSessionId());
        return map;
    }

    //6用户参数集
    public static Map getUserInfo(TSUser user) {
        //获取sign的参数集
        Map<String, Object> map = new HashMap<>();
        map.put("key", Constants.event.user);
        map.put("app_key", TSAnalyticsSDK.getAppKey());
        map.put("guid", Constants.getGuid());
        map.put("device_id", Constants.getDeviceId());
        map.put("real_name", user.getReal_name());
        map.put("nick_name", user.getNick_name());
        map.put("age", user.getAge());
        map.put("birthday", user.getBirthday());
        map.put("gender", user.getGender());
        map.put("account", user.getAccount());
        map.put("country", user.getCountry());
        map.put("province", user.getProvince());
        map.put("city", user.getCity());
        map.put("timestamp", System.currentTimeMillis() + "");
        return map;
    }

    public static String jsonToBase64(Map map) {
        try {
            byte byteXl[] = JSON.toJSONString(map).getBytes("UTF-8");
            String base64EncStr = new String(Base64.encodeBase64(byteXl), "UTF-8");
            String jsonString = URLEncoder.encode(base64EncStr, "UTF-8");
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void getValue(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        Set<String> strings = extras.keySet();
        Map<String, String> map = new HashMap<>();
        for (String keyStr : strings) {
            if (extras.get(keyStr) instanceof Integer) {
                Log.v("tag_2", "intent extras(int) :" + keyStr + "=" + extras.get(keyStr));
            } else if (extras.get(keyStr) instanceof String) {
                Log.v("tag_2", "intent extras(String) :" + keyStr + "=" + extras.get(keyStr));
            } else {
                Log.v("tag_2", "intent extras() :" + keyStr + ":" + extras.get(keyStr));
            }

            map.put(keyStr, extras.get(keyStr) + "");
            buffer.append(keyStr + "=" + extras.get(keyStr) + "&");
        }

        JSONObject json = new JSONObject(map);
        Constants.PAGE_URL = "?" + buffer.toString().substring(0, buffer.toString().length() - 1);

        Constants.PAGE_QUERY = json.toString().equals("{\"profile\":\"UserHandle{0}\"}") ? "" : json.toString();
    }

    public static Object getEventParamValue(com.alibaba.fastjson.JSONObject jsonObject) {
        if (jsonObject == null) {
            return "";
        } else {
            Set<String> strings = jsonObject.keySet();
            Map<String, Object> map = new HashMap<>();
            for (String keyStr : strings) {
                if (jsonObject.get(keyStr) instanceof Integer) {
                    System.out.println("intent extras(int) :" + keyStr + "=" + jsonObject.get(keyStr));
                } else if (jsonObject.get(keyStr) instanceof String) {
                    System.out.println("intent extras(String) :" + keyStr + "=" + jsonObject.get(keyStr));
                } else {
                    System.out.println("intent extras() :" + keyStr + "=" + jsonObject.get(keyStr));
                }
                map.put(keyStr, jsonObject.get(keyStr) + "");
            }
            Object json = JSON.toJSON(map);
            return json;
        }
    }
}

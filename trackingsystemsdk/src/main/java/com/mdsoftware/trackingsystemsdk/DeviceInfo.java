package com.mdsoftware.trackingsystemsdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@SuppressWarnings("deprecation")
public class DeviceInfo {

    private static DeviceInfo instance;

    public DeviceInfo() {
    }

    public static DeviceInfo getInstance() {

        if (instance == null) {
            instance = new DeviceInfo();
        }
        return instance;
    }

    /**
     * 获取设备id
     *
     * @return string
     */
    public String getDeviceId(Context context) {
        String deviceId = getAppProcessId(context);
        if (deviceId == null) {
            return "";
        } else {
            return "TS_" + deviceId;
        }
    }

    /**
     * 获取设备宽度
     *
     * @param context shangxiawen
     * @return string
     */
    public String deviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels + "";
    }

    /**
     * 获取设备高度
     *
     * @param context shangxiawen
     * @return string
     */
    public String deviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels + "";
    }

    /**
     * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
     * 自定义
     *
     * @param context shangxiawen
     * @return string
     */
    public String getAPNType(Context context) {
        //结果返回值
        String netType = "没有网络";
        //获取手机所有连接管理对象
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //NetworkInfo对象为空 则代表没有网络
        if (networkInfo == null) {
            return netType;
        }
        //否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            //WIFI
            netType = "wifi";
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //3G   联通的3G为UMTS或HSDPA 电信的3G为EVDO
            if (nSubType == TelephonyManager.NETWORK_TYPE_LTE
                    && !telephonyManager.isNetworkRoaming()) {
                netType = "4g";
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                    || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    && !telephonyManager.isNetworkRoaming()) {
                netType = "3g";
                //2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
                    || nSubType == TelephonyManager.NETWORK_TYPE_EDGE
                    || nSubType == TelephonyManager.NETWORK_TYPE_CDMA
                    && !telephonyManager.isNetworkRoaming()) {
                netType = "2g";
            } else {
                netType = "2g";
            }
        }
        return netType;
    }

    /**
     * 获取手机品牌
     *
     * @return string
     */
    public String getBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return string
     */
    public String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取操作系统
     *
     * @return string
     */
    public String getOS() {
        return "Android " + android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机Android 版本
     *
     * @return string
     */
    public String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机Android 版本
     *
     * @return string
     */
    public String getBuildVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取到版本号";
        }
    }

    /**
     * 获取手机Android_ID
     *
     * @return string
     */
    public static String getAppProcessId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
    }
}

package com.mdsoftware.trackingsystemsdk;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncHttpUtils {

    static String testUrl = "https://tsapiqa.escase.cn/i";

    static String formalUrl = "https://tsapi.escase.cn/collection/i";

    //获取当前设备的CPU数
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心池大小设为CPU数加1
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //设置线程池的最大大小
    private static final int MAX_POOL_SIZE = 2 * CPU_COUNT + 1;
//    private static final int MAX_POOL_SIZE = 2;
    //存活时间
    private static final long KEEP_ALIVE = 5L;

    //创建线程池对象
    public static final Executor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAX_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public static void Post(final String Json) {

        //创建一个新的请求任务
        Runnable requestRunnable = new Runnable() {
            @Override
            public void run() {
                post(Json);
            }
        };
        threadPoolExecutor.execute(requestRunnable);
    }

    public static String post(String Json) {
        StringBuilder sb = new StringBuilder();

        HttpURLConnection urlConnection = null;

        String host = TSAnalyticsSDK.getAppKey().contains("qa") ? testUrl : formalUrl;

        try {
            URL url = new URL(host);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
            urlConnection.connect();

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            try {
                jsonParam.put("data", Json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonParam.toString());
            out.close();

            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

//                System.out.println("" + sb.toString());
                Log.v("tag_2", sb.toString());
                return sb.toString();
            } else {
//                System.out.println(urlConnection.getResponseMessage());
                return urlConnection.getResponseMessage();
            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "";
    }
}

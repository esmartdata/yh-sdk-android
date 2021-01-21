package com.mdsoftware.trackingsystemsdk;

public class HttpHelper {

    static String testUrl = "https://tsapiqa.escase.cn/i";

    static String formalUrl = "https://tsapi.escase.cn/collection/i";

//    public static void sendJsonPost(String Json) {
//        //创建OKHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();
//        FormBody body = new FormBody.Builder()
//                .add("data", Json).build();
//        final Request request = new Request.Builder().url(Constants.app_key.contains("qa") ? testUrl : formalUrl).post(body).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.v("tag_2", response.body().string());
//            }
//        });
//    }

}

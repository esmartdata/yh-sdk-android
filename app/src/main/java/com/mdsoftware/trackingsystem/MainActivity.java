package com.mdsoftware.trackingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mdsoftware.trackingsystemsdk.StringUtils;
import com.mdsoftware.trackingsystemsdk.TSAnalyticsSDK;
import com.mdsoftware.trackingsystemsdk.TSUser;

import static com.mdsoftware.trackingsystemsdk.TSAnalyticsSDK.event;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5, et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et_1);
        et2 = findViewById(R.id.et_2);
        et3 = findViewById(R.id.et_3);
        et4 = findViewById(R.id.et_4);
        et5 = findViewById(R.id.et_5);
        et6 = findViewById(R.id.et_6);

        findViewById(R.id.tv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TSUser user = new TSUser();
                user.setGuid("123456");
                TSAnalyticsSDK.sharedInstance().setUserInfo(user);

                Toast.makeText(MainActivity.this,"你已经点击了登陆",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.tv_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Start.class);
                if (!StringUtils.isEmpty(et1.getText().toString()))
                    intent.putExtra(et1.getText().toString(), et3.getText().toString());
                if (!StringUtils.isEmpty(et1.getText().toString()))
                    intent.putExtra(et2.getText().toString(), et4.getText().toString());
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
                jsonObject.put("phone", "1861087138x");
                jsonObject.put("verificationCode", "HELLO");
                event("获取验证码", jsonObject);

                Toast.makeText(MainActivity.this,"你已经点击了手动触发按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

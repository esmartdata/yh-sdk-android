package com.mdsoftware.trackingsystem;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mdsoftware.trackingsystemsdk.Constants;
import com.mdsoftware.trackingsystemsdk.StringUtils;
import com.mdsoftware.trackingsystemsdk.TSAnalyticsSDK;
import com.mdsoftware.trackingsystemsdk.TSUser;

public class MainActivity extends AppCompatActivity {

    int Code_PERMISSION = 0;

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
                user.setGuid("");
                TSAnalyticsSDK.sharedInstance().setUserInfo(user);
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
//                TSAnalyticsSDK.sharedInstance()
//                        .event(StringUtils.isEmpty(et1.getText().toString()) ? "" : et1.getText().toString(),
//                                StringUtils.isEmpty(et1.getText().toString()) ? "" : et1.getText().toString());
                TSAnalyticsSDK.sharedInstance()
                        .event("获取验证码",
                                "{\n" +
                                        "   \"phone\": \"1861087138x\",\n" +
                                        "   \"verificationCode\": \"HELLO\"\n" +
                                        "}");
            }
        });
    }

    /**
     * 基本权限管理
     */
    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {  //1. 检查是否已经有该权限

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限
                new AlertDialog.Builder(this)
                        .setTitle("友情提醒")
                        .setMessage("亲，没有定位权限我会崩溃，请把定位权限赐给我吧！")
                        .setPositiveButton("给你", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                // 用户同意 ，再次申请
                                ActivityCompat.requestPermissions(MainActivity.this, BASIC_PERMISSIONS, Code_PERMISSION);
                            }
                        })
                        .setNegativeButton("不给", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                // 用户拒绝 ，如果APP必须有权限否则崩溃，那就继续重复询问弹框~~
                            }
                        }).show();
            } else {
                //2. 权限没有开启，请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}, Code_PERMISSION);
            }

        } else {
            //3. 权限已开，处理逻辑
        }
    }

    //4. 接收申请成功或者失败回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Code_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被用户同意,做相应的事情

            } else {
                //权限被用户拒绝，做相应的事情
                requestPermission();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

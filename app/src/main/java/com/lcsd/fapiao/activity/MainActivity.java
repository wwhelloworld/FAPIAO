package com.lcsd.fapiao.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.lcsd.fapiao.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout ll_to_scan, ll_to_input, ll_to_chou, ll_to_dui;
    private static final int CHECK_PERMISSION = 8002;
    private final static int REQ_CODE = 1029;
    //抽奖弹窗

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        ll_to_scan = findViewById(R.id.ll_to_scan);
        ll_to_input = findViewById(R.id.ll_to_input);
        ll_to_chou = findViewById(R.id.ll_to_chou);
        ll_to_dui = findViewById(R.id.ll_to_dui);
        ll_to_scan.setOnClickListener(this);
        ll_to_input.setOnClickListener(this);
        ll_to_chou.setOnClickListener(this);
        ll_to_dui.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_to_scan:
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(context, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED
                        ) {
                    startActivityForResult(new Intent(context, CaptureActivity.class), REQ_CODE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA, Manifest.permission.VIBRATE}, CHECK_PERMISSION);
                }
                break;

            case R.id.ll_to_input:
                startActivity(new Intent(context, InputActivity.class));
                break;
            case R.id.ll_to_chou:
                startActivity(new Intent(context, CJHistory.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (data != null) {
                String result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
                if (result != null) {
                    startActivity(new Intent(context, InputActivity.class).putExtra("result", result));
                }

            }
        }
    }

    //权限申请回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CHECK_PERMISSION
                && grantResults.length == 4
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED
                && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
            startActivityForResult(new Intent(context, CaptureActivity.class), REQ_CODE);
        }
    }
}

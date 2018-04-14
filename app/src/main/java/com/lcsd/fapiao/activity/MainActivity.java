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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.activity.CaptureActivity;
import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.CJDialog;
import com.lcsd.fapiao.dialog.CalendarDialog;
import com.lcsd.fapiao.http.MyApplication;
import com.lcsd.fapiao.utils.JacksonUtils;
import com.lcsd.fapiao.view.LuckPanLayout;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private TextView tv_scan, tv_input, tv_to_chou, tv_to_dui, tv_description;
    //抽奖弹窗
    private CJDialog cjDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        tv_scan = findViewById(R.id.tv_to_scan);
        tv_input = findViewById(R.id.tv_to_input);
        tv_to_chou = findViewById(R.id.tv_to_chou);
        tv_to_dui = findViewById(R.id.tv_to_dui);
        tv_description = findViewById(R.id.tv_description);
        tv_scan.setOnClickListener(this);
        tv_input.setOnClickListener(this);
        tv_to_chou.setOnClickListener(this);
        tv_to_dui.setOnClickListener(this);
        tv_description.setOnClickListener(this);
        //dialog宽高
        cjDialog = new CJDialog(context);
        cjDialog.setCancelable(false);
        Window window = cjDialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.76); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_to_scan:
                cjDialog.show();
                ImageView go = cjDialog.findViewById(R.id.go);
                final LuckPanLayout luckPanLayout = cjDialog.findViewById(R.id.luckpan_layout);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        luckPanLayout.rotate(-1, 100);
                    }
                });
                break;
            case R.id.tv_to_input:
                startActivity(new Intent(context, InputActivity.class));
                break;
            case R.id.tv_to_chou:
                startActivity(new Intent(context, CJHistory.class));
                break;
        }
    }
}

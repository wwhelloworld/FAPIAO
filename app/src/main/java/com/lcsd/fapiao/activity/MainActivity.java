package com.lcsd.fapiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.lcsd.fapiao.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout ll_to_scan, ll_to_input, ll_to_chou, ll_to_dui;
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


                break;
            case R.id.ll_to_input:
                startActivity(new Intent(context, InputActivity.class));
                break;
            case R.id.ll_to_chou:
                startActivity(new Intent(context, CJHistory.class));
                break;
        }
    }
}

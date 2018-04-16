package com.lcsd.fapiao.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.CJDialog;
import com.lcsd.fapiao.listener.RotateListener;
import com.lcsd.fapiao.view.WheelSurfView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private LinearLayout ll_to_scan, ll_to_input, ll_to_chou, ll_to_dui;
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
                //dialog宽高
                cjDialog = new CJDialog(context);
                cjDialog.setCancelable(false);
                Window window = cjDialog.getWindow();
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
                p.height = (int) (d.getHeight() * 0.70); // 改变的是dialog框在屏幕中的位置而不是大小
                p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.65
                window.setAttributes(p);
                cjDialog.show();

                //获取第一个视图
                final WheelSurfView wheelSurfView = cjDialog.findViewById(R.id.wheelSurfView1);
                final ImageView iv_cancle = cjDialog.findViewById(R.id.iv_dialog_cancel);
                final TextView tv_input = cjDialog.findViewById(R.id.tv_input_info);
                tv_input.setBackgroundResource(R.drawable.content_moren);
                iv_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cjDialog.cancel();
                        Log.d("TAG====","dialog销毁了");
                    }
                });
                //添加滚动监听
                wheelSurfView.setRotateListener(new RotateListener() {
                    @Override
                    public void rotateEnd(int position, String des) {
                        //获得结果取消点击监听
                        iv_cancle.setVisibility(View.VISIBLE);
                        wheelSurfView.setRotateListener(null);
                        tv_input.setBackgroundResource(R.drawable.content_commit);
                        tv_input.setClickable(true);
                        tv_input.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cjDialog.cancel();//销毁dialog
                                startActivity(new Intent(context, InformationActivity.class));
                            }
                        });
                        Toast.makeText(MainActivity.this, "恭喜您获得了:" +des, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void rotating(ValueAnimator valueAnimator) {
                        iv_cancle.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void rotateBefore(ImageView goImg) {
                        //模拟位置
                        int position = new Random().nextInt(7) + 1;
                        wheelSurfView.startRotate(position);
                        tv_input.setClickable(false);

                    }
                });

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

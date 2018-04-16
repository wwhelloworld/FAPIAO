package com.lcsd.fapiao.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cretin.www.wheelsruflibrary.listener.RotateListener;
import com.cretin.www.wheelsruflibrary.view.WheelSurfView;
import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.CJDialog;

import java.util.Random;

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
        tv_to_chou.setOnClickListener(this);        tv_to_dui.setOnClickListener(this);
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
                //获取第一个视图
                final WheelSurfView wheelSurfView =cjDialog. findViewById(R.id.wheelSurfView);
                //添加滚动监听
                wheelSurfView.setRotateListener(new RotateListener() {
                    @Override
                    public void rotateEnd(int position, String des) {
                        Toast.makeText(MainActivity.this, "结束了 " + position + "   " + des, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void rotating(ValueAnimator valueAnimator) {

                    }

                    @Override
                    public void rotateBefore(ImageView goImg) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("温馨提示");
                        builder.setMessage("确定要花费100积分抽奖？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //模拟位置
                                int position = new Random().nextInt(7) + 1;
                                wheelSurfView.startRotate(position);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();

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

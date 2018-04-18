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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.google.zxing.activity.CaptureActivity;
import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.CalendarDialog;
import com.lcsd.fapiao.entity.JYInfo;
import com.lcsd.fapiao.http.MyApplication;
import com.lcsd.fapiao.utils.JacksonUtils;
import com.lcsd.fapiao.utils.Mytools;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private ImageView iv_code, iv_rili, iv_scan, iv_check_code;
    private TextView tv_commit, tv_calendar;
    private EditText et_yzm, et_fpdm, et_fphm, et_jym;
    private static final int CHECK_PERMISSION = 8001;
    private final static int REQ_CODE = 1028;
    private CalendarDialog calendarDialog;
    private String type;
    private String check_url = "https://www.51fapiao.cn:20890/webServer/webapi/modFpcy";
    private String checkyzm_url = "https://www.51fapiao.cn:20890/webServer/webapi/fpcx/checkYzm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        context = this;
        initView();
    }

    private void initView() {
        findViewById(R.id.ll_input_back).setOnClickListener(this);
        //二维码扫描
        iv_scan = findViewById(R.id.iv_scan);
        iv_scan.setOnClickListener(this);
        //验证码点击
        iv_code = findViewById(R.id.iv_code);
        iv_code.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_code.setOnClickListener(this);
        //日期选择
        iv_rili = findViewById(R.id.iv_rili);
        iv_rili.setOnClickListener(this);
        //日期填入
        tv_calendar = findViewById(R.id.tv_calendar);
        //dialog宽高
        calendarDialog = new CalendarDialog(context);
        Window window = calendarDialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.66); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
        //底部查询提交
        tv_commit = findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(this);
        //发票代码输入框
        et_fpdm = findViewById(R.id.et_fpdm);
        //发票号码输入框
        et_fphm = findViewById(R.id.et_fphm);
        //发票校验码
        et_jym = findViewById(R.id.et_jym);
        //验证码输入框
        iv_check_code = findViewById(R.id.iv_check_code);
        et_yzm = findViewById(R.id.et_yzm);
        et_yzm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 4) {
                    check_code();
                } else {
                    if (editable.length() > 0 && editable.length() < 4) {
                        iv_check_code.setBackgroundResource(R.drawable.img_chahao);
                        iv_check_code.setVisibility(View.VISIBLE);
                    } else {
                        iv_check_code.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        if (getIntent().getExtras() != null) {
            String result = getIntent().getStringExtra("result");
            StringSplit(result);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_input_back:
                finish();
                break;
            case R.id.iv_scan:
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
            case R.id.iv_code:
                int tmp = (int) (Math.random() * 1000);
                String code_url = "https://www.51fapiao.cn:8060/webServer/gencode?tmp=" + tmp;
                Glide.with(context).load(code_url).into(iv_code);
                Log.d("图片地址====", code_url);
                break;
            case R.id.iv_rili:
                calendarDialog.show();
                calendarDialog.getView(tv_calendar);
                break;
            case R.id.tv_commit:
                try {
                    if (et_fpdm.getText().equals("")) {
                        Toast.makeText(context, "发票代码错误！", Toast.LENGTH_SHORT).show();
                    } else if (et_fphm.getText().equals("")) {
                        Toast.makeText(context, "发票号码错误！", Toast.LENGTH_SHORT).show();
                    } else if (tv_calendar.getText().equals("")) {
                        Toast.makeText(context, "发票日期错误", Toast.LENGTH_SHORT).show();

                    } else if (et_jym.getText().equals("")) {
                        Toast.makeText(context, "校验码错误！", Toast.LENGTH_SHORT).show();

                    } else if (et_yzm.getText().equals("")) {
                        Toast.makeText(context, "验证码错误！", Toast.LENGTH_SHORT).show();

                    } else {
                        requestData();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void check_code() {
        Map<String, String> map = new HashMap<>();
        map.put("yzm", et_yzm.getText().toString());
        MyApplication.getInstance().getMyOkHttp().post()
                .params(map)
                .url(checkyzm_url)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        if (response != null) {
                            Log.d("返回的验证码校验123====", response);
                            if (response.equals("success")) {
                                iv_check_code.setBackgroundResource(R.drawable.img_duihao);
                                iv_check_code.setVisibility(View.VISIBLE);
                            } else {
                                if (et_yzm.getText() != null && et_yzm.getText().length() > 0) {
                                    iv_check_code.setBackgroundResource(R.drawable.img_chahao);
                                    iv_check_code.setVisibility(View.VISIBLE);
                                } else {
                                    iv_check_code.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

    //请求校验发票数据
    private void requestData() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("invoiceType", type);//类型
        map.put("invoiceCode", et_fpdm.getText().toString());//发票代码
        map.put("invoiceNo", et_fphm.getText().toString());//发票号码
        map.put("invoiceDate", tv_calendar.getText().toString());//发票日期Î
        map.put("invoiceAmount", "");//发票金额
        map.put("checkCode", et_jym.getText().toString());//校验码
        map.put("validCode", et_yzm.getText().toString());//验证码
        String requestJson = JacksonUtils.getJsonFromMap(map);
        MyApplication.getInstance().getMyOkHttp().post().url(check_url).jsonParams(requestJson).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                if (response != null) {
                    Log.d("返回的校验数据====", response);
                    JYInfo info = JSON.parseObject(response, JYInfo.class);
                    if (info.getMsg().equals("success")) {
                        Toast.makeText(context, info.getData().getResultTip(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", info);
                        intent.setClass(context, DetailActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }


                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        et_fpdm.getText().clear();
        et_fphm.getText().clear();
        et_jym.getText().clear();
        et_yzm.getText().clear();
        tv_calendar.setText("");
        if (requestCode == REQ_CODE) {
            if (data != null) {
                String result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
                TextView textView = findViewById(R.id.tv_content);
                //textView.setText(result);//打印一下二维码信息
                StringSplit(result);
                Log.d("获取的扫描返回值====", result);

            }
        }
    }

    public void StringSplit(String str) {
        String[] sourceStrArray = str.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            Log.d("截取的字符第" + i + "个", sourceStrArray[i]);
        }
        type = sourceStrArray[1];
        et_fpdm.setText(sourceStrArray[2]);//代码
        et_fphm.setText(sourceStrArray[3]);//号码
        if (sourceStrArray[5] != null && !sourceStrArray[5].equals("")) {
            tv_calendar.setText(Mytools.strToDateFormat(sourceStrArray[5]));//日期
        }
        if (sourceStrArray[6] != null && !sourceStrArray[6].equals("")) {
            et_jym.setText(Mytools.strbacksubstring(6, sourceStrArray[6]));//校验码
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

package com.lcsd.fapiao.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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
import com.google.gson.JsonObject;
import com.google.zxing.activity.CaptureActivity;
import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.CalendarDialog;
import com.lcsd.fapiao.dialog.LoadingDialogUtils;
import com.lcsd.fapiao.entity.JYInfo;
import com.lcsd.fapiao.entity.YZM_Info;
import com.lcsd.fapiao.http.MyApplication;
import com.lcsd.fapiao.utils.JacksonUtils;
import com.lcsd.fapiao.utils.Mytools;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    public static InputActivity inputActivity;
    private Context context;
    private ImageView iv_code, iv_rili, iv_scan;
    private TextView tv_commit, tv_calendar, tv_yzm_tips;
    private EditText et_yzm, et_fpdm, et_fphm, et_jym, et_fp_amount;
    private static final int CHECK_PERMISSION = 8001;
    private final static int REQ_CODE = 1028;
    private CalendarDialog calendarDialog;
    private Dialog loading_dialog;

    private String url1 = "https://www.fapiao.com/dzfp-web/fpcycr.do?method=getTaxOffYzm";
    private String url2 = "https://www.fapiao.com/dzfp-web/fpcycr.do?method=fpcyQueryTaxOff";
    private String yzm, jmmy, yzmType, yzmSj, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        inputActivity = this;
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
        et_fpdm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 12 || editable.length() == 10) {
                    //输入后判断的发票类型
                    String type1 = Mytools.getfplx(editable.toString());
                    if (type1 != null) {
                        if (type1.equals("01") || type1.equals("02") || type1.equals("03")) {
                            findViewById(R.id.ll_jym).setVisibility(View.GONE);
                            findViewById(R.id.ll_fpje).setVisibility(View.VISIBLE);

                        } else if (type1.equals("04") || type1.equals("10") || type1.equals("11")) {
                            findViewById(R.id.ll_fpje).setVisibility(View.GONE);
                            findViewById(R.id.ll_jym).setVisibility(View.VISIBLE);

                        } else {
                            findViewById(R.id.ll_fpje).setVisibility(View.VISIBLE);
                            findViewById(R.id.ll_jym).setVisibility(View.GONE);
                        }
                    } else {
                        findViewById(R.id.ll_fpje).setVisibility(View.GONE);
                        findViewById(R.id.ll_jym).setVisibility(View.VISIBLE);
                    }

                } else {
                    return;
                }
            }
        });


        //发票号码输入框
        et_fphm = findViewById(R.id.et_fphm);
        //发票金额输入框
        et_fp_amount = findViewById(R.id.et_fp_amount);
        //发票校验码
        et_jym = findViewById(R.id.et_jym);
        //验证码输入框
//        iv_check_code = findViewById(R.id.iv_check_code);
        et_yzm = findViewById(R.id.et_yzm);
//        et_yzm.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editable.length() == 4) {
//                    check_code();
//                } else {
//                    if (editable.length() > 0 && editable.length() < 4) {
//                        iv_check_code.setBackgroundResource(R.drawable.img_chahao);
//                        iv_check_code.setVisibility(View.VISIBLE);
//                    } else {
//                        iv_check_code.setVisibility(View.INVISIBLE);
//                    }
//                }
//
//            }
//        });
        //发票输入提示
        tv_yzm_tips = findViewById(R.id.tv_yzm_tips);

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

                if (!et_fpdm.getText().toString().equals("") && !et_fphm.getText().toString().equals("")) {
                    //dialog显示
                    loading_dialog = LoadingDialogUtils.createLoadingDialog(InputActivity.this, "加载中...");
                    check_code();
                } else {
                    Toast.makeText(context, "请填写完成发票代码和号码信息！", Toast.LENGTH_SHORT).show();
                }
                //重新刷新清除输入栏

                if (et_yzm.getText().toString() != null) {
                    et_yzm.getText().clear();
                }

                break;
            case R.id.iv_rili:
                calendarDialog.show();
                calendarDialog.getView(tv_calendar);
                break;
            case R.id.tv_commit:
                if (et_fpdm.getText().toString().equals("")) {
                    Toast.makeText(context, "发票代码错误！", Toast.LENGTH_SHORT).show();
                } else if (et_fphm.getText().toString().equals("")) {
                    Toast.makeText(context, "发票号码错误！", Toast.LENGTH_SHORT).show();
                } else if (tv_calendar.getText().toString().equals("")) {
                    Toast.makeText(context, "发票日期错误", Toast.LENGTH_SHORT).show();

                } else if (et_jym.getText().toString().equals("")) {
                    Toast.makeText(context, "校验码错误！", Toast.LENGTH_SHORT).show();

                } else if (et_yzm.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "验证码错误！", Toast.LENGTH_SHORT).show();

                } else {
                    //dialog显示
                    loading_dialog = LoadingDialogUtils.createLoadingDialog(InputActivity.this, "加载中...");
                    requestData();
                }
                break;
        }
    }

    private void check_code() {
        Map<String, String> map = new HashMap<>();
        map.put("fpdm", et_fpdm.getText().toString());
        map.put("fphm", et_fphm.getText().toString());
        MyApplication.getInstance().getMyOkHttp().post()
                .params(map)
                .url(url1)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        if (response != null) {
                            Log.d("返回的验证码校验123====", response);
                            try {
                                JSONObject object = new JSONObject(response);
                                if (object.getJSONObject("jsonMap").get("DATA") != null) {
                                    LoadingDialogUtils.closeDialog(loading_dialog);
                                    YZM_Info info = JSON.parseObject(response, YZM_Info.class);
                                    if (info.getJsonMap().getDATA() != null) {
                                        jmmy = info.getJsonMap().getDATA().getJmmy();
                                        yzmType = info.getJsonMap().getDATA().getYzmType();
                                        yzmSj = info.getJsonMap().getDATA().getYzmSj();
                                        message = info.getJsonMap().getDATA().getMessage();
                                        yzm = info.getJsonMap().getDATA().getYzm();

                                    Log.d("打印值：", "jmmy=" + info.getJsonMap().getDATA().getJmmy() +
                                            "yzmType=" + info.getJsonMap().getDATA().getYzmSj() + "yzmSj" + info.getJsonMap().getDATA().getYzmType()
                                            + "message" + info.getJsonMap().getDATA().getMessage());
                                    }
                                    //加载验证码图片
                                    Bitmap bitmap = Mytools.stringtoBitmap(yzm);
                                    iv_code.setImageBitmap(bitmap);//显示图片
                                    tv_yzm_tips.setText(info.getJsonMap().getDATA().getMessage());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                LoadingDialogUtils.closeDialog(loading_dialog);
                            }


                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        if (error_msg!=null){
                            LoadingDialogUtils.closeDialog(loading_dialog);
                        }

                    }
                });
    }

    //请求校验发票数据
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("fpdm", et_fpdm.getText().toString());//发票代码
        map.put("fphm", et_fphm.getText().toString());//发票号码
        map.put("kprq", tv_calendar.getText().toString());//发票日期
        map.put("jym", et_jym.getText().toString());//校验码
        map.put("yzm", et_yzm.getText().toString());//输入验证码
        map.put("jmmy", jmmy);//验证码
        map.put("yzmSj", yzmSj);//验证码时间
        MyApplication.getInstance().getMyOkHttp().post().url(url2).params(map).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                if (response != null) {
                    Log.d("返回的校验数据====", response.toString() + "这个值");
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getJSONObject("jsonMap").getString("code").equals("0000")) {
                            LoadingDialogUtils.closeDialog(loading_dialog);
                            JYInfo info = JSON.parseObject(response, JYInfo.class);
                            Toast.makeText(context, info.getJsonMap().getCodeMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("list", info);
                            intent.setClass(context, DetailActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            Toast.makeText(context, object.getJSONObject("jsonMap").getString("codeMsg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        LoadingDialogUtils.closeDialog(loading_dialog);
                    }


                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                if (error_msg!=null){
                    LoadingDialogUtils.closeDialog(loading_dialog);
                }
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
        iv_code.setBackgroundResource(R.drawable.code);
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
//        type = sourceStrArray[1];
        et_fpdm.setText(sourceStrArray[2]);//代码
        et_fphm.setText(sourceStrArray[3]);//号码
        if (sourceStrArray[5] != null && !sourceStrArray[5].equals("")) {
            tv_calendar.setText(Mytools.strToDateFormat1(sourceStrArray[5]));//日期
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

    @Override
    protected void onPause() {
        super.onPause();
        tv_yzm_tips.setText("");
    }
}

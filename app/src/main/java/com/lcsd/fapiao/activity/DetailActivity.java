package com.lcsd.fapiao.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.CJDialog;
import com.lcsd.fapiao.entity.JYInfo;
import com.lcsd.fapiao.listener.RotateListener;
import com.lcsd.fapiao.sql.DBUtil;
import com.lcsd.fapiao.sql.HistoryContent;
import com.lcsd.fapiao.utils.Mytools;
import com.lcsd.fapiao.view.WheelSurfView;

import java.util.Random;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static DetailActivity detailActivity;
    private Context context;
    private JYInfo jyInfo;
    private TextView tv_buyer_name, tv_buyer_TaxNo, tv_buyer_AddressPhone, tv_buyer_Account, tv_saler_Name,
            tv_total_Amount, tv_tax_Amount, tv_invoice_Amount, tv_tax_rate, tv_goods_Name, tv_invoice_Date,
            tv_invoice_Code, tv_invoice_No, tv_choujiang, tv_invoice_name;
    //抽奖历史
    private DBUtil mDBUtil;
    private HistoryContent historyContent;
    //抽奖弹窗
    private CJDialog cjDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailActivity = this;
        context = this;
        initView();

    }

    private void initView() {
        findViewById(R.id.ll_detail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jyInfo = (JYInfo) getIntent().getSerializableExtra("list");
        if (jyInfo != null) {
            Log.d("接受的集合====", jyInfo.toString());
        }
        //发票类型名字
        tv_invoice_name = findViewById(R.id.tv_invoice_name);
//        tv_invoice_name.setText(setfpname(jyInfo.getData().getFP_TYPE()));
        tv_invoice_name.setText(jyInfo.getJsonMap().getFP_TYPE());
        //购买方
        tv_buyer_name = findViewById(R.id.tv_buyer_name);
        tv_buyer_name.setText(jyInfo.getJsonMap().getGMF_MC());
        //税号
        tv_buyer_TaxNo = findViewById(R.id.tv_buyer_TaxNo);
        tv_buyer_TaxNo.setText(jyInfo.getJsonMap().getGMF_NSRSBH());
        //地址电话
        tv_buyer_AddressPhone = findViewById(R.id.tv_buyer_AddressPhone);
        tv_buyer_AddressPhone.setText(jyInfo.getJsonMap().getGMF_DZDH());
        //开户行 开户行账号
        tv_buyer_Account = findViewById(R.id.tv_buyer_Account);
        tv_buyer_Account.setText(jyInfo.getJsonMap().getGMF_YHZH());
        //========================================================
        //销售方
        tv_saler_Name = findViewById(R.id.tv_saler_Name);
        tv_saler_Name.setText(jyInfo.getJsonMap().getXSF_MC());
        //价税合计
        tv_total_Amount = findViewById(R.id.tv_total_Amount);
        tv_total_Amount.setText("￥" + jyInfo.getJsonMap().getJSHJ());
        if (jyInfo.getJsonMap().getFPMX_LIST()!=null&jyInfo.getJsonMap().getFPMX_LIST().size()>0) {
            //税额
            tv_tax_Amount = findViewById(R.id.tv_tax_Amount);
            tv_tax_Amount.setText("￥" + jyInfo.getJsonMap().getFPMX_LIST().get(0).getSE());
            //金额
            tv_invoice_Amount = findViewById(R.id.tv_invoice_Amount);
            tv_invoice_Amount.setText("￥" + jyInfo.getJsonMap().getFPMX_LIST().get(0).getXMDJ());
            //税率
            tv_tax_rate = findViewById(R.id.tv_tax_rate);
            tv_tax_rate.setText(jyInfo.getJsonMap().getFPMX_LIST().get(0).getSL());
            //商品名称
            tv_goods_Name = findViewById(R.id.tv_goods_Name);
            tv_goods_Name.setText(jyInfo.getJsonMap().getFPMX_LIST().get(0).getXMMC());
            //开票日期
            tv_invoice_Date = findViewById(R.id.tv_invoice_Date);

            tv_invoice_Date.setText("开票日期\n" + Mytools.strToDateFormat(jyInfo.getJsonMap().getKPRQ()));
        }
        tv_invoice_Date.setText("开票日期\n" + jyInfo.getJsonMap().getKPRQ());
        //发票代码
        tv_invoice_Code = findViewById(R.id.tv_invoice_Code);
        tv_invoice_Code.setText("发票代码\n" + jyInfo.getJsonMap().getFP_DM());
        //发票号码
        tv_invoice_No = findViewById(R.id.tv_invoice_No);
        tv_invoice_No.setText("发票号码\n" + jyInfo.getJsonMap().getFP_HM());
        //抽奖按钮
        tv_choujiang = findViewById(R.id.tv_choujiang);

        tv_choujiang.setOnClickListener(this);
        //获取数据库
        mDBUtil = new DBUtil(context);
        historyContent = new HistoryContent();


    }

    /**
     * 确定发票类型的名称
     */
    private String setfpname(String fpdm) {
        String fpname = null;
        String fp_province = checkprovince(fpdm);
        if (Mytools.getfplx(fpdm) != null) {
            if (Mytools.getfplx(fpdm).equals("10")) {
                fpname = fp_province + "增值税普通发票（电子)";
            } else if (Mytools.getfplx(fpdm).equals("11")) {
                fpname = fp_province + "增值税普通发票（卷式)";
            } else if (Mytools.getfplx(fpdm).equals("04")) {
                fpname = fp_province + "增值税普通发票";
            } else if (Mytools.getfplx(fpdm).equals("03")) {
                fpname = fp_province + "机动车发票";
            } else if (Mytools.getfplx(fpdm).equals("01")) {
                fpname = fp_province + "增值税专用发票";
            } else if (Mytools.getfplx(fpdm).equals("02")) {
                fpname = fp_province + "货运运输业增值税专用发票";
            }else if (Mytools.getfplx(fpdm).equals("14")){
                fpname = fp_province + "增值税电子普通发票(通行费)";
            }
        }
        return fpname;
    }
    /**
     * 发票省份确定
     */
    private String checkprovince(String str) {
        String str_sub = null;
        if (str.length() == 12) {
            str_sub = str.substring(1, 3);

        } else if (str.length() == 10) {
            str_sub = str.substring(0, 2);

        }
        String[] names = getResources().getStringArray(R.array.provincecode);
        String[] values = getResources().getStringArray(R.array.provincename);
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(str_sub)) {
                Log.d("返回的省份===", values[i]);
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void onClick(View view) {

        //防止控件的重复点击，导致dialog多重出现，id失败
        if (Mytools.utility.isFastDoubleClick()) {
            return;
        } else {
            switch (view.getId()) {
                case R.id.tv_choujiang://042湖北
                    if (!jyInfo.getJsonMap().getFP_DM().substring(0, 3).equals("042")) {
                        Toast.makeText(context, "非湖北地区发票无法参加抽奖！", Toast.LENGTH_SHORT).show();
                    } else if (mDBUtil.find(jyInfo.getJsonMap().getJYM())) {
                        Toast.makeText(context, "该发票已参与抽奖！", Toast.LENGTH_SHORT).show();

                    } else {

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
                                Log.d("TAG====", "dialog销毁了");
                            }
                        });
                        //添加滚动监听
                        wheelSurfView.setRotateListener(new RotateListener() {
                            @Override
                            public void rotateEnd(int position, String des) {
                                //获得结果取消点击监听
                                //iv_cancle.setVisibility(View.VISIBLE);
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
                                Toast.makeText(context, "恭喜您获得了:" + des, Toast.LENGTH_SHORT).show();
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
                                //插入数据
                                historyContent.setFp_jym(jyInfo.getJsonMap().getJYM());
                                historyContent.setFp_dm(jyInfo.getJsonMap().getFP_DM());
                                historyContent.setFp_no(jyInfo.getJsonMap().getFP_HM());
                                historyContent.setFp_date(jyInfo.getJsonMap().getKPRQ());
                                historyContent.setFp_amount(jyInfo.getJsonMap().getJSHJ());//
                                mDBUtil.Insert(historyContent);

                            }
                        });


                    }

                    break;
            }
        }

    }
}

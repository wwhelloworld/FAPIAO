package com.lcsd.fapiao.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lcsd.fapiao.R;
import com.lcsd.fapiao.dialog.SelectDialog;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_commit_adress;
    private Context context;
    private boolean isok = true;
    private SelectDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        context = this;
        initView();
    }

    private void initView() {
        dialog = new SelectDialog(context);
        findViewById(R.id.ll_info_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                dialog.findViewById(R.id.tv_select_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "信息提交成功！", Toast.LENGTH_SHORT).show();
                        if (InputActivity.inputActivity != null) {
                            InputActivity.inputActivity.finish();
                        }
                        DetailActivity.detailActivity.finish();
                        finish();
                    }
                });
            }
        });
        tv_commit_adress = findViewById(R.id.tv_commit_adress);
        tv_commit_adress.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit_adress:
                Toast.makeText(context, "信息提交成功！", Toast.LENGTH_SHORT).show();

                if (InputActivity.inputActivity != null) {
                    InputActivity.inputActivity.finish();
                }
                DetailActivity.detailActivity.finish();
                finish();
                break;
        }
    }
}

package com.lcsd.fapiao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcsd.fapiao.R;

public class SelectDialog extends Dialog implements View.OnClickListener {
    private LinearLayout ll_select_bc;
    private TextView tv_select_confirm, tv_select_cancel;

    public SelectDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.select_dialog);
        initView();
    }

    private void initView() {
        this.setCancelable(false);
        ll_select_bc = findViewById(R.id.ll_select_bc);
        ll_select_bc.setBackgroundResource(R.drawable.round_bc_white);
        tv_select_confirm = findViewById(R.id.tv_select_confirm);
        tv_select_cancel = findViewById(R.id.tv_select_cancel);
        tv_select_cancel.setOnClickListener(this);
        tv_select_confirm.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_cancel:
                this.dismiss();
                break;
            case R.id.tv_select_confirm:

                break;
        }

    }
}

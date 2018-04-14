package com.lcsd.fapiao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lcsd.fapiao.R;

public class CJDialog extends Dialog {
    public CJDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.choujiang_dialog);
    }

}

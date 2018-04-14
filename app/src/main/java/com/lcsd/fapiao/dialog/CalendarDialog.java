package com.lcsd.fapiao.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lcsd.fapiao.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wei on 18-4-2.
 */

public class CalendarDialog extends Dialog implements View.OnClickListener {
    private TextView tv_no, tv_ok;

    public CalendarDialog(Context context) {
        super(context);
        setContentView(R.layout.calendar_select_layout);
        initView();
    }

    private void initView() {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        CalendarPickerView calendar = findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);
        tv_no = findViewById(R.id.tv_calendar_cancel);
        tv_ok = findViewById(R.id.tv_canlendar_confirm);
        tv_no.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_calendar_cancel:
                this.dismiss();
                break;
            case R.id.tv_canlendar_confirm:
                break;
        }

    }
}

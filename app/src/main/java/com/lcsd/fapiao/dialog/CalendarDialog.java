package com.lcsd.fapiao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lcsd.fapiao.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wei on 18-4-2.
 */

public class CalendarDialog extends Dialog implements View.OnClickListener {
    private TextView tv_no, tv_ok, tv_calendar;
    private CalendarPickerView calendar;

    public CalendarDialog(Context context) {
        super(context);
        setContentView(R.layout.calendar_select_layout);
        initView();
    }

    private void initView() {
        //下一年
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        //上一年
        Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        calendar = findViewById(R.id.calendar_view);
        //当前日期
        Date today = new Date();
        calendar.init(lastYear.getTime(), nextYear.getTime()).withSelectedDate(today);
        tv_no = findViewById(R.id.tv_calendar_cancel);
        tv_ok = findViewById(R.id.tv_canlendar_confirm);
        tv_no.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_calendar_cancel:
                this.cancel();
                break;
            case R.id.tv_canlendar_confirm:
                SetDate();
                this.cancel();
                break;
        }
    }


    public View getView(TextView textView) {
        tv_calendar = textView;
        return tv_calendar;
    }

    public void SetDate() {
        long time = calendar.getSelectedDate().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String result = format.format(time);
        Log.i("选择日期是====", result + time);
        tv_calendar.setText(result);
    }
}

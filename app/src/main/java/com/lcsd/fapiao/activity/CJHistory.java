package com.lcsd.fapiao.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lcsd.fapiao.R;
import com.lcsd.fapiao.adapter.CJHistory_adapter;
import com.lcsd.fapiao.sql.DBUtil;
import com.lcsd.fapiao.sql.HistoryContent;

import java.util.ArrayList;
import java.util.List;

public class CJHistory extends AppCompatActivity {
    private Context context;
    //数据库相关参数
    private DBUtil mUtil;
    private ListView listView; //显示查询结果
    private List<HistoryContent> list = null;
    private CJHistory_adapter adapter;
    private LinearLayout ll_history_none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cjhistory);
        context = this;
        initView();
    }

    private void initView() {
        findViewById(R.id.ll_back_cjhistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ll_history_none = findViewById(R.id.ll_history_none);
        //获取数据库
        mUtil = new DBUtil(context);
        list = new ArrayList<>();
        //查询所有数据
        list = mUtil.QueryAllData();
        listView = findViewById(R.id.lv_cjhistory);
        adapter = new CJHistory_adapter(context, list);
        listView.setAdapter(adapter);
        //Log.d("取得的日期=====",list.get(0).getFp_data()+list.get(0).getFp_dm());
        if (list != null && list.size() > 0) {
            ll_history_none.setVisibility(View.GONE);
        } else {
            ll_history_none.setVisibility(View.VISIBLE);
        }


    }
}

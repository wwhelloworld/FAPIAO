package com.lcsd.fapiao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lcsd.fapiao.R;
import com.lcsd.fapiao.sql.HistoryContent;
import com.lcsd.fapiao.utils.Mytools;

import java.util.List;

public class CJHistory_adapter extends BaseAdapter {
    private Context context;
    private List<HistoryContent> list;

    public CJHistory_adapter(Context context, List<HistoryContent> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CJHistoryHolder holder = null;
        if (view == null) {
            holder = new CJHistoryHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_cjhistory, null);
            holder.tv_01 = view.findViewById(R.id.tv_cj_dm);
            holder.tv_02 = view.findViewById(R.id.tv_cj_no);
            holder.tv_03 = view.findViewById(R.id.tv_cj_data);
            holder.tv_04 = view.findViewById(R.id.tv_cj_amount);
            view.setTag(holder);
        } else {
            holder = (CJHistoryHolder) view.getTag();
        }
        if (list.size() > 0) {
            holder.tv_01.setText("发票代码："+list.get(i).getFp_dm());
            holder.tv_02.setText("发票号码："+list.get(i).getFp_no());
            holder.tv_03.setText(Mytools.strToDateFormat(list.get(i).getFp_date().toString().trim()));
            holder.tv_04.setText("￥"+list.get(i).getFp_amount());
        }

        return view;
    }

    class CJHistoryHolder {
        TextView tv_01, tv_02, tv_03, tv_04;

    }
}

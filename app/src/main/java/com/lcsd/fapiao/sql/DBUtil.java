package com.lcsd.fapiao.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private DBhelper helper;

    public DBUtil(Context context) {
        super();
        helper = new DBhelper(context);
    }

    /**
     * 数据插入
     */
    public void Insert(HistoryContent historyContent) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //插入数据SQL语句
        //实例化常量值
        ContentValues cValue = new ContentValues();
        //验证码
        cValue.put("id", historyContent.getFp_jym());//tirm()不然会报转换格式错误
        //添加代码
        cValue.put("code", historyContent.getFp_dm());
        //添加号码
        cValue.put("number", historyContent.getFp_no());
        //添加日期
        cValue.put("date", historyContent.getFp_date());
        //添加金额
        cValue.put("amount", historyContent.getFp_amount());
        //执行SQL语句
        try {            //db.execSQL(cValue.toString());
            //调用insert()方法插入数据
            db.insert("CJHISTORY", null, cValue);
            Log.e("success", "添加数据成功");
        } catch (SQLException e) {
            Log.e("error", "添加数据失败");
        } finally {
            db.close();
        }


    }

    /**
     * 数据删除
     */
    public void Delete(String id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = "id = ?";
        String[] whereValue = {id};
        db.delete("CJHISTORY", where, whereValue);
    }

    /**
     * 查询所有数据
     */
    public List<HistoryContent> QueryAllData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<HistoryContent> list = new ArrayList<>();
        Cursor cursor = db.query("CJHISTORY", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            HistoryContent historyContent = new HistoryContent();
            historyContent.setFp_jym(cursor.getString(cursor.getColumnIndex("id")));
            historyContent.setFp_dm(cursor.getString(cursor.getColumnIndex("code")));
            historyContent.setFp_no(cursor.getString(cursor.getColumnIndex("number")));
            historyContent.setFp_date(cursor.getString(cursor.getColumnIndex("date")));
            historyContent.setFp_amount(cursor.getString(cursor.getColumnIndex("amount")));
            list.add(historyContent);
        }
        db.close();
        return list;
    }
}

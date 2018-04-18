package com.lcsd.fapiao.utils;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wei on 18-4-4.
 */

public class Mytools {


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double change(double a) {
        return a * Math.PI / 180;
    }

    public static double changeAngle(double a) {
        return a * 180 / Math.PI;
    }

    /**
     * 将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
     */
    public static String strToDateFormat(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate = null;
        try {
            newDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(newDate);
    }

    /**
     * 截取字符串，从后往前截取
     */
    public static String strbacksubstring(int i, String str) {
        String sstr = "";
        sstr = str.substring(str.length() - i, str.length());
        return sstr;
    }

    public static class utility {
        private static long lastClickTime;

        public static boolean isFastDoubleClick() {
            long time = System.currentTimeMillis();
            long timeD = time - lastClickTime;
            if (0 < timeD && timeD < 1000) {
                return true;
            }
            lastClickTime = time;
            return false;
        }
    }


    /**
     * 发票类型判断
     */
    public static String getfplx(String str) {
        String[] code = {"144031539110", "131001570151", "133011501118", "111001571071"};
        String b;
        String c = null;

        if (str.length() == 12) {
            //括号里四个是10类型的
            for (int i = 0; i < code.length; i++) {
                if (str.equals(code[i])) {
                    c = "10";//增值税普通发票（电子）
                    return c;
                }
            }
            if (c == null) {
                //截取第八位字符
                b = str.substring(8, 9);
                Log.i("截取字符1===", str.substring(str.length() - 2, str.length()));
                Log.i("截取的字符2====", b);
                Log.i("截取的字符3====", str.charAt(0) + "");
                //增加判断，判断是否为新版电子票
                if (str.charAt(0) == '0' && str.substring(str.length() - 2, str.length()).equals("11")) {
                    c = "10";//增值税普通发票（电子）
                } else if (str.charAt(0) == '0' && (str.substring(str.length() - 2, str.length()).equals("06") || str.substring(str.length() - 2, str.length()).equals("07"))) {
                    c = "11";//增值税普通发票（卷式）
                } else if (str.charAt(0) == '0' && (str.substring(str.length() - 2, str.length()).equals("04") || str.substring(str.length() - 2, str.length()).equals("05"))) {  //第11-12位代表票种和联次，其中04代表二联增值税普通发票（折叠票）、05代表五联增值税普通发票（折叠票）
                    c = "04";//2018年1月1日执行的增值税普通发票
                } else if (b == "2" && str.charAt(0) != '0') {  //如果还是99，且第8位是2，则是机动车发票
                    c = "03";//机动车发票
                }
            }

        } else if (str.length() == 10) {
            b = str.substring(8, 9);
            if (b == "1" || b == "5") {
                c = "01";//专票
            } else if (b.equals("6") || b.equals("3")) {
                c = "04";//增值税普通发票
            } else if (b.equals("7") || b.equals("2")) {
                c = "02";//货运运输业增值税专用发票
            }
        }
        if (c != null) {
            Log.i("返回的类型值====", c);
        } else {
            Log.i("返回的类型值====", "空值");

        }
        return c;
    }

    public static String getprovinces(String str) {
        String[] provincennames={};
        String[] provincencodes={"11","12","13","14","15","21","22","23","31","32","33","34","35","36","37","41","42","43","44","45","46","50","51",
        "52","53","54","61","62","63","64","65"};
        String provincename = null;
        String sub_word = null;//截取省份的字符
        if (str.length() == 12) {
            sub_word = str.substring(1, 3);

        } else if (str.length() == 10) {
            sub_word = str.substring(0, 2);

        } else {
            return "非法";
        }

        return provincename;

    }

}

package com.lcsd.fapiao.utils;

import android.content.Context;

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
        String c = "99";

        if (str.length() == 12) {
            b = str.substring(7, 8);
            for (int i = 0; i < code.length; i++) {
                if (str == code[i]) {
                    c = "10";//增值税普通发票（电子）
                    break;
                }
            }
            if (c == "99") {//增加判断，判断是否为新版电子票
                if (str.charAt(0) == '0' && str.substring(10, 12) == "11") {
                    c = "10";//增值税普通发票（电子）
                }
                if (str.charAt(0) == '0' && (str.substring(10, 12) == "06" || str.substring(10, 12) == "07")) {
                    c = "11";//增值税普通发票（卷式）
                }
                if (str.charAt(0) == '0' && (str.substring(10, 12) == "04" || str.substring(10, 12) == "05")) {  //第11-12位代表票种和联次，其中04代表二联增值税普通发票（折叠票）、05代表五联增值税普通发票（折叠票）
                    c = "04";//2018年1月1日执行的增值税普通发票
                }
            }
            if (c == "99") { //如果还是99，且第8位是2，则是机动车发票
                if (b == "2" && str.charAt(0) != '0') {
                    c = "03";//机动车发票
                }
            }
        } else if (str.length() == 10) {
            b = str.substring(7, 8);
            if (b == "1" || b == "5") {
                c = "01";//专票
            } else if (b == "6" || b == "3") {
                c = "04";//增值税普通发票
            } else if (b == "7" || b == "2") {
                c = "02";//货运运输业增值税专用发票
            }
        }
        return c;
    }


    // private
/*/**
 * 校验不含税金额或校验码
function validateBhsjeJym(bhsjejym){
    if(fplx=="" || fplx=="99" ||fplx=="01" || fplx =="02" || fplx=="03"){
        if(bhsjejym.length!=0){
            $("#bhsje_id").parent().parent().removeClass("inputError").addClass("inputSuccess");
            return true;
        }else{
            $("#bhsje_id").parent().parent().removeClass("inputSuccess").addClass("inputError");
            return false;
        }
    }else{
        if(bhsjejym.length==6){
            var reg = /^\d{6}$/;
            if(reg.test(bhsjejym)){
                $("#bhsje_id").parent().parent().removeClass("inputError").addClass("inputSuccess");
                return true;
            }else{
                $("#bhsje_id").parent().parent().removeClass("inputSuccess").addClass("inputError");
                return false;
            }
        }else{
            $("#bhsje_id").parent().parent().removeClass("inputSuccess").addClass("inputError");
            return false;
        }
    }

}*/
}

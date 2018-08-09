package com.lcsd.fapiao.entity;

import java.util.List;

/***
 *
 * Json To JavaBean
 * @author www.json123.com
 *
 */
public class YZM_Info {

    public static class TJsonMap{

        public static class TDATA{

            private	String	message;	/*请输入验证码图片中黄色文字*/
            private	String	yzmSj;	/*2018-08-08 08:49:17*/
            private	String	yzmType;	/*02*/
            private	String	jmmy;	/*f99a24cc185ac4c03c1fcdaa4523f626*/
            private	String	yzm;	/*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*/

            public void setMessage(String value){
                this.message = value;
            }
            public String getMessage(){
                return this.message;
            }

            public void setYzmSj(String value){
                this.yzmSj = value;
            }
            public String getYzmSj(){
                return this.yzmSj;
            }

            public void setYzmType(String value){
                this.yzmType = value;
            }
            public String getYzmType(){
                return this.yzmType;
            }

            public void setJmmy(String value){
                this.jmmy = value;
            }
            public String getJmmy(){
                return this.jmmy;
            }

            public void setYzm(String value){
                this.yzm = value;
            }
            public String getYzm(){
                return this.yzm;
            }

        }
        private	String	mSG;	/*查验发票信息成功!*/
        private	String	rSCODE;	/*0000*/
        private	TDATA	dATA;	/*TDATA*/

        public void setMSG(String value){
            this.mSG = value;
        }
        public String getMSG(){
            return this.mSG;
        }

        public void setRSCODE(String value){
            this.rSCODE = value;
        }
        public String getRSCODE(){
            return this.rSCODE;
        }

        public void setDATA(TDATA value){
            this.dATA = value;
        }
        public TDATA getDATA(){
            return this.dATA;
        }

    }
    private	TJsonMap	jsonMap;	/*TJsonMap*/

    public void setJsonMap(TJsonMap value){
        this.jsonMap = value;
    }
    public TJsonMap getJsonMap(){
        return this.jsonMap;
    }

}

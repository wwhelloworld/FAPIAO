package com.lcsd.fapiao.entity;

import java.io.Serializable;
import java.util.List;

/***
 *
 * Json To JavaBean
 * @author www.json123.com
 *
 */
public class JYInfo implements Serializable {

    public static class TJsonMap implements Serializable{

        public static class TFPMX_LIST implements Serializable{

            private	String	xMDJ;	/*133*/
            private	String	sE;	/*0*/
            private	String	xMMC;	/**电信服务*通信服务费*/
            private	String	xMJE;	/*133*/
            private	String	gGXH;	/* */
            private	String	sL;	/*0*/
            private	String	xMSL;	/*1*/
            private	String	dW;	/* */

            public void setXMDJ(String value){
                this.xMDJ = value;
            }
            public String getXMDJ(){
                return this.xMDJ;
            }

            public void setSE(String value){
                this.sE = value;
            }
            public String getSE(){
                return this.sE;
            }

            public void setXMMC(String value){
                this.xMMC = value;
            }
            public String getXMMC(){
                return this.xMMC;
            }

            public void setXMJE(String value){
                this.xMJE = value;
            }
            public String getXMJE(){
                return this.xMJE;
            }

            public void setGGXH(String value){
                this.gGXH = value;
            }
            public String getGGXH(){
                return this.gGXH;
            }

            public void setSL(String value){
                this.sL = value;
            }
            public String getSL(){
                return this.sL;
            }

            public void setXMSL(String value){
                this.xMSL = value;
            }
            public String getXMSL(){
                return this.xMSL;
            }

            public void setDW(String value){
                this.dW = value;
            }
            public String getDW(){
                return this.dW;
            }

        }
        private	List<TFPMX_LIST>	fPMX_LIST;	/*List<TFPMX_LIST>*/
        public void setFPMX_LIST(List<TFPMX_LIST> value){
            this.fPMX_LIST = value;
        }
        public List<TFPMX_LIST> getFPMX_LIST(){
            return this.fPMX_LIST;
        }

        private	String	jYM;	/*70601045633567375498*/
        private	String	gMF_MC;	/*合肥福斯特科技有限公司*/
        private	String	xSF_DZDH;	/*合肥市长江西路99号10086*/
        private	String	xSF_NSRSBH;	/*913401007430785973*/
        private	String	xSF_YHZH;	/*中国光大银行合肥分行营业部 087669120100304073427*/
        private	String	fP_HM;	/*21628850*/
        private	String	codeMsg;	/*查验发票信息成功!*/
        private	String	jSHJ_ZWDX;	/*壹佰零叁圆柒角零分整*/
        private	String	fP_DM;	/*034011700111*/
        private	String	jSHJ;	/*103.7*/
        private	String	gMF_DZDH;	/*合肥高新区大学科技园A-302、304室 0551-65319088*/
        private	String	code;	/*0000*/
        private	String	xSF_MC;	/*中国移动通信集团安徽有限公司合肥分公司*/
        private	String	bZ;	/*15956509143 当前可用余额：102.53 本次费用结算时间为: 2018年02月01日至2018年02月28日*/
        private	String	gMF_NSRSBH;	/*91340100760833953C*/
        private	String	kPRQ;	/*20180304*/
        private	String	hJJE;	/*103.7*/
        private	String	jQBH;	/*661616299504*/
        private	String	fP_AREA;	/*安徽*/
        private	String	kPRQ_CHANGE;	/*2018年03月04日*/
        private	String	fP_TYPE;	/*增值税电子普通发票*/
        private	String	hJSE;	/*0*/
        private	String	gMF_YHZH;	/*建设银行合肥高新支行 34050147860500000132*/

        public void setJYM(String value){
            this.jYM = value;
        }
        public String getJYM(){
            return this.jYM;
        }

        public void setGMF_MC(String value){
            this.gMF_MC = value;
        }
        public String getGMF_MC(){
            return this.gMF_MC;
        }

        public void setXSF_DZDH(String value){
            this.xSF_DZDH = value;
        }
        public String getXSF_DZDH(){
            return this.xSF_DZDH;
        }

        public void setXSF_NSRSBH(String value){
            this.xSF_NSRSBH = value;
        }
        public String getXSF_NSRSBH(){
            return this.xSF_NSRSBH;
        }

        public void setXSF_YHZH(String value){
            this.xSF_YHZH = value;
        }
        public String getXSF_YHZH(){
            return this.xSF_YHZH;
        }

        public void setFP_HM(String value){
            this.fP_HM = value;
        }
        public String getFP_HM(){
            return this.fP_HM;
        }

        public void setCodeMsg(String value){
            this.codeMsg = value;
        }
        public String getCodeMsg(){
            return this.codeMsg;
        }

        public void setJSHJ_ZWDX(String value){
            this.jSHJ_ZWDX = value;
        }
        public String getJSHJ_ZWDX(){
            return this.jSHJ_ZWDX;
        }

        public void setFP_DM(String value){
            this.fP_DM = value;
        }
        public String getFP_DM(){
            return this.fP_DM;
        }

        public void setJSHJ(String value){
            this.jSHJ = value;
        }
        public String getJSHJ(){
            return this.jSHJ;
        }

        public void setGMF_DZDH(String value){
            this.gMF_DZDH = value;
        }
        public String getGMF_DZDH(){
            return this.gMF_DZDH;
        }

        public void setCode(String value){
            this.code = value;
        }
        public String getCode(){
            return this.code;
        }

        public void setXSF_MC(String value){
            this.xSF_MC = value;
        }
        public String getXSF_MC(){
            return this.xSF_MC;
        }

        public void setBZ(String value){
            this.bZ = value;
        }
        public String getBZ(){
            return this.bZ;
        }

        public void setGMF_NSRSBH(String value){
            this.gMF_NSRSBH = value;
        }
        public String getGMF_NSRSBH(){
            return this.gMF_NSRSBH;
        }

        public void setKPRQ(String value){
            this.kPRQ = value;
        }
        public String getKPRQ(){
            return this.kPRQ;
        }

        public void setHJJE(String value){
            this.hJJE = value;
        }
        public String getHJJE(){
            return this.hJJE;
        }

        public void setJQBH(String value){
            this.jQBH = value;
        }
        public String getJQBH(){
            return this.jQBH;
        }

        public void setFP_AREA(String value){
            this.fP_AREA = value;
        }
        public String getFP_AREA(){
            return this.fP_AREA;
        }

        public void setKPRQ_CHANGE(String value){
            this.kPRQ_CHANGE = value;
        }
        public String getKPRQ_CHANGE(){
            return this.kPRQ_CHANGE;
        }

        public void setFP_TYPE(String value){
            this.fP_TYPE = value;
        }
        public String getFP_TYPE(){
            return this.fP_TYPE;
        }

        public void setHJSE(String value){
            this.hJSE = value;
        }
        public String getHJSE(){
            return this.hJSE;
        }

        public void setGMF_YHZH(String value){
            this.gMF_YHZH = value;
        }
        public String getGMF_YHZH(){
            return this.gMF_YHZH;
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

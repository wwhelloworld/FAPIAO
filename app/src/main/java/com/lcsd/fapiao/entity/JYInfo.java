package com.lcsd.fapiao.entity;

import java.io.Serializable;
import java.util.List;

/***
 *
 * Json To JavaBean
 * @author www.json123.com
 *
 */
public class JYInfo implements Serializable{

    public static class TData implements Serializable{

        public static class TDetailList implements Serializable{

            private String unit;	/**/
            private String taxUnitPrice;	/**/
            private String taxAmount;	/*0.00*/
            private String num;	/*1*/
            private String taxRate;	/*0%*/
            private String specificationModel;	/**/
            private String taxDetailAmount;	/**/
            private String detailAmount;	/*133.00*/
            private String unitPrice;	/*133*/
            private String detailNo;	/*1*/
            private String goodsName;

            /**
             * 电信服务*通信服务费
             */

            public void setUnit(String value) {
                this.unit = value;
            }

            public String getUnit() {
                return this.unit;
            }

            public void setTaxUnitPrice(String value) {
                this.taxUnitPrice = value;
            }

            public String getTaxUnitPrice() {
                return this.taxUnitPrice;
            }

            public void setTaxAmount(String value) {
                this.taxAmount = value;
            }

            public String getTaxAmount() {
                return this.taxAmount;
            }

            public void setNum(String value) {
                this.num = value;
            }

            public String getNum() {
                return this.num;
            }

            public void setTaxRate(String value) {
                this.taxRate = value;
            }

            public String getTaxRate() {
                return this.taxRate;
            }

            public void setSpecificationModel(String value) {
                this.specificationModel = value;
            }

            public String getSpecificationModel() {
                return this.specificationModel;
            }

            public void setTaxDetailAmount(String value) {
                this.taxDetailAmount = value;
            }

            public String getTaxDetailAmount() {
                return this.taxDetailAmount;
            }

            public void setDetailAmount(String value) {
                this.detailAmount = value;
            }

            public String getDetailAmount() {
                return this.detailAmount;
            }

            public void setUnitPrice(String value) {
                this.unitPrice = value;
            }

            public String getUnitPrice() {
                return this.unitPrice;
            }

            public void setDetailNo(String value) {
                this.detailNo = value;
            }

            public String getDetailNo() {
                return this.detailNo;
            }

            public void setGoodsName(String value) {
                this.goodsName = value;
            }

            public String getGoodsName() {
                return this.goodsName;
            }

        }

        private List<TDetailList> detailList;	/*List<TDetailList>*/

        public void setDetailList(List<TDetailList> value) {
            this.detailList = value;
        }

        public List<TDetailList> getDetailList() {
            return this.detailList;
        }

        private String invoiceDate;	/*20180304*/
        private String salerAddressPhone;	/*合肥市长江西路99号10086*/
        private String remark;	/*15956509143 当前可用余额：102.53 本次费用结算时间为: 2018年02月01日至2018年02月28日*/
        private String invoiceAmount;	/*103.70*/
        private Object ewmPath;	/*Object*/
        private String cancellationMark;	/*N*/
        private String invoiceNo;	/*21628850*/
        private String drawer;	/**/
        private String checkCount;	/*3*/
        private String blueInvoiceNo;	/**/
        private Object cxKey;	/*Object*/
        private String salerTaxNo;	/*913401007430785973*/
        private String resultCode;	/*0001*/
        private String totalAmount;	/*103.70*/
        private String buyerAddressPhone;	/*合肥高新区大学科技园A-302、304室 0551-65319088*/
        private String reviewer;	/**/
        private String salerName;	/*中国移动通信集团安徽有限公司合肥分公司*/
        private String buyerAccount;	/*建设银行合肥高新支行 34050147860500000132*/
        private Boolean lpflag;	/*false*/
        private String buyerTaxNo;	/*91340100760833953C*/
        private String checkCode;	/*70601045633567375498*/
        private String machineNo;	/*661616299504*/
        private String blueInvoiceCode;	/**/
        private String invoiceType;	/*10*/
        private String payee;	/**/
        private String resultTip;	/*查验成功发票一致*/
        private String salerAccount;	/*中国光大银行合肥分行营业部 087669120100304073427*/
        private String buyerName;	/*合肥福斯特科技有限公司*/
        private String taxAmount;	/*0.00*/
        private String invoiceCode;	/*034011700111*/

        public void setInvoiceDate(String value) {
            this.invoiceDate = value;
        }

        public String getInvoiceDate() {
            return this.invoiceDate;
        }

        public void setSalerAddressPhone(String value) {
            this.salerAddressPhone = value;
        }

        public String getSalerAddressPhone() {
            return this.salerAddressPhone;
        }

        public void setRemark(String value) {
            this.remark = value;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setInvoiceAmount(String value) {
            this.invoiceAmount = value;
        }

        public String getInvoiceAmount() {
            return this.invoiceAmount;
        }

        public void setEwmPath(Object value) {
            this.ewmPath = value;
        }

        public Object getEwmPath() {
            return this.ewmPath;
        }

        public void setCancellationMark(String value) {
            this.cancellationMark = value;
        }

        public String getCancellationMark() {
            return this.cancellationMark;
        }

        public void setInvoiceNo(String value) {
            this.invoiceNo = value;
        }

        public String getInvoiceNo() {
            return this.invoiceNo;
        }

        public void setDrawer(String value) {
            this.drawer = value;
        }

        public String getDrawer() {
            return this.drawer;
        }

        public void setCheckCount(String value) {
            this.checkCount = value;
        }

        public String getCheckCount() {
            return this.checkCount;
        }

        public void setBlueInvoiceNo(String value) {
            this.blueInvoiceNo = value;
        }

        public String getBlueInvoiceNo() {
            return this.blueInvoiceNo;
        }

        public void setCxKey(Object value) {
            this.cxKey = value;
        }

        public Object getCxKey() {
            return this.cxKey;
        }

        public void setSalerTaxNo(String value) {
            this.salerTaxNo = value;
        }

        public String getSalerTaxNo() {
            return this.salerTaxNo;
        }

        public void setResultCode(String value) {
            this.resultCode = value;
        }

        public String getResultCode() {
            return this.resultCode;
        }

        public void setTotalAmount(String value) {
            this.totalAmount = value;
        }

        public String getTotalAmount() {
            return this.totalAmount;
        }

        public void setBuyerAddressPhone(String value) {
            this.buyerAddressPhone = value;
        }

        public String getBuyerAddressPhone() {
            return this.buyerAddressPhone;
        }

        public void setReviewer(String value) {
            this.reviewer = value;
        }

        public String getReviewer() {
            return this.reviewer;
        }

        public void setSalerName(String value) {
            this.salerName = value;
        }

        public String getSalerName() {
            return this.salerName;
        }

        public void setBuyerAccount(String value) {
            this.buyerAccount = value;
        }

        public String getBuyerAccount() {
            return this.buyerAccount;
        }

        public void setLpflag(Boolean value) {
            this.lpflag = value;
        }

        public Boolean getLpflag() {
            return this.lpflag;
        }

        public void setBuyerTaxNo(String value) {
            this.buyerTaxNo = value;
        }

        public String getBuyerTaxNo() {
            return this.buyerTaxNo;
        }

        public void setCheckCode(String value) {
            this.checkCode = value;
        }

        public String getCheckCode() {
            return this.checkCode;
        }

        public void setMachineNo(String value) {
            this.machineNo = value;
        }

        public String getMachineNo() {
            return this.machineNo;
        }

        public void setBlueInvoiceCode(String value) {
            this.blueInvoiceCode = value;
        }

        public String getBlueInvoiceCode() {
            return this.blueInvoiceCode;
        }

        public void setInvoiceType(String value) {
            this.invoiceType = value;
        }

        public String getInvoiceType() {
            return this.invoiceType;
        }

        public void setPayee(String value) {
            this.payee = value;
        }

        public String getPayee() {
            return this.payee;
        }

        public void setResultTip(String value) {
            this.resultTip = value;
        }

        public String getResultTip() {
            return this.resultTip;
        }

        public void setSalerAccount(String value) {
            this.salerAccount = value;
        }

        public String getSalerAccount() {
            return this.salerAccount;
        }

        public void setBuyerName(String value) {
            this.buyerName = value;
        }

        public String getBuyerName() {
            return this.buyerName;
        }

        public void setTaxAmount(String value) {
            this.taxAmount = value;
        }

        public String getTaxAmount() {
            return this.taxAmount;
        }

        public void setInvoiceCode(String value) {
            this.invoiceCode = value;
        }

        public String   getInvoiceCode() {
            return this.invoiceCode;
        }

    }

    private String errCode;	/**/
    private TData data;	/*TData*/
    private Integer code;	/*0*/
    private String msg;	/*success*/

    public void setErrCode(String value) {
        this.errCode = value;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setData(TData value) {
        this.data = value;
    }

    public TData getData() {
        return this.data;
    }

    public void setCode(Integer value) {
        this.code = value;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setMsg(String value) {
        this.msg = value;
    }

    public String getMsg() {
        return this.msg;
    }

}

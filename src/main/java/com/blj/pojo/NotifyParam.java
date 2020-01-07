package com.blj.pojo;



import java.math.BigDecimal;

/**
 * @author BaiLiJun  on 2020/1/2
 */

public class NotifyParam {

    private String orderNo;


    private BigDecimal orderAmount;


    private String orderCurrency;


    private BigDecimal paymentAmount;


    private String transactionId;


    private String status;


    private String signType;


    private String sign;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "NotifyParam{" +
                "orderNo='" + orderNo + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderCurrency='" + orderCurrency + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", transactionId='" + transactionId + '\'' +
                ", status='" + status + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}

package com.blj.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author BaiLiJun  on 2020/3/31
 */
public class BbMatchExtVo implements Serializable {

    private Long id;

    //资产
    private String asset;

    //合约交易品种
    private String symbol;


    private Long matchTxId;

    private Long tkAccountId;

    private Long tkOrderId;


    private Long mkAccountId;


    private Long mkOrderId;


    private BigDecimal price;


    private BigDecimal number;


    private Long tradeTime;


    private Long tkBidFlag;


    private Long modified;


    private Long created;

    public BbMatchExtVo() {
    }

    @Override
    public String toString() {
        return "BbMatchExtVo{" +
                "id=" + id +
                ", asset='" + asset + '\'' +
                ", symbol='" + symbol + '\'' +
                ", matchTxId=" + matchTxId +
                ", tkAccountId=" + tkAccountId +
                ", tkOrderId=" + tkOrderId +
                ", mkAccountId=" + mkAccountId +
                ", mkOrderId=" + mkOrderId +
                ", price=" + price +
                ", number=" + number +
                ", tradeTime=" + tradeTime +
                ", tkBidFlag=" + tkBidFlag +
                ", modified=" + modified +
                ", created=" + created +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getMatchTxId() {
        return matchTxId;
    }

    public void setMatchTxId(Long matchTxId) {
        this.matchTxId = matchTxId;
    }

    public Long getTkAccountId() {
        return tkAccountId;
    }

    public void setTkAccountId(Long tkAccountId) {
        this.tkAccountId = tkAccountId;
    }

    public Long getTkOrderId() {
        return tkOrderId;
    }

    public void setTkOrderId(Long tkOrderId) {
        this.tkOrderId = tkOrderId;
    }

    public Long getMkAccountId() {
        return mkAccountId;
    }

    public void setMkAccountId(Long mkAccountId) {
        this.mkAccountId = mkAccountId;
    }

    public Long getMkOrderId() {
        return mkOrderId;
    }

    public void setMkOrderId(Long mkOrderId) {
        this.mkOrderId = mkOrderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Long getTkBidFlag() {
        return tkBidFlag;
    }

    public void setTkBidFlag(Long tkBidFlag) {
        this.tkBidFlag = tkBidFlag;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}

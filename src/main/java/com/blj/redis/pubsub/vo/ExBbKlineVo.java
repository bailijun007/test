package com.blj.redis.pubsub.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author BaiLiJun  on 2020/3/10
 */
public class ExBbKlineVo implements Serializable {
    private long timestamp; // 毫秒时间戳
    private BigDecimal volume; //  number 累加
    private BigDecimal high; // max(price)
    private BigDecimal low;// min(price)
    private BigDecimal open;// 按成交时间第一个 first(price)
    private BigDecimal close;// 按成交时间最后一个 last(price)
    public ExBbKlineVo() {
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }
}

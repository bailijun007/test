package com.blj.pojo;

import java.io.Serializable;

/**
 * (TOrder)实体类
 *
 * @author makejava
 * @since 2020-05-09 10:28:18
 */
public class TOrder implements Serializable {
    private static final long serialVersionUID = -35755724040436094L;
    
    private Integer id;
    /**
    * 商品名称
    */
    private String orderName;
    /**
    * 订单用户信息
    */
    private String orderUser;
    /**
    * 创建时间
    */
    private Long created;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "TOrder{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderUser='" + orderUser + '\'' +
                ", created=" + created +
                '}';
    }
}
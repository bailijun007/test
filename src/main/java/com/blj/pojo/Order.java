package com.blj.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author BaiLiJun  on 2019/12/26
 */
@Data
@Table(name = "t_order")
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_user")
    private String orderUser;
}


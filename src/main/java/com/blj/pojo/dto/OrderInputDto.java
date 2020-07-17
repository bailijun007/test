package com.blj.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author BaiLiJun  on 2019/12/26
 */
@Data
@JsonIgnoreProperties({"id","created"})
@Table(name = "t_order")
public class OrderInputDto implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_user")
    private String orderUser;

    @Column(name = "created")
    private Long created;

}


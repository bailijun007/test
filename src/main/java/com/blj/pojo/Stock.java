package com.blj.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author BaiLiJun  on 2019/12/26
 */
@Table(name = "stock")
@Data
public class Stock implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private Long stock;
}


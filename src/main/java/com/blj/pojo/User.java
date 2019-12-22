package com.blj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private  Long id;

    private  String name;

    private  Integer age;

    private String password;


    public User() {}
    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

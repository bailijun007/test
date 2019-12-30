package com.blj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "t_user")
public class User implements Serializable {
    private  Long id;

    private  String name;

    private  Integer age;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public User(Long id, String name, Integer age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public User() {}
    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(age, user.age) &&
                Objects.equals(password, user.password) &&
                Objects.equals(createDate, user.createDate) &&
                Objects.equals(createTime, user.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, password, createDate, createTime);
    }
}

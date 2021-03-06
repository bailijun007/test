package com.blj.mapper;

import com.blj.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/30
 */
public interface UserMapper  {


    List<User> queryList(Map<String, Object> map);

    User queryById(@Param("id") Long id);

    Integer queryCount(Map<String, Object> map);

    void batchSave(@Param("userList") List<User> userList);

    void batchUpdate(@Param("userList") List<User> userList);

    void updateById(@Param("user") User user);

    int batchUpdateWithTwoParam(@Param("userList") List<User> userList, @Param("id") long id);

    BigDecimal querySum();

    int existTable(@Param("dbName") String dbName, @Param("tableName") String tableName);

    void dropTable(@Param("tableName") String tableName);

    void createUserTable(@Param("tableName") String tableName);

    List<User> queryByTimeInterval(@Param("id") Long id, @Param("createDateBegin") String createDateBegin, @Param("createDateEnd") String createDateEnd, @Param("endLimit") int endLimit);

    List<User> findAll();


    void save(User user);

    User queryByName(@Param("name") String name);

    void deleteById(@Param("id") Long id);

    List<User> queryLikeByName(@Param("name") String name);

    @MapKey("id")
    Map<Long,User> getUserMapByIds(@Param("idList") List<Long> userList);

}

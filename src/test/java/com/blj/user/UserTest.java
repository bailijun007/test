package com.blj.user;

import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.blj.util.CommonIntegerUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/30
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserMapper userMapper;


//    @Autowired
//    private SecondOrderMapper secondOrderMapper;


    /**
     * 批量保存
     */
    @Test
    public void batchSave() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            User user = new User();
            user.setCreateDate(LocalDate.now())
                    .setCreateTime(LocalDateTime.now())
                    .setName("李四" + i)
                    .setAge(20 + i)
                    .setPassword("123456" + i);
            userList.add(user);
        }
        userMapper.batchSave(userList);

    }

    /**
     * 批量更新
     * 注意：这种方法必须在配置连接数据库url后面带一个参数 &allowMultiQueries=true，
     * 表示允许批量操作，例 jdbc:mysql://localhost:3306/mysqlTest?characterEncoding=utf-8&allowMultiQueries=true
     */
    @Test
    public void batchUpdateWithTwoParam() {
        List<User> userList = new ArrayList<>();
        long id = 1000L;
        for (int i = 1; i <= 7; i++) {
            User user = new User();
            user.setAge(60 + i);
            userList.add(user);
        }

        final int i = userMapper.batchUpdateWithTwoParam(userList, id);
        System.out.println("i = " + i);
    }


    @Test
    public void batchUpdate2() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            User user = new User();
            user.setAge(30 + i);
            user.setId(Long.parseLong(i + ""));

            userList.add(user);
        }

        userMapper.batchUpdate(userList);
    }

    @Test
    public void queryAll() {
        List<User> list = userMapper.findAll();
        for (User user : list) {
            System.out.println("user = " + user);
        }
    }

    /**
     * 模拟跨数据库查询
     * userMapper 数据库是：boot_test,
     * secondOrderMapper 数据库是：boot_test2
     */
    @Test
    public void testManyDataSource(){
        List<User> list = userMapper.findAll();
        for (User user : list) {
            System.out.println("user = " + user);
        }
//         List<Order> orderList = secondOrderMapper.findAll();
//        for (Order order : orderList) {
//            System.out.println("order = " + order);
//        }
    }

    @Test
    public void testCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", "20");
        Integer count = userMapper.queryCount(map);
        System.out.println("count = " + count);
    }

    @Test
    public void testSum() {
        BigDecimal count = userMapper.querySum();
        System.out.println("count = " + count);
    }

    @Test
    public void test4() {
        final List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println("user = " + user);
        }
        final PageInfo<User> info = new PageInfo<>(userList);
        System.out.println("总条数为：" + info.getTotal());

    }


    /**
     * 判断某个库的某个表是否存在
     */
    @Test
    public void testExistTable() {
        int i = userMapper.existTable("boot_test", "t_user");
        if (i == 1) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }



    /**
     * 删除某个表
     */
    @Test
    public void testDropTable() {
        userMapper.dropTable("t_test");
        System.out.println("删除成功");
    }


    /**
     * 创建某个表
     */
    @Test
    public void testCreateUserTable() {
        String tableName = "t_user2";
        userMapper.createUserTable(tableName);
        System.out.println("创建" + tableName + "成功！");
    }

    /**
     * 是否创建某个表，存在则过滤，不存在就创建
     */
    @Test
    public void testIsCreateTable(){
        String tableName = "t_user2";
        int i = userMapper.existTable("boot_test", tableName);
        if (i == 1) {
            System.out.println("已经存在");
        } else {
            System.out.println("不存在,则创建");
            userMapper.createUserTable(tableName);
            System.out.println("创建成功");
        }
    }

    /**
     * 是否删除某个表
     */
    @Test
    public void testIsDropTable(){
        String tableName = "t_user2";
        int i = userMapper.existTable("boot_test", tableName);
        if (i == 1) {
            System.out.println("存在，则删除");
            userMapper.dropTable(tableName);
            System.out.println("删除成功！");
        } else {
            System.out.println("不存在这个表");
        }
    }

    @Test
    public void testDemo(){
        boolean numeric = CommonIntegerUtil.isNumeric("-19162431.1254");
        System.out.println("numeric = " + numeric);

        boolean numeric2 = CommonIntegerUtil.isNumeric("-98877.54");
        System.out.println("numeric2 = " + numeric2);

        boolean numeric3 = CommonIntegerUtil.isNumeric("98877.54");
        System.out.println("numeric3 = " + numeric3);

    }

}

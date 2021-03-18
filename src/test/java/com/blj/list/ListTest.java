package com.blj.list;

import com.blj.pojo.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/1/14
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class ListTest {

    /**
     * java把一个list中的内容添加到另一个list中
     */
    @Test
    public void test1() {
        List<Integer> oneList = new ArrayList<>();
        oneList.add(1);
        oneList.add(2);
        oneList.add(3);

        List<Integer> twoList = new ArrayList<>();
        twoList.add(4);
        twoList.addAll(oneList);
        for (Integer integer : twoList) {
            System.out.println("isNumeric = " + integer);
        }
    }

    //空集合
    @Test
    public void test2() {
        List<Object> list = Collections.emptyList();
        System.out.println("list = " + list);
    }

    @Test
    public void test4() {
        List<Object> list = Collections.emptyList();
        System.out.println("list = " + list);

        List<User> list2=new ArrayList<>();
        if (1==1){
            System.out.println("list2 = " + list2);
        }

    }


    @Test
    public void test3() {
        List<Integer> list = Collections.emptyList();
        System.out.println("list = " + list);
        List<Integer> oneList = new ArrayList<>();
        oneList.addAll(list);
        System.out.println("oneList = " + oneList);
    }

    /**
     * 将list集合按指定长度进行切分，返回新的List<List<??>>集合
     * 注意：需要引用第三方jar包
     * <dependency>
     *                 <groupId>com.google.guava</groupId>
     *                 <artifactId>guava</artifactId>
     *                 <version>23.0</version>
     *             </dependency>
     */
    @Test
    public void testPartition() {
        List<Integer> list= Lists.newArrayList(1,2,3,4,5,6,7);
        List<List<Integer>> partition = Lists.partition(list, 3);
        for (List<Integer> integers : partition) {
            System.out.println("integers = " + integers);
        }

    }

}

package com.blj.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/1/14
 */
@SpringBootTest
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
    public void test3() {
        List<Integer> list = Collections.emptyList();
        System.out.println("list = " + list);
        List<Integer> oneList = new ArrayList<>();
        oneList.addAll(list);
        System.out.println("oneList = " + oneList);
    }

}

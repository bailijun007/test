package com.blj.map;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun on 2020/7/28 10:29 上午
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestConcurrentHashMap {

    @Test
    public void  test(){
        int sc=-1;
        int n = (sc > 0) ? sc : 0;
        System.out.println("n = " + n);
    }




}

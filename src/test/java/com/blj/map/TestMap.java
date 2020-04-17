package com.blj.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author BaiLiJun  on 2020/4/16
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestMap {
    @Test
    public void test12() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        System.out.println("map = " + map);
        if(map.size()>=2){
            map.clear();
            System.out.println("map = " + map);
        }
        map.put("d","d");
        System.out.println("map = " + map.toString());
    }
}

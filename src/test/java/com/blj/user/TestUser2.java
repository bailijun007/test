package com.blj.user;

import java.util.concurrent.TimeUnit;

/**
 * @author BaiLiJun  on 2020/4/28
 */
public class TestUser2 {
    public static void main(String[] args) {
        System.out.println("===============" );
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("222222222");
    }
}

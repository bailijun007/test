package com.blj.md5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Md5Test {

    @Test
    public void test1() {
        String pw = "123456";
        String md5 = DigestUtils.md5DigestAsHex(pw.getBytes());
        System.out.println("md5 = " + md5);

    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String pw = "123456";
        String md5 = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(pw.getBytes("utf-8")).getBytes());
        System.out.println("md5 = " + md5);
    }

}

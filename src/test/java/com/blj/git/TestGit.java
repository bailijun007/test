package com.blj.git;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试git 版本回退功能
 * @author BaiLiJun  on 2020/5/28
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestGit {
    @Test
    public void test(){
        System.out.println("当前git版本为3");
    }

}

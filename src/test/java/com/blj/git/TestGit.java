package com.blj.git;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试git 版本回退功能
 *
 * @author BaiLiJun  on 2020/5/28
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestGit {
    @Test
    public void test() {

        // i++是先被操作数栈拿去用了（先执行的load指令），然后再在局部变量表中完成了自增，但是操作数栈中还是自增前的值
        //而++1是先在局部变量表中完成了自增（先执行innc指令），然后再被load进了操作数栈，所以操作数栈中保存的是自增后的值
        //这就是它们的根本区别
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j = (j++);
        }
        System.out.println(j);


    }


}

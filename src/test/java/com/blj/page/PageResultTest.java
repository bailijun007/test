package com.blj.page;

import com.blj.common.util.PageResult;
import com.blj.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/1/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageResultTest {

    @Test
    public void test1() {
        PageResult<User> pageResult=new PageResult<>();
        System.out.println(pageResult.getList());

    }






}

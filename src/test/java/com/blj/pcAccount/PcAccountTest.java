package com.blj.pcAccount;

import com.blj.mapper.bootTest1.PcAccountMapper;
import com.blj.pojo.PcAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PcAccountTest {
    @Autowired
    private PcAccountMapper pcAccountMapper;


    @Test
    public void test1(){
      List<Long> ids=new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        System.out.println("ids = " + ids);
        List<PcAccount> pcAccounts = pcAccountMapper.queryList(ids);
        System.out.println("pcAccounts = " + pcAccounts);
    }

    @Test
    public void test3(){
        List<Long> ids=new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        System.out.println("ids = " + ids);
        Map<String, Object> map=new HashMap<>();

        map.put("userIdList",ids);
        List<PcAccount> pcAccounts = pcAccountMapper.queryList2(map);
        System.out.println("pcAccounts = " + pcAccounts);
    }


    @Test
    public void test2(){


    }

}

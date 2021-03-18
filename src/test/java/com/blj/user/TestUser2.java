package com.blj.user;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.fastjson.JSON;
import com.blj.pojo.KlineDataPo;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author BaiLiJun  on 2020/4/28
 */
public class TestUser2 {



    public static void main(String[] args) {

        Long time=1586044877820L;
        time= time+60*1000;
        System.out.println("time = " + time);
    }
}

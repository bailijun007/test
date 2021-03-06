package com.blj.bigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class BigDecimalTest2 {

    /**
     * 最小值
     */
    @Test
    public void test1() {
        BigDecimal bigDecimal = new BigDecimal("456.15");
        BigDecimal bigDecima2 = new BigDecimal("4215.15");
        final BigDecimal min = bigDecimal.min(bigDecima2);
        System.out.println("min = " + min);

    }

    /**
     * 最大值
     */
    @Test
    public void test2() {
        BigDecimal bigDecimal = new BigDecimal("456.15");
        BigDecimal bigDecima2 = new BigDecimal("4215.15");
        final BigDecimal max = bigDecimal.max(bigDecima2);
        System.out.println("max = " + max);

    }

    @Test
    public void test3() {
        BigDecimal bigDecimal = new BigDecimal("10.00");
        BigDecimal bigDecima2 = new BigDecimal("3.00");

        BigDecimal[] decimals = bigDecimal.divideAndRemainder(bigDecima2);
        System.out.println(decimals[0]);
        System.out.println(decimals[1]);
    }

    /**
     * BigDecimal 转成double
     */
    @Test
    public void test4() {
        BigDecimal bigDecimal = new BigDecimal("148651534.15645");
        final double v = bigDecimal.doubleValue();
        System.out.println("v = " + v);
    }


    /**
     * BigDecimal 去掉末尾多余的0
     */
    @Test
    public void test5() {
        BigDecimal bigDecimal = new BigDecimal("148651534.156450000");
        System.out.println("bigDecimal = " + bigDecimal);

        System.out.println("去掉末尾的0，bigDecimal ="+bigDecimal.stripTrailingZeros());

    }


    @Test
    public void test6() {
        BigDecimal bigDecimal = new BigDecimal("1234");
        //输出结果：相反数，bigDecimal=-1234
        System.out.println("相反数，bigDecimal="+bigDecimal.negate());
    }

    /**
     * bigDecimal.equals:
     *          将这个BigDecimal与指定的对象进行比较以获得相等性。与compareTo不同的是，
     *           该方法只在两个BigDecimal对象的值和比例相等时才认为它们是相等的(因此，与此方法相比，2.0不等于2.00)
     *
     * 结论：
     *          对于BigDecimal的大小比较，equals方法不仅比较值的大小，还比较两个对象的精确度，
     *           而compareTo方法则不比较精确度，只比较数值的大小。
     *
     */
    @Test
    public void test7() {
        BigDecimal bigDecimal = new BigDecimal("0.00");
        final BigDecimal zero = BigDecimal.ZERO;
        System.out.println("equals 方法比较结果为："+bigDecimal.equals(zero)); //false

        if (bigDecimal.compareTo(zero)==0) {//true
            System.out.println("二者相等");
        }else {//false
            System.out.println("二者不相等");
        }


    }


    /**
     * 小数点取舍
     */
    @Test
    public void test8() {
         BigDecimal bigDecimal = new BigDecimal("12.4564");
         BigDecimal bigDecimal1 = bigDecimal.setScale(2, RoundingMode.UP);
        System.out.println("小数点进一位 = " + bigDecimal1);

        BigDecimal bigDecimal2 = bigDecimal.setScale(2, RoundingMode.DOWN);
        System.out.println("小数点退一位 = " + bigDecimal2);

        BigDecimal bigDecimal3 = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        System.out.println("小数点四舍五入 = " + bigDecimal3);
    }

    /**
     * 绝对值
     */
    @Test
    public void test9() {
        BigDecimal a = new BigDecimal("12.4564");
        BigDecimal b = new BigDecimal("10.4");
        final BigDecimal abs = b.subtract(a).abs();
        System.out.println("绝对值 = " + abs);
    }

    /**
     * 生成中位数
     */
    @Test
    public void test10() {
        List<Long> ids= Arrays.asList(100L,90L,80L,70L);
        // 生成中位数
        Long j;
        if (ids.size() % 2 == 0) {
            j = (ids.get(ids.size() / 2 - 1) + ids.get(ids.size() / 2)) / 2;
            System.out.println("中位数为" + j);
        } else {
            j = ids.get(ids.size() / 2);
            System.out.println("中位数为" + j);
        }

    }

    /**
     * BigDecimal 中位数
     */
    @Test
    public void test11() {
        List<BigDecimal> ids= Arrays.asList(new BigDecimal("100"),new BigDecimal("90"),new BigDecimal("80"),new BigDecimal("70"));
        // 生成中位数
        BigDecimal j;
        if (ids.size() % 2 == 0) {
            j = (ids.get(ids.size() / 2 - 1) .add(ids.get(ids.size() / 2)) ) .divide(new BigDecimal("2"));
            System.out.println("中位数为" + j);
        } else {
            j = ids.get(ids.size() / 2);
            System.out.println("中位数为" + j);
        }

    }

    @Test
    public void test12() {
        BigDecimal bigDecimal = new BigDecimal("456");
        BigDecimal divide = bigDecimal.divide(new BigDecimal("1"), 4);

        System.out.println("divide = " + divide);

    }


}

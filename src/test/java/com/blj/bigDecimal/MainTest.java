package com.blj.bigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("0.00");
        System.out.println(bigDecimal.toString());
        BigDecimal scale = bigDecimal.setScale(2, RoundingMode.DOWN);
        System.out.println("scale = " + scale);
    }
}

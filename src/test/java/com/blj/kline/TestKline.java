package com.blj.kline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author BaiLiJun  on 2020/3/19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestKline {

    TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

    @Test
    public void test1(){
        List<Integer> supportFrequence = new CopyOnWriteArrayList<>();
        String supportFrequenceString="1,5,10,15,30,60,360,1440";
        final String[] freqs = supportFrequenceString.split(",");
        for (String freq : freqs) {
            supportFrequence.add(Integer.valueOf(freq));
        }
        supportFrequence.sort(Comparator.naturalOrder());

        for (int i = 0; i < supportFrequence.size(); i++) {
            if (i == 0) {
                continue;
            } else {
                map.put(supportFrequence.get(i), 1);
            }
        }

        for (Integer tarFreq : map.keySet()) {
            final Integer oldTriggerFreq = map.get(tarFreq);
            for (Integer nextTriggerFreq : supportFrequence) {
                if (nextTriggerFreq >= tarFreq) {
                    break;
                }
                if (nextTriggerFreq < tarFreq && nextTriggerFreq > oldTriggerFreq) {
                    BigDecimal remainder = BigDecimal.valueOf(tarFreq).divideAndRemainder(BigDecimal.valueOf(nextTriggerFreq))[1];
                    if (0 == BigDecimal.ZERO.compareTo(remainder)) {
                        map.put(tarFreq, nextTriggerFreq);
                    }
                }
            }
        }

        for (Integer integer : map.keySet()) {
            System.out.println(integer+"是由"+map.get(integer)+"触发");
        }

    }

    @Test
    public void test2(){
        test1();
        TreeMap<Integer, TreeSet<Integer>> trigger2Tar = new TreeMap<>();
        final Set<Map.Entry<Integer, Integer>> kvs = map.entrySet();
        for (Map.Entry<Integer, Integer> kv : kvs) {
            final Integer targetFreq = kv.getKey();
            final Integer triggerFreq = kv.getValue();
            TreeSet<Integer> targetFreqs = trigger2Tar.get(triggerFreq);
            if (targetFreqs == null) {
                targetFreqs = new TreeSet<Integer>();
                trigger2Tar.put(triggerFreq, targetFreqs);
            }
            targetFreqs.add(targetFreq);
        }
        for (Integer integer : trigger2Tar.keySet()) {
            System.out.println(integer+"触发"+trigger2Tar.get(integer));
        }
    }

}

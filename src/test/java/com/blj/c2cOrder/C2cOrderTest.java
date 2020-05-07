package com.blj.c2cOrder;

import com.blj.mapper.bootTest1.C2cOrderMapper;
import com.blj.pojo.C2cOrder;
import com.blj.pojo.NotifyParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

/**
 * @author BaiLiJun  on 2020/1/6
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class C2cOrderTest {
    private static final Logger logger = LoggerFactory.getLogger(C2cOrderTest.class);

    @Autowired
    private C2cOrderMapper c2cOrderMapper;

    @Test
    public void test1() {
        NotifyParam param = new NotifyParam();

        param.setOrderNo("c2c157827741489168");
        param.setOrderAmount(new BigDecimal(700));
        param.setOrderCurrency("USDT");
        param.setPaymentAmount(new BigDecimal(700));
        param.setTransactionId("c2c1578277414891680000");
        param.setStatus("success");
        param.setSignType("MD5");
        param.setSign("15e6edae127a7157ef510b356937ba4d");

        //更新一条c2c订单记录
        C2cOrder c2cOrder = new C2cOrder();
        BigDecimal orderAmount = param.getOrderAmount();
        c2cOrder.setAmount(orderAmount);
        //计算USDT 就是 orderAmount* 0.975 / 你系统的CNY:USD汇率;
        BigDecimal volume = param.getOrderAmount().multiply(new BigDecimal("0.975")).divide(new BigDecimal("7"));
        c2cOrder.setVolume(volume);
        //价格=总额/volume
        BigDecimal price = orderAmount.divide(volume, 18, RoundingMode.HALF_DOWN).stripTrailingZeros();
        c2cOrder.setPrice(price);
        if (param.getStatus().equals("success")) {
            c2cOrder.setPayStatus(1);
        } else {
            c2cOrder.setPayStatus(2);
        }
        c2cOrder.setPayFinishTime(Instant.now().toEpochMilli());
        c2cOrder.setSynchStatus(1);
        c2cOrder.setApprovalStatus(1);
        c2cOrder.setModified(Instant.now().toEpochMilli());
        c2cOrder.setSn(param.getOrderNo());
        c2cOrderMapper.updateBySn(c2cOrder);


    }

}

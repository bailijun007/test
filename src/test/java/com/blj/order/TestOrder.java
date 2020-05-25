package com.blj.order;

import com.blj.mapper.TOrderDao;
import com.blj.pojo.TOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author BaiLiJun  on 2020/5/9
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestOrder {
    @Autowired
  private   TOrderDao orderDao;

    @Test
    public void test1(){
        TOrder tOrder = new TOrder();
        tOrder.setOrderName("watch");
        final List<TOrder> tOrders = orderDao.queryAll(tOrder);
        for (TOrder order : tOrders) {
            System.out.println("order = " + order);
        }
    }

}

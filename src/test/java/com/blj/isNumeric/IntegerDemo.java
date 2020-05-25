package com.blj.isNumeric;

import com.blj.util.CommonIntegerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/5/15
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class IntegerDemo {
    @Test
    public void testDemo(){
         boolean numeric = CommonIntegerUtil.isNumeric("-98877.s");
        System.out.println("numeric = " + numeric);

        boolean numeric2 = CommonIntegerUtil.isNumeric("-98877.54");
        System.out.println("numeric2 = " + numeric2);

        boolean numeric3 = CommonIntegerUtil.isNumeric("98877.54");
        System.out.println("numeric3 = " + numeric3);

    }

}

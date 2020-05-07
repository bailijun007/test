package manyDataSource;

import com.blj.mapper.bootTest1.UserMapper;
import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author BaiLiJun  on 2020/5/7
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestUser {
    @Autowired
    private UserMapper masterUserMapper;


    @Test
    public void queryAll() {
        List<User> list = masterUserMapper.findAll();
        for (User user : list) {
            System.out.println("user = " + user);
        }
    }

}

package org.white.worm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.white.worm.jdbc.WJdbcTemplate;
import org.white.worm.test.entity.UserDO;

import javax.annotation.Resource;

/**
 * <p></p >
 *
 * @author baixiong
 * @version $Id: TestOrm.java, v 0.1 2019年09月03日 16:27:00 baixiong Exp$
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@ComponentScan("org.white.worm")
@SpringBootTest
@EnableAspectJAutoProxy
public class TestOrm {

    @Resource
    private WJdbcTemplate wJdbcTemplate;

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        int count = 0;
        for (long i = 0; i < 1000; i++) {
            userDO.setId(i);
            userDO.setUserName("white" + i);
            userDO.setRealName("白" + i);
            userDO.setPhone("13100000000");
            userDO.setSex((short) (i % 2));
            count += wJdbcTemplate.insert(userDO);
        }
        System.out.println(count);
    }
}

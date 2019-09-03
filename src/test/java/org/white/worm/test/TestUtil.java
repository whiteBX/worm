package org.white.worm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.white.worm.cache.OrmHolder;
import org.white.worm.test.entity.UserDO;
import org.white.worm.util.ReflectUtil;
import org.white.worm.util.SqlUtil;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: TestUtil.java, v 0.1 2019年09月02日 17:31:00 white Exp$
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUtil {


    @Test
    public void testReflectUtil() {
        Class clazz = UserDO.class;
        System.out.println(ReflectUtil.parseTable(clazz));
        System.out.println(ReflectUtil.parseColumn(clazz, "id"));
    }

    @Test
    public void testSqlUtil() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setUserName("white");
        userDO.setRealName("白");
        userDO.setPhone("13100000000");
        userDO.setSex((short) 1);
        String sql = SqlUtil.buildInsertSQL(ReflectUtil.parseTable(UserDO.class), userDO);
        System.out.println(sql);
    }
}

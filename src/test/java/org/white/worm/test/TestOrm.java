package org.white.worm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.white.worm.entity.DataSourceMeta;
import org.white.worm.jdbc.WJdbcConnection;
import org.white.worm.strategy.SplitStrategyFactory;
import org.white.worm.test.entity.UserDO;
import org.white.worm.util.SqlUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: TestOrm.java, v 0.1 2019年09月03日 16:27:00 white Exp$
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@ComponentScan("org.white.worm")
@SpringBootTest
@EnableAspectJAutoProxy
public class TestOrm {

    @Resource
    private DataSource dataSource;

    public List<DataSourceMeta> dataSourceMetaList() {
        List<DataSourceMeta> dataSourceMetaList = new ArrayList<>();
        DataSourceMeta meta = new DataSourceMeta();
        meta.setDbName("db0");
        meta.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/db0?charset=utf8mb4&useSSL=false&serverTimezone=GMT");
        meta.setUserName("root");
        meta.setPassword("");
        dataSourceMetaList.add(meta);
        DataSourceMeta meta1 = new DataSourceMeta();
        meta1.setDbName("db1");
        meta1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/db1?charset=utf8mb4&useSSL=false&serverTimezone=GMT");
        meta1.setUserName("root");
        meta1.setPassword("");
        dataSourceMetaList.add(meta1);
        return dataSourceMetaList;
    }

    @Test
    public void testSplit() {
        WJdbcConnection wJdbcConnection = WJdbcConnection.getInstance(dataSourceMetaList());
        for (long i = 0; i < 100; i++) {
            UserDO userDO = new UserDO();
            userDO.setId(i);
            userDO.setUserName("white" + i);
            userDO.setRealName("白老大" + i);
            userDO.setPhone("13100000000");
            userDO.setSex((short) (i % 2));
            wJdbcConnection.insert(userDO);
        }
    }

    @Test
    public void testDataSource() throws SQLException {
        UserDO userDO = new UserDO();
        long i = 10;
        userDO.setId(i);
        userDO.setUserName("white" + i);
        userDO.setRealName("白" + i);
        userDO.setPhone("13100000000");
        userDO.setSex((short) (i % 2));
        String sql = SqlUtil.buildInsertSQL(SplitStrategyFactory.STRATEGY.getRealTableName(userDO), userDO);
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        connection.prepareStatement(sql).execute();
        connection.commit();
    }
}

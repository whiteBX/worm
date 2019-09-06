package org.white.worm.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.white.worm.constant.ErrorCode;
import org.white.worm.entity.DataSourceMeta;
import org.white.worm.exception.BizException;
import org.white.worm.strategy.SplitStrategyFactory;
import org.white.worm.util.AssertUtil;
import org.white.worm.util.SqlUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: WJdbcConnection.java, v 0.1 2019年09月06日 15:02:00 white Exp$
 */
public class WJdbcConnection implements JdbcOperation {

    /**
     * 连接串context
     */
    private ConnectionContext connectionContext;
    /**
     * dataSource
     */
    private Map<String, DataSource> dataSourceMap = new HashMap<>();
    /**
     * 单例
     */
    private static WJdbcConnection INSTANCE;

    private WJdbcConnection(List<DataSourceMeta> dataSourceMetaList) throws SQLException, InterruptedException {
        AssertUtil.notEmpty(dataSourceMetaList, ErrorCode.ACCESS_ERROR, "dataSourceMeta is empty!");
        for (DataSourceMeta dataSourceMeta : dataSourceMetaList) {
            dataSourceMap.put(dataSourceMeta.getDbName(), DataSourceBuilder.create()
                    .type(HikariDataSource.class)
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .url(dataSourceMeta.getJdbcUrl())
                    .username(dataSourceMeta.getUserName())
                    .password(dataSourceMeta.getPassword())
                    .build());
        }
        connectionContext = new ConnectionContext(dataSourceMap);
    }

    public static WJdbcConnection getInstance(List<DataSourceMeta> dataSourceMetaList) {
        try {
            if (INSTANCE != null) {
                return INSTANCE;
            }
            synchronized (WJdbcConnection.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WJdbcConnection(dataSourceMetaList);
                }
                return INSTANCE;
            }
        } catch (Exception e) {
            throw new BizException(ErrorCode.ACCESS_ERROR, "init WJdbcConnection error" + e);
        }
    }

    @Override
    public void insert(Object obj) {
        Connection connection = null;
        String dbName = SplitStrategyFactory.STRATEGY.getRealDbName(obj);
        try {
            connection = connectionContext.getConnection(dbName);
            String sql = SqlUtil.buildInsertSQL(SplitStrategyFactory.STRATEGY.getRealTableName(obj), obj);
            connection.prepareStatement(sql).execute();
        } catch (InterruptedException e) {
            throw new BizException(ErrorCode.ACCESS_ERROR, "get connection error" + e);
        } catch (SQLException e) {
            throw new BizException(ErrorCode.ACCESS_ERROR, "execute sql error" + e);
        } finally {
            if (connection != null) {
                try {
                    connectionContext.releaseConnection(dbName, connection);
                } catch (InterruptedException e) {
                    // add error log
                    throw new BizException(ErrorCode.ACCESS_ERROR, "error: release connection failed");
                }
            }
        }
    }
}

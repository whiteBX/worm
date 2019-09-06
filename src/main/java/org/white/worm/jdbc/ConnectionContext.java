package org.white.worm.jdbc;

import org.springframework.util.CollectionUtils;
import org.white.worm.constant.ErrorCode;
import org.white.worm.util.AssertUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: ConnectionContext.java, v 0.1 2019年09月06日 14:27:00 white Exp$
 */
public class ConnectionContext {
    /**
     * 默认连接池大小
     */
    private static Integer CONNECTION_POOL_SIZE = 10;
    /**
     * 连接池
     */
    private Map<String, LinkedBlockingQueue<Connection>> CONNECTIONS = new ConcurrentHashMap<>();

    public ConnectionContext(Map<String, DataSource> dataSourceMap) throws SQLException, InterruptedException {
        if (CollectionUtils.isEmpty(dataSourceMap)) {
            return;
        }
        for (Map.Entry<String, DataSource> dataSource : dataSourceMap.entrySet()) {
            LinkedBlockingQueue<Connection> connectionSet = new LinkedBlockingQueue<>();
            for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
                connectionSet.put(dataSource.getValue().getConnection());
            }
            CONNECTIONS.put(dataSource.getKey(), connectionSet);
        }
    }

    /**
     * 获取connection
     */
    public Connection getConnection(String dbName) throws InterruptedException {
        AssertUtil.isTrue(CONNECTIONS.containsKey(dbName), ErrorCode.ACCESS_ERROR, "db not register");
        LinkedBlockingQueue<Connection> connectionQueen = CONNECTIONS.get(dbName);
        return connectionQueen.take();
    }

    /**
     * 释放connection
     */
    public void releaseConnection(String dbName, Connection connection) throws InterruptedException {
        CONNECTIONS.get(dbName).put(connection);
    }
}

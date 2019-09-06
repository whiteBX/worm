package org.white.worm.entity;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: DataSourceMeta.java, v 0.1 2019年09月06日 11:40:00 white Exp$
 */
public class DataSourceMeta {
    /** 数据库名 */
    private String dbName;
    /** 数据库URL */
    private String jdbcUrl;
    /** 数据库用户名 */
    private String userName;
    /** 数据库密码 */
    private String password;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

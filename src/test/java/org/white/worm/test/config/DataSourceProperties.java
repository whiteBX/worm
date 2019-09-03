package org.white.worm.test.config;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: DataSourceProperties.java, v 0.1 2019年09月03日 17:20:00 white Exp$
 */
public class DataSourceProperties {
    private String driverClassName;
    private String jdbcUrl;
    private String user;
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

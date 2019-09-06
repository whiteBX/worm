package org.white.worm.test.entity;

import org.white.worm.aspect.Column;
import org.white.worm.aspect.DataBase;
import org.white.worm.aspect.SplitKey;
import org.white.worm.aspect.Table;

import java.sql.JDBCType;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: UserDO.java, v 0.1 2019年09月02日 16:30:00 white Exp$
 */
@DataBase("db")
@Table("user_")
@SplitKey(column = "id", dbNum = 2, tableNum = 8)
public class UserDO {

    @Column(value = "id", columnType = JDBCType.BIGINT)
    private Long id;
    @Column(value = "user_name", columnType = JDBCType.VARCHAR)
    private String userName;
    @Column(value = "real_name", columnType = JDBCType.VARCHAR)
    private String realName;
    @Column(value = "phone", columnType = JDBCType.VARCHAR)
    private String phone;
    @Column(value = "sex", columnType = JDBCType.TINYINT)
    private short sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public short getSex() {
        return sex;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }
}

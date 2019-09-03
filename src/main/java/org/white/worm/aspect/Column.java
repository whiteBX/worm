package org.white.worm.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: Colume.java, v 0.1 2019年09月02日 11:03:00 white Exp$
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String value();

    JDBCType columnType();
}

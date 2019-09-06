package org.white.worm.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: SplitKey.java, v 0.1 2019年09月02日 17:51:00 white Exp$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SplitKey {
    /**
     * 分库分表的列,其类型必须为Long
     */
    String column();

    /**
     * 分库数量
     */
    int dbNum();

    /**
     * 分表数量
     */
    int tableNum();
}

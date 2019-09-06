package org.white.worm.strategy;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: SpiltStrategy.java, v 0.1 2019年09月06日 11:57:00 white Exp$
 */
public interface SplitStrategy {

    /**
     * 获取真实db名
     */
    String getRealDbName(Object o);

    /**
     * 获取真实表名
     */
    String getRealTableName(Object o);
}

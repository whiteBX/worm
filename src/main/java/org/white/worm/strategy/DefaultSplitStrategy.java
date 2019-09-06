package org.white.worm.strategy;

import org.white.worm.aspect.SplitKey;
import org.white.worm.cache.OrmHolder;
import org.white.worm.constant.ErrorCode;
import org.white.worm.entity.TableMeta;
import org.white.worm.util.AssertUtil;
import org.white.worm.util.ReflectUtil;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: DefaultSplitStrategy.java, v 0.1 2019年09月02日 18:51:00 white Exp$
 */
public class DefaultSplitStrategy implements SplitStrategy {

    @Override
    public String getRealDbName(Object o) {
        TableMeta tableMeta = OrmHolder.get(o.getClass());
        AssertUtil.notNull(tableMeta, ErrorCode.ACCESS_ERROR, "");
        // not split
        if (tableMeta.getSplitKey() == null) {
            return tableMeta.getTableName();
        }
        SplitKey splitKey = tableMeta.getSplitKey();
        Object splitValue = ReflectUtil.getFieldValue(o, splitKey.column());
        AssertUtil.isTrue(splitValue instanceof Long, ErrorCode.ACCESS_ERROR, "split key must be long: " + splitKey.column());
        return tableMeta.getDbName() + ((Long) splitValue % splitKey.tableNum()) / (splitKey.tableNum() / splitKey.dbNum());
    }

    @Override
    public String getRealTableName(Object o) {
        TableMeta tableMeta = OrmHolder.get(o.getClass());
        AssertUtil.notNull(tableMeta, ErrorCode.ACCESS_ERROR, "");
        // not split
        if (tableMeta.getSplitKey() == null) {
            return tableMeta.getTableName();
        }
        SplitKey splitKey = tableMeta.getSplitKey();
        Object splitValue = ReflectUtil.getFieldValue(o, splitKey.column());
        AssertUtil.isTrue(splitValue instanceof Long, ErrorCode.ACCESS_ERROR, "split key must be long: " + splitKey.column());
        return tableMeta.getTableName() + (Long) splitValue % splitKey.tableNum();
    }
}

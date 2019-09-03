package org.white.worm.util;

import org.white.worm.cache.OrmHolder;
import org.white.worm.entity.TableMeta;

import java.util.List;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: SqlUtil.java, v 0.1 2019年09月02日 19:36:00 white Exp$
 */
public class SqlUtil {

    public static String buildInsertSQL(String tableName, Object obj) {
        StringBuilder sql = new StringBuilder();
        StringBuilder valueStr = new StringBuilder();
        sql.append("Insert into ").append(tableName).append(" (");
        valueStr.append("VALUES (");
        List<TableMeta.ColumnMeta> columnMetaList = OrmHolder.get(obj.getClass()).getColumnMetaList();
        for (TableMeta.ColumnMeta columnMeta : columnMetaList) {
            sql.append(columnMeta.getColumn()).append(",");
            valueStr.append("'").append(ReflectUtil.getFieldValue(obj, columnMeta.getField())).append("',");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        valueStr.deleteCharAt(valueStr.lastIndexOf(","));
        valueStr.append(")");
        sql.append(") ").append(valueStr);
        return sql.toString();
    }
}

package org.white.worm.util;

import org.white.worm.aspect.Column;
import org.white.worm.aspect.SplitKey;
import org.white.worm.aspect.Table;
import org.white.worm.constant.ErrorCode;
import org.white.worm.entity.TableMeta;
import org.white.worm.exception.BizException;

import java.lang.reflect.Field;

/**
 * <p>反射工具类</p >
 *
 * @author white
 * @version $Id: ReflectUtil.java, v 0.1 2019年09月02日 17:01:00 white Exp$
 */
public class ReflectUtil {

    /**
     * 解析表名
     */
    public static String parseTable(Class clazz) {
        AssertUtil.isTrue(clazz.isAnnotationPresent(Table.class), ErrorCode.ACCESS_ERROR, "class need annotation Table");
        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);
        return table.value();
    }

    /**
     * 解析分表规则
     */
    public static SplitKey parseSplitKey(Class clazz) {
        AssertUtil.isTrue(clazz.isAnnotationPresent(SplitKey.class), ErrorCode.ACCESS_ERROR, "class need annotation SplitKey");
        return (SplitKey) clazz.getDeclaredAnnotation(SplitKey.class);
    }

    /**
     * 解析列
     */
    public static Column parseColumn(Class clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName).getDeclaredAnnotation(Column.class);
        } catch (NoSuchFieldException e) {
            throw new BizException(ErrorCode.ACCESS_ERROR, "parse Column error");
        }
    }

    /**
     * 反射获取对象的属性
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Field field = null;
        boolean access = false;
        try {
            field = obj.getClass().getDeclaredField(fieldName);
            access = field.isAccessible();
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new BizException(ErrorCode.ACCESS_ERROR, "get Field value error: " + e.getMessage());
        } finally {
            field.setAccessible(access);
        }

    }

    /**
     * 解析获取tableMeta
     */
    public static TableMeta parseMeta(Class clazz) {
        TableMeta meta = new TableMeta();
        meta.setTableName(parseTable(clazz));
        meta.setTableClass(clazz);
        meta.setSplitKey(parseSplitKey(clazz));
        for (Field field : clazz.getDeclaredFields()) {
            Column column = parseColumn(clazz, field.getName());
            meta.addColumnMeta(field.getName(), column.value(), column.columnType());
        }
        return meta;
    }
}

package org.white.worm.entity;

import org.white.worm.aspect.SplitKey;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: TableMeta.java, v 0.1 2019年09月02日 17:33:00 white Exp$
 */
public class TableMeta {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 对应实体类型
     */
    private Class tableClass;
    /**
     * 分库分表
     */
    private SplitKey splitKey;
    /**
     * 列属性
     */
    private List<ColumnMeta> columnMetaList;

    public class ColumnMeta {
        /**
         * 字段名
         */
        private String field;
        /**
         * 列名
         */
        private String column;
        /**
         * 列类型
         */
        private JDBCType jdbcType;

        public ColumnMeta(String field, String column, JDBCType jdbcType) {
            this.field = field;
            this.column = column;
            this.jdbcType = jdbcType;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public JDBCType getJdbcType() {
            return jdbcType;
        }

        public void setJdbcType(JDBCType jdbcType) {
            this.jdbcType = jdbcType;
        }
    }

    public ColumnMeta getColumnMetaByColumn(String columnName) {
        for (ColumnMeta columnMeta : columnMetaList) {
            if (columnMeta.equals(columnMeta.column)) {
                return columnMeta;
            }
        }
        return null;
    }

    public ColumnMeta getColumnMetaByField(String field) {
        for (ColumnMeta columnMeta : columnMetaList) {
            if (field.equals(columnMeta.field)) {
                return columnMeta;
            }
        }
        return null;
    }

    public void addColumnMeta(String field, String column, JDBCType jdbcType) {
        if (this.columnMetaList == null) {
            columnMetaList = new ArrayList<>();
        }
        columnMetaList.add(new ColumnMeta(field, column, jdbcType));
    }

    public SplitKey getSplitKey() {
        return splitKey;
    }

    public void setSplitKey(SplitKey splitKey) {
        this.splitKey = splitKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Class getTableClass() {
        return tableClass;
    }

    public void setTableClass(Class tableClass) {
        this.tableClass = tableClass;
    }

    public List<ColumnMeta> getColumnMetaList() {
        return columnMetaList;
    }

    public void setColumnMetaList(List<ColumnMeta> columnMetaList) {
        this.columnMetaList = columnMetaList;
    }
}

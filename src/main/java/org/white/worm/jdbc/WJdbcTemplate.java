package org.white.worm.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.white.worm.strategy.DefaultSplitStrategy;
import org.white.worm.util.SqlUtil;

import javax.sql.DataSource;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: WJdbcTemplate.java, v 0.1 2019年09月02日 19:26:00 white Exp$
 */
public class WJdbcTemplate extends JdbcTemplate implements JdbcOperation {

    public WJdbcTemplate() {
    }

    public WJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public int insert(Object obj) {
        return this.update(SqlUtil.buildInsertSQL(DefaultSplitStrategy.getRealTableName(obj), obj));
    }
}

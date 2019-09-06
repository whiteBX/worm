package org.white.worm.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.white.worm.strategy.SplitStrategyFactory;
import org.white.worm.util.SqlUtil;

import javax.sql.DataSource;

/**
 * <p>只支持分表，不能支持分库</p >
 *
 * @author white
 * @version $Id: WJdbcTemplate.java, v 0.1 2019年09月02日 19:26:00 white Exp$
 */
@Deprecated
public class WJdbcTemplate extends JdbcTemplate implements JdbcOperation {

    public WJdbcTemplate() {
    }

    public WJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public void insert(Object obj) {
        this.update(SqlUtil.buildInsertSQL(SplitStrategyFactory.STRATEGY.getRealTableName(obj), obj));
    }
}

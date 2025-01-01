package com.trading.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DatabaseCleanUp {

    Logger logger = LoggerFactory.getLogger(DatabaseCleanUp.class);
    private final JdbcTemplate jdbcTemplate;

    public DatabaseCleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void truncateTable() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        List<String> tableNames = getTableNames();
        tableNames.forEach(tableName -> {
            String truncateQuery = "TRUNCATE TABLE `" + tableName + "`";
            logger.info(truncateQuery);
            jdbcTemplate.execute(truncateQuery);

            String alterTableQuery = "ALTER TABLE `" + tableName + "` ALTER COLUMN id RESTART WITH 1";
            logger.info(alterTableQuery);
            jdbcTemplate.execute(alterTableQuery);
        });
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }

    private List<String> getTableNames() {
        return jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
                String.class
        );
    }
}

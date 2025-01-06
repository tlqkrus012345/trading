package com.trading.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DatabaseCleanUp {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseCleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void truncateTable() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        List<String> tableNames = getTableNames();
        tableNames.forEach(tableName -> {
            String truncateQuery = "TRUNCATE TABLE `" + tableName + "`";
            jdbcTemplate.execute(truncateQuery);
        });
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }

    private List<String> getTableNames() {
        return jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE()",
                String.class
        );
    }
}

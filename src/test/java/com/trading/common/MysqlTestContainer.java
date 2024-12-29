package com.trading.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
@ActiveProfiles("test")
public class MysqlTestContainer {

    private static final String MYSQL_DOCKER_IMAGE = "mysql";

    static {
        GenericContainer<?> mysql =
                new GenericContainer<>(DockerImageName.parse(MYSQL_DOCKER_IMAGE)).withExposedPorts(3306);
        mysql.start();
    }
}

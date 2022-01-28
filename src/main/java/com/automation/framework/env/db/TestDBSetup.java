package com.automation.framework.env.db;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

public class TestDBSetup implements BeforeAllCallback, AfterAllCallback {

    private static MySQLContainer<?> mysqlDb;
    private static MongoDBContainer mongoDb;

    /**
     * Callback that is invoked once <em>before</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        mysqlDb = new MySQLContainer<>("mysql:latest")
                .withDatabaseName("automation")
                .withUsername("mysql")
                .withPassword("mysql@123")
                .withExposedPorts(3306);

        mysqlDb.start();
        String jdbcUrl = String.format("jdbc:mysql://localhost:%d/prop", mysqlDb.getFirstMappedPort());
        System.setProperty("spring.datasource.mysql.url", jdbcUrl);
        System.setProperty("spring.datasource.mysql.username", "automation");
        System.setProperty("spring.datasource.mysql.username", "mysql");
        System.setProperty("spring.datasource.mysql.password", "mysql@123");

    }

    /**
     * Callback that is invoked once <em>after</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterAll(ExtensionContext context) throws Exception {

    }
}

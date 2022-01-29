package com.automation.framework.env.db;


import com.automation.framework.util.file.PathFinder;
import com.sun.istack.NotNull;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;

public class TestDBSetup implements BeforeAllCallback, AfterAllCallback {

    private static final int MYSQLDB_PORT = 3306;
    private static final int MONGODB_PORT = 27017;

    private static MongoDBContainer mongo;
    private static MySQLContainer mysql;

    static {

        mysql = (MySQLContainer) new MySQLContainer("mysql:latest")
                .withDatabaseName("automation")
                .withUsername("mysql")
                .withPassword("mysql@123")
                .withExposedPorts(MYSQLDB_PORT)
                .withReuse(true);

        mongo = new MongoDBContainer("mongo:latest")
                .withExposedPorts(MONGODB_PORT)
                .withReuse(true);

    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);

        registry.add("spring.data.mongodb.url", mongo::getReplicaSetUrl);
        registry.add("spring.data.mongodb.host", mongo::getHost);
        registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
    }

    @NotNull
    public Integer getPort() {
        return mongo.getMappedPort(MONGODB_PORT);
    }

    /**
     * Callback that is invoked once <em>before</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeAll(ExtensionContext context) {

        mysql.start();
        mongo.start();

        var containerDelegate = new JdbcDatabaseDelegate(mysql, "");

        try {
            URL resource = new PathFinder().getFilePathForFile("automation.sql").toUri().toURL();
            String scripts = IOUtils.toString(resource, StandardCharsets.UTF_8);
            ScriptUtils.executeDatabaseScript(containerDelegate, "/src/test/resources/datasets/automation.sql", scripts);
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }

        assertTrue("Verify MySqlDB container status.", mysql.isRunning());
        assertTrue("Verify MongoDB container status.", mongo.isRunning());
    }

    /**
     * Callback that is invoked once <em>after</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterAll(ExtensionContext context) {
        mysql.stop();
        mongo.stop();
    }
}

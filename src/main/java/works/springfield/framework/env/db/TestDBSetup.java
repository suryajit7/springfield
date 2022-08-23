package works.springfield.framework.env.db;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import works.springfield.framework.util.PathFinder;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;

public class TestDBSetup implements BeforeAllCallback, AfterAllCallback {

    private static final int MYSQLDB_PORT = 3306;
    private static final int MONGODB_PORT = 27017;

    @Autowired
    private PathFinder pathFinder;

    private static MySQLContainer mysql;

    /**
     * Callback that is invoked once <em>before</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        mysql.start();
        runSqlScripts();

        assertTrue("Verify MySqlDB container status.", mysql.isRunning());
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

    static {

        mysql = (MySQLContainer) new MySQLContainer("mysql:latest")
                .withDatabaseName("mydb")
                .withUsername("root")
                .withPassword("mysql@123")
                .withExposedPorts(MYSQLDB_PORT)
                .withReuse(true);
    }


    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }

    private void runSqlScripts() {

        var containerDelegate = new JdbcDatabaseDelegate(mysql, "");
        try {
            URL resource = pathFinder.getFilePathForFile("user-visa.sql").toUri().toURL();
            String scripts = IOUtils.toString(resource, StandardCharsets.UTF_8);
            ScriptUtils.executeDatabaseScript(containerDelegate, "/src/main/resources/script/user-visa.sql", scripts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

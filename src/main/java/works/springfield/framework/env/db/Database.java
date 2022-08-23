package works.springfield.framework.env.db;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;

public enum Database {
    POSTGRESQL {
        @Override
        protected JdbcDatabaseContainer newJdbcDatabaseContainer() {
            return new PostgreSQLContainer(
                    "postgres:13.7"
            );
        }
    },
    MYSQL {
        @Override
        protected JdbcDatabaseContainer newJdbcDatabaseContainer() {
            return new MySQLContainer(
                    "mysql:8.0"
            );
        }
    };

    private JdbcDatabaseContainer container;

    public JdbcDatabaseContainer getContainer() {
        return container;
    }

    public void initContainer(String username, String password) {
        container = (JdbcDatabaseContainer) newJdbcDatabaseContainer()
                .withEnv(Collections.singletonMap("ACCEPT_EULA", "Y"))
                .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"));

        if(supportsDatabaseName()) {
            container.withDatabaseName("high-performance-java-persistence");
        }

        if(supportsCredentials()) {
            container.withUsername(username).withPassword(password);
        }

        container.start();
    }

    protected JdbcDatabaseContainer newJdbcDatabaseContainer() {
        throw new UnsupportedOperationException(
                String.format(
                        "The [%s] database was not configured to use Testcontainers!",
                        name()
                )
        );
    }

    protected boolean supportsDatabaseName() {
        return true;
    }

    protected boolean supportsCredentials() {
        return true;
    }
}

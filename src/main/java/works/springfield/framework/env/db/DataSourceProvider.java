package works.springfield.framework.env.db;


import javax.sql.DataSource;

public interface DataSourceProvider {

    Database database();

    String hibernateDialect();

    DataSource dataSource();

    String url();

    String username();

    String password();
}

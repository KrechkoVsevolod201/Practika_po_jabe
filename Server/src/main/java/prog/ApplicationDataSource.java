package prog;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationDataSource {
    private static final PGSimpleDataSource dataSource;
    private static final Connection connection;

    static {
        dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"localhost:5432"});
        dataSource.setUser("postgres");
        dataSource.setPassword("Pivo");
        dataSource.setDatabaseName("practic");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
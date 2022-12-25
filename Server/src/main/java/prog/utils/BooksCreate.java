package prog.utils;

import lombok.SneakyThrows;
import prog.ApplicationDataSource;

import java.sql.*;

public class BooksCreate implements CommandExecutor {

    private static final String url = "jdbc:postgresql://localhost:5432/practic";
    private static final String user = "postgres";
    private static final String password = "Pivo";

    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }


    public static int selectIdFromTable(Connection connection, String tableName) {
        String query = "select max(id) from " + tableName + "";
        var maxId = 0;
        try (Statement stmt = connection.createStatement()) {
            var rs = stmt.executeQuery(query);

            while (rs.next()) {
                maxId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxId;
    }

    @Override
    public int execute(String text) {
        return createBooks(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_BOOKS;
    }

    @SneakyThrows
    public static int createBooks(String text) {
        String[] words = text.split(" ");
        //StringBuilder title = new StringBuilder();
        String req;
        int set = 0;
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        req = "insert into books(id, book_id, price, author_name) values(" + words[1]
                + "," + words[2] + "," + words[3] + ",'" + words[4] + "'" + ");";
        set = statement.executeUpdate(req);
        statement.close();
        return set;
    }
}
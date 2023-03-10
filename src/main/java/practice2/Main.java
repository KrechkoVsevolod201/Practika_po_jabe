package practice2;

import java.sql.*;
import java.util.Scanner;
import java.sql.ResultSet;

/**
 * Предметная область:
 * Table books
 * CREATE TABLE IF NOT EXISTS books
 * (ID SERIAL PRIMARY KEY  NOT NULL,
 * book_id         INT     NOT NULL,
 * price           INT     NOT NULL,
 * author_name     TEXT    NOT NULL,
 * FOREIGN  KEY (book_id)
 * REFERENCES names (book_id)
 * );
 * Table titles
 * CREATE TABLE IF NOT EXISTS titles
 * (ID SERIAL PRIMARY KEY NOT NULL,
 * book_id          INT     UNIQUE,
 * book_title       TEXT    UNIQUE
 * );
 */
public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/practic";
    private static final String user = "postgres";
    private static final String password = "Pivo";

    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public static void insertInFirstTable(Connection connection, Scanner in) {
        System.out.println("Введите запись в первую таблицу через запятую: price,author_name");
        var str = in.next();
        var temp = str.split(",");
        var price = temp[0];
        var author_name = temp[1];
        var id = selectIdFromTable(connection, "books") + 1;
        try (Statement stmt = connection.createStatement()) {
            var sqlCommand = "Select * from titles";
            ResultSet rs = stmt.executeQuery(sqlCommand);
            System.out.println("В таблице titles находятся данные: ");
            while (rs.next()) {
                var book_id = rs.getString("book_id");
                var book_title = rs.getString("book_title");
                System.out.println("book_id=" + book_id + ", book_title=" + book_title);
            }
            System.out.println("Выберите book_id с которым вы хотите связать новую запись");
            var book_id = in.next();
            sqlCommand = "INSERT INTO public.books\n" + "(id, book_id, price, author_name)\n"
                    + "VALUES(" + id + ", " + book_id + ", " + price + "," + "'" + author_name + "'" + ");\n";
            stmt.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public static void deleteFromFirstTable(Connection connection) throws SQLException {
        System.out.println("Выберите id из первой таблицы чтобы удалить данные");
        printTable(connection, "books");
        var id = new Scanner(System.in).next();
        printTable(connection, "titles");
        String query = "Delete from books where id=?";
        try {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFromSecondTable(Connection connection, Scanner in) throws SQLException {
        printTable(connection, "books");
        System.out.println("Выберите ключ book_id из первой таблицы, чтобы удалить даннные из второй таблицы:");
        var book_id = in.next();

        System.out.println("Хотите ли вы удалить связанные данные с первой таблицей? н\\д");
        var approval = in.next();

        if (approval.toLowerCase().equals("н")) {
            return;
        } else {
            try (var stmt = connection.prepareStatement("delete from titles where book_id=" + book_id)) {
                var stm = connection.prepareStatement("delete from books where book_id=" + book_id);
                stm.execute();
                System.out.println("Удаление из первой таблицы прошло успешно.");
                printTable(connection, "books");
                stmt.execute();
                System.out.println("Удаление из второй таблицы прошло успешно.");
                printTable(connection, "titles");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void insertSecondTable(Connection connection, String book_id, String book_title) {

        String sqlCommand = "insert into titles( book_id, \"book_title\") values(" + book_id + ", '" + book_title + "');";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sqlCommand);
            System.out.println("Данные в таблице обновились");
            printTable(connection, "titles");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printTable(Connection conn, String tableName) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from " + tableName);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        System.out.println("Таблица " + tableName);
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    public static void selectNaturalJoin(Connection connection) {
        String query = "SELECT titles.book_id, books.book_id, books.price, books.author_name, titles.book_title FROM titles LEFT OUTER JOIN books ON titles.book_id=books.book_id;";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String book_id = rs.getString("book_id");
                String price = rs.getString("price");
                String author_name = rs.getString("author_name");
                String book_title = rs.getString("book_title");

                System.out.println("book_id=" + book_id + ", price=" + price + ", author_name=" + author_name + ", book_title=" + book_title);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        conn = connect();

        while (true) {
            System.out.println("Выберите номер задания: 1, 2, 3, 4, 5");
            var command = new Scanner(System.in).next();
            try {
                switch (command) {
                    case "1":
                        selectNaturalJoin(conn);
                        continue;
                    case "2":
                        System.out.println("Введите book_id: ");
                        var book_id = new Scanner(System.in).next();
                        System.out.println("Введите book_title: ");
                        var book_title = new Scanner(System.in).next();
                        insertSecondTable(conn, book_id, book_title);
                        continue;
                    case "3":
                        insertInFirstTable(conn, new Scanner(System.in));
                        continue;
                    case "4":
                        deleteFromSecondTable(conn, new Scanner(System.in));
                        continue;
                    case "5":
                        deleteFromFirstTable(conn);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
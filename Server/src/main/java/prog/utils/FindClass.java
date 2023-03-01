package prog.utils;

import prog.utils.First;
import prog.utils.Second;

import java.sql.*;
import java.util.ArrayList;

public class FindClass {

    public Second findSecond(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM titles where book_id=" + id;
        Class.forName("org.postgresql.Driver");
        Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practic", "postgres", "Pivo").createStatement();
        ResultSet res = statement.executeQuery(sql);
        while (res.next()) {
            return new Second(res.getString("book_id"), res.getString("book_title"));
        }
        return null;
    }

    public First findFirst(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM books where id=" + id;
        Class.forName("org.postgresql.Driver");
        Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/practic", "postgres", "Pivo").createStatement();
        ResultSet res = statement.executeQuery(sql);
        while (res.next()){
            return new First(res.getString("id"), res.getString("book_id"), res.getString("price"), res.getString("author_name"));
        }
        return null;
    }
}
import prog.ApplicationDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/main")
public class ServletNew extends HttpServlet {
    static String tableString = "<table style=\"border:5px solid Violet; font-size: 25px;\"><tr>" +
            "<th style=\"border:2px solid Violet; font-size: 25px;\">id</th>" +
            "<th style=\"border:2px solid Violet; font-size: 25px;\">book_id</th>" +
            "<th style=\"border:2px solid Violet; font-size: 25px;\">author_name</th>" +
            "<th style=\"border:2px solid Violet; font-size: 25px;\">book_title</th>" +
            "<th style=\"border:2px solid Violet; font-size: 25px;\">price</th></tr>";
    static String td = "<td style=\"border:2px solid Violet; font-size: 25px;\">";
    static String std = "</td>";
    static String tr = "<tr style=\"border:2px solid Violet; font-size: 25px;\">";
    static String str = "</tr\">";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter out = resp.getWriter()) {
            Statement statement = ApplicationDataSource.getConnection().createStatement();
            ResultSet set = statement.executeQuery("select * from books join titles on books.book_id = titles.book_id;");
            createTable(set, out);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createTable(ResultSet set, PrintWriter out) throws SQLException {
        out.println("<HTML><BODY><div style=\"font-size: 25px; text-align: center; padding: 30px; margin: 50px; border: 5px solid pink; border-radius: 5px; display: flex; flex-direction: column;\">");
        if (set.next()) {
            out.print(tableString);
            do {
                out.print(tr);
                out.print(td + set.getObject("id") + std + td + set.getObject("book_id") + std + td + set.getObject("author_name") + std + td + set.getObject("book_title") + std + td + set.getObject("price") + std);
                out.print(str);
            } while (set.next());
            out.print("</table>");
        }
        out.println("</div></BODY></HTML>");
    }
}
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
    static String tableString = "<table><tr><th>id</th><th>book_id</th><th>author_name</th><th>book_title</th><th>price</th></tr>";
    static String td = "<td>";
    static String std = "</td>";
    static String tr = "<tr>";
    static String str = "</tr>";
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
        out.println("<HTML><BODY>");
        if (set.next()) {
            out.print(tableString);
            do {
                out.print(tr);
                out.print(td + set.getObject("id") + std + td + set.getObject("book_id") + std + td + set.getObject("author_name") + std + td + set.getObject("book_title") + std + td + set.getObject("price") + std);
                out.print(str);
            } while (set.next());
            out.print("</table>");
        }
        out.println("</BODY></HTML>");
    }
}
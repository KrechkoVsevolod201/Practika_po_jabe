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
            out.print("<table><tr><th>id</th><th>book_id</th><th>author_name</th><th>book_title</th><th>price</th></tr>");
            do {
                out.print("<tr>");
                out.print("<td>" + set.getObject("id") + "</td>");
                out.print("<td>" + set.getObject("book_id") + "</td>");
                out.print("<td>" + set.getObject("author_name") + "</td>");
                out.print("<td>" + set.getObject("book_title") + "</td>");
                out.print("<td>" + set.getObject("price") + "</td>");
                out.print("</tr>");
            } while (set.next());
            out.print("</table>");
        }
        out.println("</BODY></HTML>");
    }
}
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.sql.SQLException;

@WebServlet(urlPatterns = "/main")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("tableWithJoin", DBUtill.selectTable("participants, names", "where participants.user_id  = names.user_id;"));
        //req.setAttribute("table1", DBUtill.selectTable("participants", ""));
        //req.setAttribute("table2", DBUtill.selectTable("names", ""));
        req.getRequestDispatcher("main.jsp").forward(req, resp);

    }

}
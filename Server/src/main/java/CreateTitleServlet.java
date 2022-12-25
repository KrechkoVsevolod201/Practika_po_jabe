import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static prog.utils.TitlesCreate.createTitles;

@WebServlet("/createtitle")
public class CreateTitleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createTitles.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String book_id = request.getParameter("book_id");
        String book_title = request.getParameter("book_title");
        try {
            createTitles("create " + book_id + " " + book_title);
            response.sendRedirect(request.getContextPath());
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
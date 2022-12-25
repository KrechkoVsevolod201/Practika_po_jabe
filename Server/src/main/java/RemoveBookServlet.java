import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static prog.utils.BooksRemove.removeTextBook;

@WebServlet("/removebook")
public class RemoveBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/removeBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        try {
            int i = removeTextBook("remove " + id);
            if (i != 1) {
                throw new RuntimeException("Check the correctness of the books ID");
            }
            response.sendRedirect(request.getContextPath());
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
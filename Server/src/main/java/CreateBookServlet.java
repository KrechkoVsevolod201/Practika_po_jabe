import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static prog.utils.BooksCreate.createBooks;

@WebServlet("/createbook")
public class CreateBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String book_id = request.getParameter("book_id");
        String price = request.getParameter("price");
        String author_name = request.getParameter("author_name");
        try {
            createBooks("create " + id + " " + book_id + " " + price + " " + author_name);
            response.sendRedirect(request.getContextPath());
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
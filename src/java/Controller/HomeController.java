package Controller;

import DAO.BookDAO;
import Model.Book;
import Util.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Mendapatkan koneksi dari JDBC
            Connection connection = JDBC.getInstance().getConnection();
            BookDAO bookDAO = new BookDAO(connection);

            String search = request.getParameter("search");
            String categoryParamId = request.getParameter("category");
            int categoryId = 0; // Default value in case the parameter is missing or invalid
            
            if (categoryParamId != null && !categoryParamId.isEmpty()) {
                try {
                    categoryId = Integer.parseInt(categoryParamId);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Kategori harus berupa angka");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }
            }

            List<Book> books = bookDAO.getAllBooks(search, categoryId);
            request.setAttribute("books", books);
            request.setAttribute("search", search);
            request.setAttribute("category", categoryId);

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Mendapatkan parameter dari form
            String action = request.getParameter("action");

            if ("add".equals(action)) {
                String title = request.getParameter("title");
                String details = request.getParameter("details");
                String publisher = request.getParameter("publisher");

                // Mendapatkan koneksi dari JDBC
                Connection connection = JDBC.getInstance().getConnection();
                BookDAO bookDAO = new BookDAO(connection);

                // Menambahkan buku baru
                Book book = new Book();
                book.setTitle(title);
                book.setDetails(details);
                book.setPublisher(publisher);
                bookDAO.addBook(book);

                // Redirect kembali ke halaman arian.jsp
                response.sendRedirect("HomeController");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

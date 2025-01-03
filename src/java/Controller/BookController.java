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

@WebServlet("/BookController")
public class BookController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Mendapatkan koneksi dari JDBC
            Connection connection = JDBC.getInstance().getConnection();
            BookDAO bookDAO = new BookDAO(connection);

            // Mengambil semua buku dari database
            List<Book> books = bookDAO.getAllBooks();

            // Menyimpan data buku ke request agar bisa digunakan di Librarian.jsp
            request.setAttribute("books", books);

            // Forward ke Librarian.jsp
            request.getRequestDispatcher("Librarian.jsp").forward(request, response);
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

                // Redirect kembali ke halaman Librarian.jsp
                response.sendRedirect("BookController");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

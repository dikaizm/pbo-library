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

@WebServlet("/book")
public class BookController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Mendapatkan parameter id dari URL
            String id = request.getParameter("id");

            System.out.println("id: " + id);

            // Mendapatkan koneksi dari JDBC
            Connection connection = JDBC.getInstance().getConnection();
            BookDAO bookDAO = new BookDAO(connection);

            if (id == null || id.isEmpty()) {
                request.setAttribute("error", "ID buku tidak ditemukan");
                request.getRequestDispatcher("../error.jsp").forward(request, response);
                return;
            }

            // Mendapatkan detail buku berdasarkan id
            Book book = bookDAO.getBookById(Integer.parseInt(id));
            request.setAttribute("book", book);

            // Menampilkan halaman detail buku
            request.getRequestDispatcher("book/detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("../error.jsp").forward(request, response);
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
                response.sendRedirect("Librarian.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

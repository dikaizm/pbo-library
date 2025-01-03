package Controller;

import DAO.BookDAO;
import DAO.BorrowRecordDAO;
import Model.Book;
import Model.BorrowRecord;
import Model.User;
import Util.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet("/book/me")
public class BookMeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/auth/login");
                return;
            }

            // Mendapatkan koneksi dari JDBC
            Connection connection = JDBC.getInstance().getConnection();
            BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO(connection);

            String search = request.getParameter("search");
            String categoryParamId = request.getParameter("category");
            int categoryId = 0; // Default value in case the parameter is missing or invalid
            String status = request.getParameter("status");

            if (categoryParamId != null && !categoryParamId.isEmpty()) {
                try {
                    categoryId = Integer.parseInt(categoryParamId);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Kategori harus berupa angka");
                    request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
                    return;
                }
            }

            List<BorrowRecord> borrowRecords = borrowRecordDAO.getAllMyBooks(search, categoryId, status);
            request.setAttribute("borrowRecords", borrowRecords);
            request.setAttribute("search", search);
            request.setAttribute("category", categoryId);
            request.setAttribute("status", status);

            request.getRequestDispatcher("me.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("../error.jsp").forward(request, response);
        }
    }
}

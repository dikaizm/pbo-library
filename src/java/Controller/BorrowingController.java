/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author tiara
 */
//import com.library.dao.BorrowingDAO;
//import com.library.model.Borrowing;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import DAO.BorrowRecordDAO;
import Model.BorrowRecord;
import Model.User;
import Util.JDBC;

import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/borrowing")
public class BorrowingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String borrowDaysParam = request.getParameter("borrowDays");
        int borrowDays = 0; // Default value in case the parameter is missing or invalid

        if (borrowDaysParam != null && !borrowDaysParam.isEmpty()) {
            try {
                borrowDays = Integer.parseInt(borrowDaysParam);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Hari pinjam harus berupa angka");
                request.getRequestDispatcher("book.jsp").forward(request, response);
                return;
            }
        }

        BorrowRecordDAO borrowingDAO = new BorrowRecordDAO(JDBC.getInstance().getConnection());

        if ("borrow".equals(action)) {
            String bookIdParam = request.getParameter("bookId");
            int bookId = 0; // Default value in case the parameter is missing or invalid

            if (bookIdParam != null && !bookIdParam.isEmpty()) {
                try {
                    bookId = Integer.parseInt(bookIdParam);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "ID buku harus berupa angka");
                    request.getRequestDispatcher("book.jsp").forward(request, response);
                    return;
                }
            }

            User user = (User) request.getSession().getAttribute("user");

            if (user == null) {
                request.setAttribute("error", "Anda harus login terlebih dahulu");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            System.out.println(user.getName() + " is borrowing book with ID " + bookId + " for " + borrowDays + " days");

            try {
                borrowingDAO.borrowBook(user.getId(), bookId, borrowDays);
                response.sendRedirect(request.getContextPath() + "/book/me");
            } catch (SQLException e) {
                throw new ServletException("Error borrowing book", e);
            }
        } else if ("return".equals(action)) {

        }
    }
}

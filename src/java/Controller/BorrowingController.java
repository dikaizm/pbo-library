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

import DAO.BorrowingDAO;
import Model.Borrowing;
import java.util.List;

public class BorrowingController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        BorrowingDAO borrowingDAO = new BorrowingDAO();

        try {
            if ("borrow".equals(action)) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                borrowingDAO.addBorrowing(new Borrowing(0, bookId, userId, new java.sql.Date(System.currentTimeMillis()), null));
            } else if ("return".equals(action)) {
                int borrowingId = Integer.parseInt(request.getParameter("borrowingId"));
                borrowingDAO.returnBook(borrowingId);
            }
            response.sendRedirect("BorrowingController");
        } catch (SQLException e) {
            throw new ServletException("Error processing borrowing operation", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowingDAO borrowingDAO = new BorrowingDAO();
        try {
            List<Borrowing> borrowings = borrowingDAO.getAllBorrowings();
            request.setAttribute("borrowings", borrowings); // Forward borrowing data to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("Borrowing.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving borrowings", e);
        }
    }
}

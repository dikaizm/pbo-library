package Controller;

import DAO.UserDAO;
import Model.Student;
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

@WebServlet("/manage/users")
public class ManageUsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/auth/login");
                return;
            }

            // Mendapatkan koneksi dari JDBC
            Connection connection = JDBC.getInstance().getConnection();
            UserDAO userDAO = new UserDAO(connection);

            String action = request.getParameter("action");

            if ("delete".equals(action)) {

            } else if ("view_page".equals(action)) {
                String email = request.getParameter("email");
                Student user = userDAO.getStudentByEmail(email);
                request.setAttribute("user", user);
                request.setAttribute("borrowings", user.getBorrowRecords());
                request.getRequestDispatcher("view_user.jsp").forward(request, response);
            } else {
                String search = request.getParameter("search");

                List<Student> users = userDAO.getAllStudents(search);
                request.setAttribute("users", users);
                request.setAttribute("search", search);

                request.getRequestDispatcher("users.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("../error.jsp").forward(request, response);
        }
    }
}

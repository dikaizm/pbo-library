package Controller;

import DAO.UserDAO;
import Util.JDBC;
import Model.User;
import Model.Librarian;
import Model.Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("login".equals(action)) {
                // Ambil parameter username dan password
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                // Validasi input
                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    request.setAttribute("error", "Username or password cannot be empty.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                // Panggil DAO untuk memvalidasi user
                UserDAO userDAO = new UserDAO(JDBC.getInstance().getConnection());
                User user = userDAO.getUserByEmail(email);

                if (user != null) {
                    if (!password.equals(user.getPassword())) {
                        request.setAttribute("error", "Invalid username or password.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    }

                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    request.setAttribute("error", "Invalid username or password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                // Jika tidak ada aksi, arahkan ke login.jsp
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("../error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

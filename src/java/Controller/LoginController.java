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


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("login".equals(action)) {
                // Ambil parameter username dan password
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                // Validasi input
                if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                    request.setAttribute("error", "Username or password cannot be empty.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                // Panggil DAO untuk memvalidasi user
                UserDAO userDAO = new UserDAO(JDBC.getInstance().getConnection());
                User user = userDAO.getUserByUsernameAndPassword(username, password);

                if (user != null) {
                    HttpSession session = request.getSession();
                    if (user instanceof Librarian) {
                        session.setAttribute("librarian", user);
                        response.sendRedirect("Librarian.jsp");
                    } else if (user instanceof Student) {
                        session.setAttribute("student", user);
                        response.sendRedirect("Student.jsp");
                    }
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
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

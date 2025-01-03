package Controller;

import DAO.UserDAO;
import Util.JDBC;
import Model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/signup")
public class SignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("signup".equals(action)) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String role = request.getParameter("role");

                System.out.println(name + " " + email + " " + password + " " + role);

                // Validasi input
                if (email == null || email.isEmpty() || password == null || password.isEmpty() || name == null || name.isEmpty() || role == null || role.isEmpty()) {
                    request.setAttribute("error", "Semua field harus diisi.");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                    return;
                }

                // Panggil DAO untuk memvalidasi user
                UserDAO userDAO = new UserDAO(JDBC.getInstance().getConnection());
                User existUser = userDAO.getUserByEmail(email);

                if (existUser != null) {
                    System.out.println("Email sudah terdaftar.");
                    request.setAttribute("error", "Email sudah terdaftar.");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                    return;
                }

                if (userDAO.createUser(name, email, password, role)) {
                    request.setAttribute("success", true);
                } else {
                    request.setAttribute("error", "Gagal membuat akun.");
                }

                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else {
                // Jika tidak ada aksi, arahkan ke signup.jsp
                request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}

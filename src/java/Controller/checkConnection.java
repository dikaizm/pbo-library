package Controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Util.JDBC;
// Servlet ini diakses melalui /checkConnection
@WebServlet("/checkConnection")
public class checkConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        try {
            // Periksa koneksi menggunakan kelas JDBC Anda
            Connection con = JDBC.getInstance().getConnection();
            if (con != null && !con.isClosed()) {
                response.getWriter().println("<h1>Koneksi ke database berhasil!</h1>");
            } else {
                response.getWriter().println("<h1>Koneksi ke database gagal!</h1>");
            }
        } catch (Exception e) {
            response.getWriter().println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}

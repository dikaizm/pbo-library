package DAO;

import Model.User;
import Model.Student;
import Model.Librarian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Method untuk memeriksa apakah username sudah ada
    public boolean checkIfUserExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Method untuk membuat user baru
    public boolean createUser(String name, String username, String password, String role) throws SQLException {
        String query = "INSERT INTO users (name, username, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, role);
            return stmt.executeUpdate() > 0;
        }
    }

    // Method untuk mendapatkan user berdasarkan username dan password
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    if ("librarian".equalsIgnoreCase(role)) {
                            Librarian librarian = new Librarian();
                        librarian.setId(rs.getInt("id"));
                        librarian.setName(rs.getString("name"));
                        librarian.setUsername(rs.getString("username"));
                        librarian.setPassword(rs.getString("password"));
                        librarian.setRole(role);
                        return librarian;
                    } else if ("student".equalsIgnoreCase(role)) {
                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setName(rs.getString("name"));
                        student.setUsername(rs.getString("username"));
                        student.setPassword(rs.getString("password"));
                        student.setRole(role);
                        return student;
                    }
                }
            }
        }
        return null;
    }

}

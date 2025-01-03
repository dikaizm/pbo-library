package DAO;

import Model.BorrowRecord;
import Model.Student;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to create a new user
    public boolean createUser(String name, String email, String password, String role) throws SQLException {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        // Use the provided connection instead of getting a new one
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to get user by email
    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);

            // Use the provided connection instead of getting a new one
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

        return user;
    }

    public List<Student> getAllStudents(String name) {
        // Default query to get all students
        String query = "SELECT u.*, s.* FROM users u LEFT JOIN students s ON s.user_id = u.id WHERE role = 'student'";

        // If name is not empty, modify the query to filter by name
        if (name != null && !name.isEmpty()) {
            query += " AND name LIKE ?";
        }

        List<Student> users = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // If name is not empty, set the parameter for the LIKE query
            if (name != null && !name.isEmpty()) {
                stmt.setString(1, "%" + name + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student user = new Student();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setMajor(rs.getString("major"));

                    List<BorrowRecord> borrowRecords = new ArrayList<>();
                    String query2 = "SELECT * FROM borrow_records WHERE user_id = ?";
                    try (PreparedStatement stmt2 = connection.prepareStatement(query2)) {
                        stmt2.setInt(1, user.getId());
                        try (ResultSet rs2 = stmt2.executeQuery()) {
                            while (rs2.next()) {
                                BorrowRecord record = new BorrowRecord();
                                record.setId(rs2.getInt("id"));
                                record.setBookId(rs2.getInt("book_id"));
                                record.setUserId(rs2.getInt("user_id"));
                                record.setBorrowDate(rs2.getDate("borrow_date"));
                                record.setReturnDate(rs2.getDate("return_date"));
                                record.setDueDate(rs2.getDate("due_date"));
                                record.setStatus(rs2.getString("status"));

                                borrowRecords.add(record);
                            }
                        }
                    }

                    user.setBorrowRecords(borrowRecords);

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}

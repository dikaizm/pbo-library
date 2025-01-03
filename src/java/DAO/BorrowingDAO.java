/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author tiara
 */
import Model.Borrowing;
import Util.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAO {
    public List<Borrowing> getAllBorrowings() throws SQLException {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM borrowing";

        try (Connection conn = JDBC.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                borrowings.add(new Borrowing(
                    rs.getInt("id"),
                    rs.getInt("book_id"),
                    rs.getInt("user_id"),
                    rs.getDate("date_borrowed"),
                    rs.getDate("date_due")
                ));
            }
        }

        return borrowings;
    }

    public void addBorrowing(Borrowing borrowing) throws SQLException {
        String sql = "INSERT INTO borrowing (book_id, user_id, date_borrowed, date_due) VALUES (?, ?, ?, ?)";

        try (Connection conn = JDBC.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrowing.getBookId());
            stmt.setInt(2, borrowing.getUserId());
            stmt.setDate(3, borrowing.getDateBorrowed());
            stmt.setDate(4, borrowing.getDateDue());
            stmt.executeUpdate();
        }
    }

    public void returnBook(int borrowingId) throws SQLException {
        String sql = "DELETE FROM borrowing WHERE id = ?";

        try (Connection conn = JDBC.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrowingId);
            stmt.executeUpdate();
        }
    }
}

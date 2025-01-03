/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Book;
import Model.BookCategory;
import Model.BorrowRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAO {
    private final Connection connection;

    public BorrowRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public List<BorrowRecord> getAllMyBooks(String title, int categoryId, String status) throws SQLException {
        List<BorrowRecord> borrowings = new ArrayList<>();
        String sql = "SELECT br.*, b.*, bc.`name` AS 'category_name' FROM borrow_records br JOIN books b ON b.id = br.book_id JOIN book_categories bc ON b.category_id = bc.id WHERE status = 'borrowed';";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BorrowRecord borrow = new BorrowRecord();
                borrow.setId(rs.getInt("id"));
                borrow.setUserId(rs.getInt("user_id"));
                borrow.setBookId(rs.getInt("book_id"));
                borrow.setBorrowDate(rs.getDate("borrow_date"));
                borrow.setDueDate(rs.getDate("due_date"));
                borrow.setStatus(rs.getString("status"));

                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setCategory(new BookCategory(rs.getString("category_name")));

                borrowings.add(borrow);
            }
        }

        return borrowings;
    }

    public boolean borrowBook(int userId, int bookId, int borrowDays) throws SQLException {
        Date currentDate = new Date(System.currentTimeMillis());
        Date dueDate = new Date(System.currentTimeMillis() + borrowDays * 24 * 60 * 60 * 1000);

        BorrowRecord borrow = new BorrowRecord();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(currentDate);
        borrow.setDueDate(dueDate);
        borrow.setStatus("borrowed");

        String sql = "INSERT INTO borrow_records (book_id, user_id, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, borrow.getBookId());
            stmt.setInt(2, borrow.getUserId());
            stmt.setDate(3, borrow.getBorrowDate());
            stmt.setDate(4, borrow.getDueDate());
            stmt.setString(5, borrow.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public void returnBook(int borrowingId) throws SQLException {
        String sql = "DELETE FROM borrowing WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, borrowingId);
            stmt.executeUpdate();
        }
    }
}

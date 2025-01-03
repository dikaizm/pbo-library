package DAO;

import Model.Book;
import Model.BookCategory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Book> getAllBooks(String title, int categoryId) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query;

        if ((title == null || title.isEmpty()) && categoryId == 0) {
            query = "SELECT b.*, c.name AS category_name FROM books b JOIN book_categories c ON b.category_id = c.id ORDER BY title DESC"; // Fetch all books
        } else if (categoryId == 0) {
            query = "SELECT b.*, c.name AS category_name FROM books b JOIN book_categories c ON b.category_id = c.id WHERE title LIKE ? ORDER BY title DESC"; // Filter by title
        } else if (title == null || title.isEmpty()) {
            query = "SELECT b.*, c.name AS category_name FROM books b JOIN book_categories c ON b.category_id = c.id WHERE category_id = ? ORDER BY title DESC"; // Filter by category
        } else {
            query = "SELECT b.*, c.name AS category_name FROM books b JOIN book_categories c ON b.category_id = c.id WHERE title LIKE ? AND category_id = ? ORDER BY title DESC"; // Filter by both
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            if (categoryId == 0 && (title == null || title.isEmpty())) {
                // No parameters needed
            } else if (categoryId == 0) {
                stmt.setString(1, "%" + title + "%");
            } else if (title == null || title.isEmpty()) {
                stmt.setInt(1, categoryId);
            } else {
                stmt.setString(1, "%" + title + "%");
                stmt.setInt(2, categoryId);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDetails(rs.getString("details"));
                book.setPublisher(rs.getString("publisher"));
                book.setImageUrl(rs.getString("image_url"));
                book.setQuantity(rs.getInt("quantity"));

                BookCategory category = new BookCategory();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));

                book.setCategory(category);

                books.add(book);
            }
        }
        return books;
    }

    public Book getBookById(int id) throws SQLException {
        String query = "SELECT b.*, c.name AS category_name FROM books b JOIN book_categories c ON b.category_id = c.id WHERE b.id = ?";
        Book book = new Book();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDetails(rs.getString("details"));
                book.setPublisher(rs.getString("publisher"));
                book.setImageUrl(rs.getString("image_url"));
                book.setQuantity(rs.getInt("quantity"));

                BookCategory category = new BookCategory();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));

                book.setCategory(category);
            } else {
                book = null;
            }
        }
        return book;
    }

    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, details, publisher) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getDetails());
            stmt.setString(3, book.getPublisher());
            stmt.executeUpdate();
        }
    }
}

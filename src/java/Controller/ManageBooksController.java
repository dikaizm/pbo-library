package Controller;

import DAO.BookDAO;
import Model.Book;
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

@WebServlet("/manage/books")
public class ManageBooksController extends HttpServlet {

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
            BookDAO bookDAO = new BookDAO(connection);

            String action = request.getParameter("action");

            if ("add_page".equals(action)) {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null || !currentUser.isLibrarian()) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                    return;
                }
                request.getRequestDispatcher("add_book.jsp").forward(request, response);
                return;
            } else if ("edit_page".equals(action)) {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null || !currentUser.isLibrarian()) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                    return;
                }
                String bookIdParam = request.getParameter("id");

                if (bookIdParam != null && !bookIdParam.isEmpty()) {
                    int bookId = Integer.parseInt(bookIdParam);
                    Book book = bookDAO.getBookById(bookId);

                    if (book != null) {
                        request.setAttribute("book", book);
                        request.getRequestDispatcher("edit_book.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Buku tidak ditemukan");
                        request.getRequestDispatcher("books.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "ID buku harus diisi");
                    request.getRequestDispatcher("books.jsp").forward(request, response);
                }
                return;
            } else if ("view_page".equals(action)) {
                String bookIdParam = request.getParameter("id");

                if (bookIdParam != null && !bookIdParam.isEmpty()) {
                    int bookId = Integer.parseInt(bookIdParam);
                    Book book = bookDAO.getBookById(bookId);

                    if (book != null) {
                        request.setAttribute("book", book);
                        request.getRequestDispatcher("view_book.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Buku tidak ditemukan");
                        request.getRequestDispatcher("books.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "ID buku harus diisi");
                    request.getRequestDispatcher("books.jsp").forward(request, response);
                }
                return;
            }

            if ("delete".equals(action)) {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null || !currentUser.isLibrarian()) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                    return;
                }

                String bookIdParam = request.getParameter("id");

                if (bookIdParam != null && !bookIdParam.isEmpty()) {
                    int bookId = Integer.parseInt(bookIdParam);
                    bookDAO.deleteBook(bookId);
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                } else {
                    request.setAttribute("error", "ID buku harus diisi");
                    request.getRequestDispatcher("books.jsp").forward(request, response);
                }
            } else {
                String search = request.getParameter("search");
                String categoryParamId = request.getParameter("category");
                int categoryId = 0; // Default value in case the parameter is missing or invalid

                if (categoryParamId != null && !categoryParamId.isEmpty()) {
                    try {
                        categoryId = Integer.parseInt(categoryParamId);
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Kategori harus berupa angka");
                        request.getRequestDispatcher("books.jsp").forward(request, response);
                        return;
                    }
                }

                List<Book> books = bookDAO.getAllBooks(search, categoryId);
                request.setAttribute("books", books);
                request.setAttribute("search", search);
                request.setAttribute("category", categoryId);

                request.getRequestDispatcher("books.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("../error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/auth/login");
                return;
            }

            // Mendapatkan koneksi dari JDBC
            Connection connection = JDBC.getInstance().getConnection();
            BookDAO bookDAO = new BookDAO(connection);

            // Mendapatkan parameter dari form
            String action = request.getParameter("action");

            if ("add".equals(action)) {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null || !currentUser.isLibrarian()) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                    return;
                }

                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String isbn = request.getParameter("isbn");
                String categoryIdStr = request.getParameter("category");
                String publicationYearStr = request.getParameter("publicationYear");
                String publisher = request.getParameter("publisher");
                String quantityStr = request.getParameter("quantity");
                String details = request.getParameter("details");
                String imageUrl = request.getParameter("imageUrl");

                if (title == null || title.isEmpty() || author == null || author.isEmpty() || isbn == null || isbn.isEmpty() || categoryIdStr == null || categoryIdStr.isEmpty() || publicationYearStr == null || publicationYearStr.isEmpty() || publisher == null || publisher.isEmpty() || quantityStr == null || quantityStr.isEmpty() || details == null || details.isEmpty() || imageUrl == null || imageUrl.isEmpty()) {
                    request.setAttribute("error", "Semua field harus diisi");
                    request.getRequestDispatcher(request.getContextPath() + "/manage/books?action=add_page").forward(request, response);
                    return;
                }

                if (bookDAO.isIsbnExists(isbn)) {
                    request.setAttribute("error", "ISBN sudah digunakan");
                    request.getRequestDispatcher(request.getContextPath() + "/manage/books?action=add_page").forward(request, response);
                    return;
                }

                int categoryId = Integer.parseInt(categoryIdStr);
                int publicationYear = Integer.parseInt(publicationYearStr);
                int quantity = Integer.parseInt(quantityStr);

                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setCategoryId(categoryId);
                book.setPublicationYear(publicationYear);
                book.setPublisher(publisher);
                book.setQuantity(quantity);
                book.setDetails(details);
                book.setImageUrl(imageUrl);

                if (bookDAO.addBook(book)) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                } else {
                    request.setAttribute("error", "Gagal menambahkan buku");
                    request.getRequestDispatcher(request.getContextPath() + "/manage/books?action=add_page").forward(request, response);
                }

            } else if ("edit".equals(action)) {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null || !currentUser.isLibrarian()) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                    return;
                }

                String bookIdStr = request.getParameter("id");
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String isbn = request.getParameter("isbn");
                String categoryIdStr = request.getParameter("category");
                String publicationYearStr = request.getParameter("publicationYear");
                String publisher = request.getParameter("publisher");
                String quantityStr = request.getParameter("quantity");
                String details = request.getParameter("details");
                String imageUrl = request.getParameter("imageUrl");

                if (bookIdStr == null || bookIdStr.isEmpty() || title == null || title.isEmpty() || author == null || author.isEmpty() || isbn == null || isbn.isEmpty() || categoryIdStr == null || categoryIdStr.isEmpty() || publicationYearStr == null || publicationYearStr.isEmpty() || publisher == null || publisher.isEmpty() || quantityStr == null || quantityStr.isEmpty() || details == null || details.isEmpty() || imageUrl == null || imageUrl.isEmpty()) {
                    request.setAttribute("error", "Semua field harus diisi");
                    request.getRequestDispatcher(request.getContextPath() + "/manage/edit_book.jsp").forward(request, response);
                    return;
                }

                int bookId = Integer.parseInt(bookIdStr);
                int categoryId = Integer.parseInt(categoryIdStr);
                int publicationYear = Integer.parseInt(publicationYearStr);
                int quantity = Integer.parseInt(quantityStr);

                Book book = new Book();
                book.setId(bookId);
                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setCategoryId(categoryId);
                book.setPublicationYear(publicationYear);
                book.setPublisher(publisher);
                book.setQuantity(quantity);
                book.setDetails(details);
                book.setImageUrl(imageUrl);

                if (bookDAO.updateBook(book)) {
                    response.sendRedirect(request.getContextPath() + "/manage/books");
                } else {
                    request.setAttribute("error", "Gagal mengubah buku");
                    request.getRequestDispatcher(request.getContextPath() + "/manage/edit_book.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
        }
    }
}

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Book"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Librarian Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Librarian Dashboard</h2>
            <div>
                <p><strong>Name:</strong> <%= ((Model.Librarian) session.getAttribute("librarian")).getName() %></p>
                <p><strong>Department:</strong> <%= ((Model.Librarian) session.getAttribute("librarian")).getDepartment() %></p>
            </div>
        </div>

        <h3>Book List</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Details</th>
                    <th>Publisher</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Book> books = (List<Book>) request.getAttribute("books"); // Ambil semua buku
                    if (books != null && !books.isEmpty()) {
                        for (Book book : books) {
                %>
                    <tr>
                        <td><%= book.getId() %></td>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getDetails() %></td>
                        <td><%= book.getPublisher() %></td>
                    </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="4" class="text-center">No books available.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <h3>Add New Book</h3>
        <form method="post" action="BookController">
            <input type="hidden" name="action" value="add">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="details" class="form-label">Details</label>
                <textarea class="form-control" id="details" name="details" required></textarea>
            </div>
            <div class="mb-3">
                <label for="publisher" class="form-label">Publisher</label>
                <input type="text" class="form-control" id="publisher" name="publisher" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Book</button>
        </form>
    </div>
</body>
</html>

<%-- 
    Document   : Book
    Created on : Jan 2, 2025, 12:11:29 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.Book"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Book Management</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Details</th>
                    <th>Publisher</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    for (Book book : books) { 
                %>
                <tr>
                    <td><%= book.getId() %></td>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getDetails() %></td>
                    <td><%= book.getPublisher() %></td>
                    <td>
                        <form method="post" action="BookController">
                            <input type="hidden" name="id" value="<%= book.getId() %>">
                            <input type="hidden" name="action" value="delete">
                            <button class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <h3>Add Book</h3>
        <form method="post" action="BookController">
            <input type="hidden" name="action" value="add">
            <div class="mb-3">
                <label>Title</label>
                <input type="text" name="title" class="form-control">
            </div>
            <div class="mb-3">
                <label>Details</label>
                <input type="text" name="details" class="form-control">
            </div>
            <div class="mb-3">
                <label>Publisher</label>
                <input type="text" name="publisher" class="form-control">
            </div>
            <button class="btn btn-primary">Add Book</button>
        </form>
    </div>
</body>
</html>

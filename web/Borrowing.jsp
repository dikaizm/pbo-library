<%-- 
    Document   : Borrowing
    Created on : Jan 2, 2025, 12:12:35 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.Borrowing"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Borrowings</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Borrowing Management</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Book ID</th>
                    <th>User ID</th>
                    <th>Date Borrowed</th>
                    <th>Date Due</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Borrowing> borrowings = (List<Borrowing>) request.getAttribute("borrowings");
                    for (Borrowing borrowing : borrowings) { 
                %>
                <tr>
                    <td><%= borrowing.getId() %></td>
                    <td><%= borrowing.getBookId() %></td>
                    <td><%= borrowing.getUserId() %></td>
                    <td><%= borrowing.getDateBorrowed() %></td>
                    <td><%= borrowing.getDateDue() %></td>
                    <td>
                        <form method="post" action="BorrowingController">
                            <input type="hidden" name="borrowingId" value="<%= borrowing.getId() %>">
                            <input type="hidden" name="action" value="return">
                            <button class="btn btn-success btn-sm">Return</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>

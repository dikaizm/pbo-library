<%@page import="Model.BorrowRecord"%>
<%@page import="java.util.List"%>
<%@page import="Model.Book"%>
<%@page import="Model.Student"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Pengguna</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@include file="../components/navbar.jsp" %>

    <div class="container mt-5 w-75">
        <% 
            Student user = (Student) request.getAttribute("user"); 
            boolean userExists = (user != null);
        %>

        <!-- Profile Header -->
        <div class="card mb-4 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">
                    <%= userExists ? user.getName() : "User not found" %>
                </h5>
                <p class="card-text">
                    <%= userExists ? user.getEmail() : "Email not available" %>
                </p>
            </div>
        </div>

        <!-- Borrowed Books Section -->
        <div class="card">
            <div class="card-header bg-secondary text-white">Daftar Buku yang Dipinjam</div>
            <div class="card-body">
                <% List<BorrowRecord> borrowings = (List<BorrowRecord>) request.getAttribute("borrowings"); %>
                <% if (borrowings != null && !borrowings.isEmpty()) { %>
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr>
                            <th>No</th>
                            <th>Judul Buku</th>
                            <th>Tanggal Pinjam</th>
                            <th>Status</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int no = 1; 
                        for (BorrowRecord borrowing : borrowings) { 
                            Book book = borrowing.getBook(); 
                        %>
                        <tr>
                            <td><%= no++ %></td>
                            <td><%= book.getTitle() %></td>
                            <td><%= borrowing.getBorrowDate() %></td>
                            <td>
                                <%= borrowing.isReturned() ? "Dikembalikan" : "Dipinjam" %>
                            </td>
                            <td>
                                <% if (!borrowing.isReturned()) { %>
                                <form action="return-book" method="post">
                                    <input type="hidden" name="borrowingId" value="<%= borrowing.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Kembalikan</button>
                                </form>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } else { %>
                <p class="text-muted text-center">Belum ada buku yang dipinjam.</p>
                <% } %>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

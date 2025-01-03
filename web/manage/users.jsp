<%@page import="Model.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Kelola Pengguna</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
    />
    <style>
      p {
        margin: 0;
      }
    </style>
  </head>
  <body class="bg-light">
    <%@include file="../components/navbar.jsp" %>

    <div class="container mt-5" style="height: 100%;"> 

      <!-- Search Form -->
      <div class="d-flex justify-content-center mb-4">
        <form action="${pageContext.request.contextPath}/manage/users" method="get" class="d-flex w-75">
          <input
            type="text"
            name="search"
            class="form-control"
            placeholder="Cari pengguna..."
            value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>"
          />
          <button type="submit" class="btn btn-primary ms-3">Cari</button>
        </form>
      </div>

      <!-- User Table -->
      <div class="table-responsive">
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>#</th>
              <th>Nama</th>
              <th>Email</th>
              <th>Jurusan</th>
              <th>Buku Dipinjam</th>
              <th>Buku Telat</th>
              <th>Aksi</th>
            </tr>
          </thead>
          <tbody>
            <% 
              List<Student> users = (List<Student>) request.getAttribute("users");
              if (users != null && !users.isEmpty()) {
                for (int i = 0; i < users.size(); i++) {
                  Student user = users.get(i);
            %>
            <tr>
              <td><%= i + 1 %></td>
              <td><%= user.getName() %></td>
              <td><%= user.getEmail() %></td>
              <td><%= user.getMajor() %></td>
              <td><%= user.countBorrowedBooks() %></td>
              <td><%= user.countOverdueBooks() %></td>
              <td>
                <a href="user?action=view_page&email=<%= user.getEmail() %>" class="btn btn-info btn-sm">Lihat</a>
                <a href="user?action=delete&id=<%= user.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus pengguna ini?')">Hapus</a>
              </td>
            </tr>
            <% 
                }
              } else { 
            %>
            <tr>
              <td colspan="6" class="text-center">Belum ada pengguna tersedia.</td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

<%@page import="Model.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Kelola Buku</title>
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

      <div class="d-flex justify-content-center mb-4">
        <!-- Add Book Button -->
      <div class="me-3">
        <a href="${pageContext.request.contextPath}/manage/books?action=add_page" class="btn btn-success">Tambah Buku</a>
      </div>

        <form action="${pageContext.request.contextPath}/manage/books" method="get" class="d-flex w-75">
          <input
            type="text"
            name="search"
            class="form-control"
            placeholder="Cari buku..."
            value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>"
          />
          <select name="category" class="form-select ms-3 w-25">
            <option value="">Semua Kategori</option>
            <option value="1" <%= "1".equals(request.getParameter("category")) ? "selected" : "" %>>Fiksi</option>
            <option value="2" <%= "2".equals(request.getParameter("category")) ? "selected" : "" %>>Ilmu Pengetahuan</option>
            <option value="3" <%= "3".equals(request.getParameter("category")) ? "selected" : "" %>>Sejarah</option>
            <option value="4" <%= "4".equals(request.getParameter("category")) ? "selected" : "" %>>Biografi</option>
            <option value="5" <%= "5".equals(request.getParameter("category")) ? "selected" : "" %>>Teknologi</option>
            <option value="6" <%= "6".equals(request.getParameter("category")) ? "selected" : "" %>>Filsafat</option>
            <option value="7" <%= "7".equals(request.getParameter("category")) ? "selected" : "" %>>Misteri</option>
            <option value="8" <%= "8".equals(request.getParameter("category")) ? "selected" : "" %>>Fantasi</option>
            <option value="9" <%= "9".equals(request.getParameter("category")) ? "selected" : "" %>>Seni</option>
            <option value="10" <%= "10".equals(request.getParameter("category")) ? "selected" : "" %>>Pengembangan Diri</option>
          </select>
          <button type="submit" class="btn btn-primary ms-3">Cari</button>
        </form>
      </div>

      <!-- Book Table -->
      <div class="table-responsive">
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>#</th>
              <th>Judul Buku</th>
              <th>Kategori</th>
              <th>Tahun Terbit</th>
              <th>Penerbit</th>
              <th>Stok</th>
              <th>Aksi</th>
            </tr>
          </thead>
          <tbody>
            <% 
              List<Book> books = (List<Book>) request.getAttribute("books");
              if (books != null && !books.isEmpty()) {
                for (int i = 0; i < books.size(); i++) {
                  Book book = books.get(i);
            %>
            <tr>
              <td><%= i + 1 %></td>
              <td><%= book.getTitle() %></td>
              <td><%= book.getCategory().getName() %></td>
              <td><%= book.getPublicationYear() %></td>
              <td><%= book.getPublisher() %></td>
              <td><%= book.getQuantity() %></td>
              <td>
                <a href="${pageContext.request.contextPath}/manage/books?action=view_page&id=<%= book.getId() %>" class="btn btn-info btn-sm">Lihat</a>
                <a href="${pageContext.request.contextPath}/manage/books?action=edit_page&id=<%= book.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="${pageContext.request.contextPath}/manage/books?action=delete&id=<%= book.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus buku ini?')">Hapus</a>
              </td>
            </tr>
            <% 
                }
              } else { 
            %>
            <tr>
              <td colspan="6" class="text-center">Belum ada buku tersedia.</td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

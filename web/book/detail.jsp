<%@ page import="Model.Book" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Detail Buku</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
    />
  </head>
  <body class="bg-light">
    <%@ include file="../components/navbar.jsp" %>

    <div class="container mt-5">
      <% Book book = (Book) request.getAttribute("book"); if (book != null) { %>
      <div class="row">
        <div class="col-md-4">
          <img
            src="<%= book.getImageUrl() %>"
            alt="Cover <%= book.getTitle() %>"
            class="img-fluid rounded shadow-sm"
          />
        </div>
        <div class="col-md-8">
          <div>
            <h3 class="fw-bold"><%= book.getTitle() %></h3>
            <p class="text-muted">Penulis: <%= book.getAuthor() %></p>
            <p class="text-muted">Penerbit: <%= book.getPublisher() %></p>
            <p class="text-muted">
              Kategori: <%= book.getCategory().getName() %>
            </p>
            <p class="text-muted">Stok: <%= book.getQuantity() %></p>
            <p class="text-muted">
              Tahun Terbit: <%= book.getPublicationYear() %>
            </p>
            <hr />
            <h5 class="fw-bold">Deskripsi</h5>
            <p class="text-justify"><%= book.getDetails() %></p>
            <hr />
          </div>

          <div>
            <% if (book.getQuantity() > 0 && session.getAttribute("user") !=
            null) { %>
            <form action="borrowing" method="post" class="d-flex w-50">
              <input type="hidden" name="action" value="borrow">

              <input type="hidden" name="bookId" value="<%= book.getId() %>" />
              <label for="borrowDays">Hari</label>
              <input type="number" name="borrowDays" id="borrowDays" class="form-control w-25 ms-3" />

              <button type="submit" class="btn btn-primary ms-3">Pinjam</button>
            </form>
            <% } %>
          </div>

          <div><a href="home" class="btn btn-secondary mt-3">Kembali</a></div>
        </div>
      </div>
      <% } else { %>
      <p class="text-center mt-5">Buku tidak ditemukan.</p>
      <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
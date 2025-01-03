<%@page import="Model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Edit Buku</title>
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
  <body class="bg-light pb-5">
    <%@include file="../components/navbar.jsp" %>

    <div class="container mt-5 w-50">
      <h2 class="mb-4">Edit Buku</h2>

      <form action="${pageContext.request.contextPath}/manage/books?action=edit" method="post">
        <!-- Hidden field for book ID -->
        <input type="hidden" name="id" value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getId() : "" %>" />

        <div class="mb-3">
          <label for="title" class="form-label">Judul Buku</label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getTitle() : "" %>"
            required
          />
        </div>

        <div class="mb-3">
          <label for="author" class="form-label">Penulis</label>
          <input
            type="text"
            class="form-control"
            id="author"
            name="author"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getAuthor() : "" %>"
            required
          />
        </div>

        <div class="mb-3">
          <label for="isbn" class="form-label">ISBN</label>
          <input
            type="text"
            class="form-control"
            id="isbn"
            name="isbn"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getIsbn() : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="category" class="form-label">Kategori</label>
          <select class="form-select" id="category" name="category" required>
            <option value="">Pilih Kategori</option>
            <option value="1" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 1) ? "selected" : "" %>>Fiksi</option>
            <option value="2" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 2) ? "selected" : "" %>>Ilmu Pengetahuan</option>
            <option value="3" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 3) ? "selected" : "" %>>Sejarah</option>
            <option value="4" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 4) ? "selected" : "" %>>Biografi</option>
            <option value="5" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 5) ? "selected" : "" %>>Teknologi</option>
            <option value="6" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 6) ? "selected" : "" %>>Filsafat</option>
            <option value="7" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 7) ? "selected" : "" %>>Misteri</option>
            <option value="8" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 8) ? "selected" : "" %>>Fantasi</option>
            <option value="9" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 9) ? "selected" : "" %>>Seni</option>
            <option value="10" <%= (request.getAttribute("book") != null && ((Book) request.getAttribute("book")).getCategoryId() == 10) ? "selected" : "" %>>Pengembangan Diri</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="publicationYear" class="form-label">Tahun Terbit</label>
          <input
            type="number"
            class="form-control"
            id="publicationYear"
            name="publicationYear"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getPublicationYear() : "" %>"
            required
          />
        </div>

        <div class="mb-3">
          <label for="publisher" class="form-label">Penerbit</label>
          <input
            type="text"
            class="form-control"
            id="publisher"
            name="publisher"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getPublisher() : "" %>"
            required
          />
        </div>

        <div class="mb-3">
          <label for="quantity" class="form-label">Stok</label>
          <input
            type="number"
            class="form-control"
            id="quantity"
            name="quantity"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getQuantity() : "" %>"
            required
          />
        </div>

        <div class="mb-3">
          <label for="details" class="form-label">Detail Buku</label>
          <textarea
            class="form-control"
            id="details"
            name="details"
          ><%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getDetails() : "" %></textarea>
        </div>

        <div class="mb-3">
          <label for="imageUrl" class="form-label">URL Gambar</label>
          <input
            type="text"
            class="form-control"
            id="imageUrl"
            name="imageUrl"
            value="<%= request.getAttribute("book") != null ? ((Book) request.getAttribute("book")).getImageUrl() : "" %>"
          />
        </div>

        <button type="submit" class="btn btn-primary">Simpan Perubahan</button>
        <a href="${pageContext.request.contextPath}/manage/books" class="btn btn-secondary ms-3">Batal</a>
      </form>

      <% if (request.getAttribute("error") != null) { %>
        <p class="text-danger mt-3 text-center">Invalid email or password</p>
      <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

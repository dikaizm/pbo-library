<%@page import="Model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tambah Buku</title>
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
      <h2 class="mb-4">Tambah Buku</h2>

      <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger text-center">
          <%= request.getAttribute("error") %>
        </div>
      <% } %>

      <form action="${pageContext.request.contextPath}/manage/books?action=add" method="post">
        <div class="mb-3">
          <label for="title" class="form-label">Judul Buku</label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            required
            value="<%= request.getAttribute("title") != null ? request.getAttribute("title") : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="author" class="form-label">Penulis</label>
          <input
            type="text"
            class="form-control"
            id="author"
            name="author"
            required
            value="<%= request.getAttribute("author") != null ? request.getAttribute("author") : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="isbn" class="form-label">ISBN</label>
          <input
            type="text"
            class="form-control"
            id="isbn"
            name="isbn"
            value="<%= request.getAttribute("isbn") != null ? request.getAttribute("isbn") : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="category" class="form-label">Kategori</label>
          <select class="form-select" id="category" name="category" required>
            <option value="">Pilih Kategori</option>
            <% 
              String selectedCategory = (String) request.getAttribute("category");
              String[] categories = { "Fiksi", "Ilmu Pengetahuan", "Sejarah", "Biografi", "Teknologi", "Filsafat", "Misteri", "Fantasi", "Seni", "Pengembangan Diri" };
              for (int i = 0; i < categories.length; i++) {
                String value = String.valueOf(i + 1);
            %>
            <option value="<%= value %>" <%= value.equals(selectedCategory) ? "selected" : "" %>>
              <%= categories[i] %>
            </option>
            <% } %>
          </select>
        </div>

        <div class="mb-3">
          <label for="publicationYear" class="form-label">Tahun Terbit</label>
          <input
            type="number"
            class="form-control"
            id="publicationYear"
            name="publicationYear"
            required
            value="<%= request.getAttribute("publicationYear") != null ? request.getAttribute("publicationYear") : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="publisher" class="form-label">Penerbit</label>
          <input
            type="text"
            class="form-control"
            id="publisher"
            name="publisher"
            required
            value="<%= request.getAttribute("publisher") != null ? request.getAttribute("publisher") : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="quantity" class="form-label">Stok</label>
          <input
            type="number"
            class="form-control"
            id="quantity"
            name="quantity"
            required
            value="<%= request.getAttribute("quantity") != null ? request.getAttribute("quantity") : "" %>"
          />
        </div>

        <div class="mb-3">
          <label for="details" class="form-label">Detail Buku</label>
          <textarea
            class="form-control"
            id="details"
            name="details"
          ><%= request.getAttribute("details") != null ? request.getAttribute("details") : "" %></textarea>
        </div>

        <div class="mb-3">
          <label for="imageUrl" class="form-label">URL Gambar</label>
          <input
            type="text"
            class="form-control"
            id="imageUrl"
            name="imageUrl"
            value="<%= request.getAttribute("imageUrl") != null ? request.getAttribute("imageUrl") : "" %>"
          />
        </div>

        <button type="submit" class="btn btn-primary">Tambah Buku</button>
        <a href="${pageContext.request.contextPath}/manage/books" class="btn btn-secondary ms-3">Batal</a>
      </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

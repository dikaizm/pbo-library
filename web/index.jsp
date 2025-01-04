<%@page import="Model.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manajemen Perpustakaan</title>
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
    <%@include file="components/navbar.jsp" %>

    <div class="container mt-5" style="height: 100%;">

      <div class="d-flex justify-content-center mb-4">
        <form action="home" method="get" class="d-flex w-75">
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

      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-5 g-4">
        <% 
          List<Book> books = (List<Book>) request.getAttribute("books");
          if (books != null && !books.isEmpty()) {
            for (Book book : books) {
        %>
        <div class="col">
          <div class="card" style="width: 12rem;">
            <!-- Book Image -->
            <a href="book?id=<%= book.getId() %>" class="text-decoration-none text-dark">
              <div class="card-img-container" style="width: 100%; height: 240px; overflow: hidden;">
                <img src="<%= book.getImageUrl() %>" class="card-img-top img-fluid" alt="<%= book.getTitle() %>" style="object-fit: cover; width: 100%; height: 100%;">
            </div>
              <div class="card-body">
                  <h5 class="card-title"><%= book.getTitle() %></h5>
                  <p class="card-text">Penerbit: <%= book.getPublisher() %></p>
                  <p class="card-text">Kategori: <%= book.getCategory().getName() %></p>
                  <p class="card-text">Stok: <%= book.getQuantity() %></p>
              </div>
          </a>
          </div>
        </div>
        <% 
            }
          } else { 
        %>
        
      </div>
      <p class="text-center mt-5">Belum ada buku tersedia.</p>
        <% } %>
    </div>

  </body>
</html>

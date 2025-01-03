<%@ page import="Model.User" %>
<nav class="navbar navbar-expand-lg navbar-light bg-white">
  <div class="container-fluid">
    <a class="navbar-brand text-dark" href="${pageContext.request.contextPath}/home">Perpustakaan</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item d-flex">
          <% 
            User user = (User) session.getAttribute("user");
            if (user == null) {
          %>
            <form action="${pageContext.request.contextPath}/auth/login" method="get" class="m-0">
              <button type="submit" class="btn btn-link nav-link text-dark">Masuk</button>
            </form>
          <%
            } else {
                String role = user.getRole();
                if ("librarian".equals(role)) {
            %>
                <form action="manage/books.jsp" method="get" class="m-0">
                  <button type="submit" class="btn btn-link nav-link text-dark">Kelola Buku</button>
                </form>
                <form action="manage/users.jsp" method="get" class="m-0">
                  <button type="submit" class="btn btn-link nav-link text-dark">Kelola Pengguna</button>
                </form>
            <% 
                } else if ("student".equals(role)) {
            %>
                <form action="${pageContext.request.contextPath}/book/me" method="get" class="m-0">
                  <button type="submit" class="btn btn-link nav-link text-dark">Buku Saya</button>
                </form>
            <% } %>

            <form action="profile.jsp" method="get" class="m-0">
              <button type="submit" class="btn btn-link nav-link text-dark">Profil</button>
            </form>
            <form action="auth/logout" method="get" class="m-0">
              <button type="submit" class="btn btn-link nav-link text-dark">Keluar</button>
            </form>
          <% } %>
        </li>
      </ul>
    </div>
  </div>
</nav>

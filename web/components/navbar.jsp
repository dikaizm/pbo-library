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
            User userSession = (User) session.getAttribute("user");
            if (userSession == null) {
          %>
            <form action="${pageContext.request.contextPath}/auth/login" method="get" class="m-0">
              <button type="submit" class="btn btn-link nav-link text-dark">Masuk</button>
            </form>
          <%
            } else {
                String role = userSession.getRole();
                if ("librarian".equals(role)) {
            %>
                <form action="${pageContext.request.contextPath}/manage/books" method="get" class="m-0">
                  <button type="submit" class="btn btn-link nav-link text-dark">Kelola Buku</button>
                </form>
                <form action="${pageContext.request.contextPath}/manage/users" method="get" class="m-0">
                  <button type="submit" class="btn btn-link nav-link text-dark">Kelola Pengguna</button>
                </form>
            <% 
                } else if ("student".equals(role)) {
            %>
                <form action="${pageContext.request.contextPath}/book/me" method="get" class="m-0">
                  <button type="submit" class="btn btn-link nav-link text-dark">Buku Saya</button>
                </form>
            <% } %>

            <form action="${pageContext.request.contextPath}/profile" method="get" class="m-0">
              <button type="submit" class="btn btn-link nav-link text-dark">Profil</button>
            </form>
            <form action="${pageContext.request.contextPath}/auth/logout" method="get" class="m-0">
              <button type="submit" class="btn btn-link nav-link text-dark">Keluar</button>
            </form>
          <% } %>
        </li>
      </ul>
    </div>
  </div>
</nav>

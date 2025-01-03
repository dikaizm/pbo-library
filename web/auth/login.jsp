<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
    />
  </head>
  <body class="bg-light">
    <%@include file="../components/navbar.jsp" %>

    <div
      class="container-fluid d-flex flex-column flex-md-row align-items-center min-vh-100"
    >
      <!-- Box Form -->
      <div class="card p-4 shadow ms-5 w-100" style="max-width: 400px">
        <h2 class="text-center text-primary mb-4">Welcome Back!</h2>
        <form method="post" action="login">
          <input type="hidden" name="action" value="login" />
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input
              type="text"
              class="form-control"
              id="email"
              name="email"
              required
            />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input
              type="password"
              class="form-control"
              id="password"
              name="password"
              required
            />
          </div>
          <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <% if (request.getAttribute("error") != null) { %>
        <p class="text-danger mt-3 text-center">Invalid email or password</p>
        <% } %>
        <p class="text-muted text-center mt-3">
          Belum punya akun? <a href="signup">Daftar disini!</a>
        </p>
      </div>

      <!-- Logo Besar -->
      <div
        class="d-flex justify-content-center align-items-center flex-grow-1 mx-3"
      >
        <img
          src="${pageContext.request.contextPath}/assets/images/logo-library.png"
          alt="Logo"
          class="img-fluid"
          style="max-width: 800px"
        />
      </div>
    </div>
  </body>
</html>

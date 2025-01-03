<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Sign Up</title>
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
      <!-- Sign Up Form -->
      <div class="card p-4 shadow ms-5 w-100" style="max-width: 400px">
        <h2 class="text-center text-primary mb-4">Create Your Account</h2>
        <form method="post" action="signup">
          <input type="hidden" name="action" value="signup" />
          <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input
              type="text"
              class="form-control"
              id="name"
              name="name"
              required
            />
          </div>
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
          <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-select" id="role" name="role" required>
              <option value="student">Student</option>
              <option value="librarian">Librarian</option>
            </select>
          </div>
          <button type="submit" class="btn btn-primary w-100">Sign Up</button>
        </form>
        
        <% if (request.getAttribute("error") != null) { %>
        <p class="text-danger mt-3 text-center">
          User already exists. Please choose a different email.
        </p>
        <% } %> <% if (request.getAttribute("success") != null) { %>
        <p class="text-success mt-3 text-center">
          Sign up successful! You can now <a href="login.jsp">login</a>.
        </p>
        <% } %>

        <p class="text-muted text-center mt-3">
          Sudah punya akun? <a href="login">Masuk</a>
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

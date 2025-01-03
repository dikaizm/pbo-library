<%-- 
    Document   : signup
    Created on : Jan 2, 2025, 12:35:30 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Sign Up</h2>
            <form method="post" action="LoginController">
                <input type="hidden" name="action" value="signup">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Role</label>
                    <select class="form-select" id="role" name="role" required>
                        <option value="student">Student</option>
                        <option value="librarian">Librarian</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </form>

            <%
                if (request.getParameter("error") != null) {
            %>
            <p class="text-danger">User already exists. Please choose a different username.</p>
            <%
                }
                if (request.getParameter("signup") != null && request.getParameter("signup").equals("success")) {
            %>
            <p class="text-success">Sign up successful! You can now <a href="login.jsp">login</a>.</p>
            <%
                }
            %>
        </div>
    </body>
</html>
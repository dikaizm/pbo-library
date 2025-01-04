<%@page import="Model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Pengguna</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light pb-5">
    <%@include file="../components/navbar.jsp" %>

    <div class="container mt-5 w-50">
        <% 
            User currentUser = (User) request.getAttribute("user"); 
            boolean userExists = (currentUser != null);
        %>

        <!-- Profile Header -->
        <div class="card mb-4 shadow-sm">
            <div class="card-body text-center">
                <h5 class="card-title">
                    <%= userExists ? currentUser.getName() : "User not found" %>
                </h5>
                <p class="card-text">
                    <%= userExists ? currentUser.getEmail() : "Email not available" %>
                </p>
            </div>
        </div>

        <!-- Update Form -->
        <div class="card">
            <div class="card-header bg-secondary text-white">Update Profile</div>
            <div class="card-body">
                <form action="update-profile" method="post">
                    <% if (userExists) { %>
                        <!-- Name Field -->
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Full Name</label>
                            <input type="text" name="fullName" id="fullName" class="form-control" 
                                   value="<%= currentUser.getName() %>" required>
                        </div>
                        <!-- Email Field -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control" 
                                   value="<%= currentUser.getEmail() %>" required>
                        </div>
                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-success">Save Changes</button>
                    <% } else { %>
                        <p class="text-danger">User data is not available for updating.</p>
                    <% } %>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

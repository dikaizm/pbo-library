<%-- 
    Document   : User
    Created on : Jan 2, 2025, 12:15:23 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.User"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <div class="container mt-5">
        <h2>User Profile</h2>
        <p>Name: <%= ((User) request.getAttribute("user")).getName() %></p>
        <p>Username: <%= ((User) request.getAttribute("user")).getUsername() %></p>
    </div>
</body>
</html>

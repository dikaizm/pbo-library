<%-- 
    Document   : Student
    Created on : Jan 2, 2025, 12:15:01 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Student"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Profile</title>
</head>
<body>
    <div class="container mt-5">
        <h2>Student Profile</h2>
        <p>Name: <%= ((Student) request.getAttribute("student")).getName() %></p>
        <p>Course: <%= ((Student) request.getAttribute("student")).getCourse() %></p>
    </div>
</body>
</html>

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
        <p>Name: <%= ((Student) session.getAttribute("student") != null ? ((Student) session.getAttribute("student")).getName() : "Unknown") %></p>
        <p>Course: <%= ((Student) session.getAttribute("student") != null ? ((Student) session.getAttribute("student")).getCourse() : "Unknown") %></p>        
    </div>
</body>
</html>

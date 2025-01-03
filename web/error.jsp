<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5 text-center">
            <h1 class="text-danger">Oops! Something went wrong.</h1>
            <p>An error occurred while processing your request.</p>
            <p><strong>Error Details:</strong> <%= request.getAttribute("errorDetails") %></p>
            <a href="index.jsp" class="btn btn-primary">Go Back to Home</a>
        </div>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background: linear-gradient(135deg, #f9f9f9, #e3f2fd);
                margin: 0;
                padding: 0;
            }
            .login-container {
                display: flex;
                justify-content: flex-start; /* Membuat form condong ke kiri */
                align-items: center;
                min-height: 100vh;
                padding: 20px 50px;
                margin-left: 90px;
            }
            .login-card {
                background: white;
                border-radius: 15px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 400px;
                width: 100%;
                padding: 30px;
                margin-right: 50px; /* Menambah jarak dengan logo */
            }
            .login-card h2 {
                text-align: center;
                margin-bottom: 30px;
                font-size: 24px;
                font-weight: bold;
                color: #496187;
            }
            .form-control {
                border-radius: 5px;
                font-size: 16px;
            }
            .btn-primary {
                background-color: #7D9BCA;
                border-color: #6782AD;
                border-radius: 5px;
                font-size: 16px;
            }
            .btn-primary:hover {
                background-color: #6782AD;
                border-color: #496187;
            }
            .text-muted {
                font-size: 14px;
                text-align: center;
                margin-top: 20px;
            }
            .logo-container {
                text-align: center;
                flex: 1;
            }
            .logo-container img {
                width: 100%;
                max-width: 800px; /* Membuat logo lebih besar */
                height: auto;
            }
            @media (max-width: 768px) {
                .login-container {
                    flex-direction: column;
                    justify-content: center;
                }
                .login-card {
                    margin-right: 0;
                    margin-bottom: 30px;
                }
                .logo-container img {
                    max-width: 300px; /* Ukuran logo untuk layar kecil */
                }
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <!-- Box Form -->
            <div class="login-card">
                <h2>Welcome Back!</h2>
                <form method="post" action="LoginController">
                    <input type="hidden" name="action" value="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>

                <% if (request.getParameter("error") != null) { %>
                    <p class="text-danger mt-3 text-center">Invalid username or password</p>
                <% } %> 
                <p class="text-muted">Don't have an account? <a href="signup.jsp">Sign up here</a>.</p>
            </div>

            <!-- Logo Besar -->
            <div class="logo-container">
                <img src="assets/images/logo-library.png" alt="Logo">
            </div>
        </div>
    </body>
</html>

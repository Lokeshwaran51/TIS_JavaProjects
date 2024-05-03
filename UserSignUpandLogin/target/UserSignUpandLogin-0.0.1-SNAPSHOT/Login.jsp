<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    
    <script>
        function validate(){
            var email = document.form.email.value;
            document.getElementById('emailError').innerHTML = "";
            var contact = document.form.contact.value;
            document.getElementById('contactError').innerHTML = "";
            var password = document.form.password.value;
            document.getElementById('passwordError').innerHTML = "";
            
            if(email.trim() === "" || email === null){
                document.getElementById('emailError').innerHTML = "Email field is required..";
                return false;
            }
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                document.getElementById('emailError').innerHTML = "Invalid email address..";
                return false;
            }
            if(contact.trim() === "" || contact === null){
                document.getElementById('contactError').innerHTML = "Contact field is required..";
                return false;
            }
            if(password.trim() === "" || password === null){
                document.getElementById('passwordError').innerHTML = "Password field is required..";
                return false;
            }
            return true;
        }
    </script>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(1.0, 1.0, 1.0, 1.0);
            padding: 20px;
            width: 300px;
            /* Increased height for better visibility */
            height: 360px;
        }

        h2 {
            text-align: center;
            color: #333;
            /* Adjusted font size and added fallback fonts */
            font-family: 'Oswald', sans-serif;
            font-weight: 400;
            margin-bottom: 20px;
        }

        table {
            margin-top: 20px;
        }

        td {
            padding: 10px;
            /* Adjusted padding for better alignment */
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #ddd;
            outline: none;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        p {
            text-align: center;
            margin-top: 20px;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
         .form-text {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <!-- JavaScript moved to the bottom of the body -->
    <div>
        <form action="login" method="post" name="form" onsubmit="return validate()">
            <h2>Login Here</h2>
            <table>
                <tr>
                    <td><b>Email:</b></td>
                    <td>
                        <input type="email" name="email" placeholder="someone1@gmail.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}">
                        <span id="emailError" class="form-text"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Contact:</b></td>
                    <td>
                        <input type="text" name="contact" placeholder="Enter your mobile no" pattern="[0-9]{10}">
                        <span id="contactError" class="form-text"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Password:</b></td>
                    <td>
                        <input type="password" name="password" placeholder="Enter your password" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}">
                        <span id="passwordError" class="form-text"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Login"></td>
                </tr>
            </table>
        </form>
        <p>Don't have an account? <a href="Register.jsp">Register Here</a></p>
    </div>
    
    
    
    <% String error = (String) request.getAttribute("error");
       if (error != null && !error.isEmpty()) { %>
       <script>
           alert("<%= error %>");
       </script>
    <% } %>
</body>
</html>

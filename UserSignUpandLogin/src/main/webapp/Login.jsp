<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    
    <script>
    function validate() {
    	
    	//Email Validation  
        var email = document.form.email.value;
        var emailError=document.getElementById("emailError");
        
        if (email.trim() === "" || email == null) {
        	emailError.innerHTML = "Email field is required..";
            return false;
        }
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
        	emailError.innerHTML = "Invalid email address..";
            return false;
        }
        else{
        	emailError.innerHTML="";
        }
        
        //Contact Validation
        var contact = document.form.contact.value;
        var contactError=document.getElementById("contactError");
        
        if (contact.trim() === "" || contact == null) {
            document.getElementById("contactError").innerHTML = "Contact field is required..";
            return false;
        }
        var contactPattern = /^(\d{3})[- ]?(\d{3})[- ]?(\d{4})$/;
    	if (!contactPattern.test(contact)) {
    	    contactError.innerHTML = "Invalid Contact..";
    	    return false;
    	}
       
        }else{
        	emailError.innerHTML="";
        }
        
        //Password Validation
        var password = document.form.password.value;
        document.getElementById("passwordError").innerHTML = "";
        if (password.trim() === "" || password == null) {
            document.getElementById("passwordError").innerHTML = "Password field is required..";
            return false;
        }else{
        	passwordError.innerHTML="";
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
            height: 360px;
        }

        h2 {
            text-align: center;
            color: #333;
            font-family: 'Oswald', sans-serif;
            font-weight: 400;
            margin-bottom: 20px;
        }

        table {
            margin-top: 20px;
        }

        td {
            padding: 10px;
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
    <div>
        <form action="login" method="post" name="form" onsubmit="return validate()">
            <h2>Login Here</h2>
            <table>
                <tr>
                    <td><b>Email:</b></td>
                    <td>
                        <input type="email" id="email" name="email" placeholder="someone1@gmail.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}">
                        <span id="emailError" class="form-text"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Contact:</b></td>
                    <td>
                        <input type="text" id="contact" name="contact" placeholder="Enter your mobile no" pattern="[0-9]{10}">
                        <span id="contactError" class="form-text"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Password:</b></td>
                    <td>
                        <input type="password" id="password" name="password" placeholder="Enter your password" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}">
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
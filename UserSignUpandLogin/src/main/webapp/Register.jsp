<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Registration</title>
    <script src="https://kit.fontawesome.com/c4254e24a8.js"></script>
    <script type="text/javascript">
        function validate() {
        	
           //firstName Validation
            var fname = document.myform.firstName.value;
            var fnameError=document.getElementById("fnameError");
            
            if(fname.trim()===""||fname==null) {
            	fnameError.innerHTML="First Name field can't be blank.";
                return false;
            }
            else{
            	fnameError.innerHTML="";
            }
           var fNamePattern=/^[a-zA-Z]*$/;
           if(!fNamePattern.test(fname)){
        	   fnameError.innerHTML="Name field should not contain numbers...";
       		return false;
       		}
       		else{
       		fnameError.innerHTML = "";
       		}
           
           //LastName Validation
           var lname = document.myform.lastName.value;
           var lnameError=document.getElementById("lnameError");
           
            if(lname.trim()===""||lname==null) {
            	lnameError.innerHTML="Last Name field can't be blank.";
                return false;
            }
            else{
            	lnameError.innerHTML="";
            }
            
            
			//Contact Validation
			var contact = document.myform.contact.value;
			var contactError=document.getElementById("contactError");
			
            var pattern = /^\d{10}$/; // Pattern to match exactly 10 digits
            if(!pattern.test(contact)) {
            	contactError.innerHTML="Contact field must contain exactly 10 numbers.";
                return false;
            }
            else{
            	contactError.innerHTML="";
            }
            if(contact.trim()===""||contact==null) {
            	contactError.innerHTML="Contact field can't be blank.";
                return false;
            }else{
            	contactError.innerHTML="";
            }
            
			//Address Validation
			 var address = document.myform.address.value;
			 var addressError=document.getElementById("addressError");
			 
            if(address.trim()===""||address==null) {
            	addressError.innerHTML="Address field can't be blank.";
                return false;
            }
            else{
            	addressError.innerHTML="";
            }
           
			//Email Validation
			var email = document.myform.email.value;
			var emailError=document.getElementById("emailError");
			
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if(!emailPattern.test(email)) {
            	emailError.innerHTML="Invalid email address.";
                return false;
            }
            else{
            	emailError.innerHTML="";
            }

            if(email.trim()===""||email==null) {
            	emailError.innerHTML="Email field can't be blank.";
                return false;
            }
            else{
            	emailError.innerHTML="";
            }
            
            //Password Validation
            var password=document.myform.password.value;
            var passwordError=document.getElementById("passwordError");
            
			if(password.trim()===""||password==null){
				passwordError.innerHTML="Password field can't be blank.";
				return false;
			}
            var passwordPattern = /^(?=.*[!@#$%^&*])(?=.*\d).{6,}$/;
            if (!passwordPattern.test(password)) {
            	passwordError.innerHTML = "Password must contain at least one special character and one number.";
                return false;
            } else if (password.length > 15) {
            	passwordError.innerHTML = "Password length should be less than 15 characters.";
                return false;
            }
            else{
            	passwordError.innerHTML="";
            }
            return true;
        }
    </script>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 1);
            width: 300px;
            text-align: left;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        button {
            background-color: #5f98e8;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }

        button:hover {
            background-color: #32a881;
        }

        p {
            margin-top: 20px;
            color: #333;
        }

        a {
            color: #3498db;
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
    <div align="center">
        <form id="form" name="myform" action="<%=request.getContextPath()%>/register" method="post" onsubmit="return validate()">
            <h2>Register Here</h2>
            <label>First Name:</label>
            <input type="text" id="fName" name="firstName" style="font-size:16px">
            <span id="fnameError" class="form-text"></span>

            <label>Last Name:</label>
            <input type="text" id="lName" name="lastName" style="font-size:16px">
            <span id="lnameError" class="form-text"></span>

            <label>Contact:</label>
            <input type="text" id="Contact" name="contact"  style="font-size:16px">
            <span id="contactError" class="form-text"></span>

            <label>Address:</label>
            <input type="text" id="Address" name="address" style="font-size:16px">
            <span id="addressError" class="form-text"></span>

            <label>Email:</label>
            <input type="email" id="Email" name="email" style="font-size:16px">
            <span id="emailError" class="form-text"></span>

            <label>Password:</label>
            <input type="password" id="Password" name="password" style="font-size:16px">
            <span id="passwordError" class="form-text"></span>
            <div class="col-auto">
            </div>
            <button type="submit">Register</button>
            <p>Already have an account? <a href="Login.jsp">Login Here</a></p>
        </form>
    </div>
</body>
</html>

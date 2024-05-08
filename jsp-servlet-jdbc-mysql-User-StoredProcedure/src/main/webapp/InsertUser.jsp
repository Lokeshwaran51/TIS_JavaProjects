<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.model.User" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="com.servlet.UserServlet" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Management Form</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">

<style>
body {
    background-color: #f8f9fa;
}
header {
    text-align: center;
    margin-bottom: 20px;
}
form {
    max-width: 600px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(1.0, 1.0, 1.0, 1.0);
}
table {
    width: 100%;
    margin-top: 10px;
}
td {
    padding: 10px;
}
button {
    padding: 10px 20px;
    border-radius: 5px;
    background-color: #28a745;
    color: #fff;
    border: none;
    cursor: pointer;
}
select {
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
    margin: 0 auto;
    border-radius: 5px;
    border: none;
}
button a {
    color: #fff;
    text-decoration: none;
}
</style>

<script>
function validation(){
	//Name Validation
		var name=document.getElementById("name");
		var nameError=document.getElementById("nameError");
		
		if(name.trim()===""||name==null){
			nameError.innerHTML="Name field is required..";
			return false;
		}
		var namePattern= /^[a-zA-Z]*$/;
		if(!namePattern.test(name)){
			nameError.innerHTML="Name field should not contain numbers...";
			return false;
		}
		else{
			 nameError.innerHTML = "";
		}
	//Email Validation	
		var email=document.getElementById("email");
		var emailError=document.getElementById("emailError");

		if(email.trim()===""||email==null){
			emailError.innerHTML="Email field is required..";
			return false;
		}
		var emailPattern=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if(!emailPattern.test(email)){
			emailError.innerHTML="Invalid email address..";
			return false;
		}
		else{
			 nameError.innerHTML = "";
		}
		
	//Contact Validation	
		var contact = document.getElementById("contact");
		var contactError = document.getElementById("contactError");

		if (contact.trim() === "" || contact == null) {
		    contactError.innerHTML = "Contact field is required";
		    return false;
		}

		var contactPattern = /^(\d{3})[- ]?(\d{3})[- ]?(\d{4})$/;
		if (!contactPattern.test(contact)) {
		    contactError.innerHTML = "Invalid Contact";
		    return false;
		}
		else{
			 nameError.innerHTML = "";
		}
		return true;
	}
</script>

</head>
<body>
    <form action="<%= request.getContextPath() %>/insert" method="POST" onsubmit="return validation()" enctype="multipart/form-data">
            <h2 align="center" style="text-decoration:underline">Add New User</h2>
        <div align="center">
            <table>
				<tr>
					<td>
						<button style="background-color: #a83254">
							<a href="<%=request.getContextPath()%>/list"style="text-decoration: none">User List</a>
						</button>
					</td>
				</tr>
				<tr>
                    <td><b>Name :</b></td>
                    <td><input type="text" id="name" class="form-control" name="name">
                    <span id="nameError" style="color:red"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Email :</b></td>
                    <td>
                        <input type="email" id="email" class="form-control" name="email">
                        <span id="emailError" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Contact :</b></td>
                    <td><input type="text" id="contact" class="form-control" name="contact">
                    <span id="contactError" style="color:red"></span>
                    </td>
                </tr>
                <tr>
                    <td><label for="country"><b>Select a country:</b></label></td>
                    <td>
                        <select name="country" id="country" required>
                            <option value="">Select Country</option>                
                             <%
                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root@12345");
                                    Statement stmt = conn.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT country_Name from country");
                                    while (rs.next()) {
                                        String name = rs.getString("country_Name");
                                        %>
                                        <option value="name"><%= name %></option>
                                        <%
                                    }
                                } catch(Exception e) { 
                                    e.printStackTrace(); 
                                } 
                                %>
                        </select>
                    </td>
                </tr>         
                <tr>
                    <td><b>Gender: </b></td>
                    <td>
                        <input type="radio" value="male" id="male" name="gender" required>
                        Male 
                        <input type="radio" value="female" id="female" name="gender">
                        Female
                    </td>
                </tr>
                <tr>
                    <td><b>Area of Interest:</b></td>
                    <td>
                        <input type="checkbox" name="areaOfInterest" value="Playing Football"> Playing Football<br />
                        <input type="checkbox" name="areaOfInterest" value="Playing Cricket"> Playing Cricket<br /> 
                        <input type="checkbox" name="areaOfInterest" value="Listening Music"> Listening Music<br />
                    </td>
                </tr>
                <tr>
                    <td><b>Upload File:</b></td>
                    <td><input type="file" name="ImageName" multiple required></td>
                </tr>
            </table>
            <button type="submit" style="padding: 5px 100px" class="btn btn-success">Add User</button>
        </div>
    </form>
</body>
</html>

<%
String error = (String) request.getAttribute("error");
if (error != null) {
%>
<script>
alert("<%=error%>");
</script>
<%
}
%>

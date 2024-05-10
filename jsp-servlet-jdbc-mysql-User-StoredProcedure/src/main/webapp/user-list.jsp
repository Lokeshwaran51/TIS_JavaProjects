<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
   
    <script>
        function confirmDelete(id) {
            var confirmDelete = confirm("Are you sure you want to delete this user?");
            if (confirmDelete){
                window.location.href = "<%=request.getContextPath()%>/delete?id=" + id;
            }
        }
    </script>
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        header {
            background-color: #E0CA36;
            padding: 10px 0;
        }
        header h4 {
            color: black;
            margin: 1;
            width:135%;
            align:center;
        }
        table {
            margin-top: 20px;
        }
        table th, table td {
            text-align: center;
        }
        table th {
            background-color: tomato;
            color: white;
        }
        table td a {
            margin-right: 10px;   
        }
    </style>
    
</head>
<body>
<header>
    <nav class="navbar navbar-expand-xl navbar-dark" >
        <div class="container"> 
           <b> <a href="<%=request.getContextPath()%>/InsertUser.jsp" class="navbar-brand" style="color:black;border-radius:50%;text-decoration:underline">Add New User</a></b>
           </div> 
          <h4 text-align="center">List Of Users</h4>
    </nav>
</header>
<div class="container">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Contact</th>
            <th>Gender</th>
         	<th>FileName</th>
            <th>AreaOfInterest</th>
            <th style="width:150px">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<User> listUser =(List<User>) request.getAttribute("listUser");
            if(listUser!=null){
                for(User user:listUser){
        %>
		 <tr>
		    <td><%= user.getId() %></td>
		    <td><%= user.getName() %></td>
		    <td><%= user.getEmail() %></td>
		    <td><%= user.getCountry() %></td>
		    <td><%= user.getContact() %></td>
		    <td><%= user.getGender() %></td>
		    <td><a href="<%=request.getContextPath()%>/display image?id=<%= user.getId()%>" onclick="<%=user.getImageName()%>" style="text-decoration:none"><%= user.getImageName() %></a></td>
		    <td><%= user.getAreaOfInterest() %></td>
		    <td>
		        <a href="<%=request.getContextPath()%>/edit?id=<%=user.getId()%>" style="text-decoration:none;">Edit</a>|
		        <a href="javascript:void(0);" onclick="confirmDelete('<%=user.getId()%>');"style="text-decoration:none;">Delete</a><br/>
		    </td>
	</tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
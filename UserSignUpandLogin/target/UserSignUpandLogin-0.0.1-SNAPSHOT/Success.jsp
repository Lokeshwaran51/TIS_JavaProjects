<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="io.jsonwebtoken.Jwts" %>
<%@ page import="io.jsonwebtoken.security.Keys" %>
<%@ page import="java.util.Date" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %> --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
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

        .welcome-container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 1.0);
            width:300px;
            heigth:100px;
        }

        h2 {
            color: #007bff;
            margin-bottom: 10px;
        }

        p {
            color: #555;
            margin-bottom: 20px;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
	<script>
    // Get expiration time from session attribute
    var expirationTime = new Date("<%= session.getAttribute("expirationTime") %>");
    var timeUntilExpiration = expirationTime - new Date();// Calculate time until expiration in milliseconds
														//It calculates the diffrence between expiration time and date
    setTimeout(timeout,timeUntilExpiration);  // Redirect to logout page after expiration time
				
    function timeout() {
        window.location.href = '<%=request.getContextPath()%>/Logout.jsp';
    }				
	</script>
    <div class="welcome-container">
        <h2>Welcome</h2>
        <p>You have successfully logged in.</p>
        <p>
            <a href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
        </p>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Image</title>
</head>
<body>
    <div align="center">
        <h1>Display Image</h1>
        <form action="DisplayServlet" method="post" enctype="multipart\form-data">
            <b>Enter Image Id:</b> <input type="number" name="id" /> 
            <input type="submit">
            <hr />
            <table border="2">
                <tr>    
                    <th>ImageId</th>
                    <th>ImageName</th>
                </tr>
                <% 
                String imgId = (String) request.getAttribute("id");
                String imgFileName = (String) request.getAttribute("img");
                
                if (imgFileName != null && !imgFileName.isEmpty() && imgId != null && !imgId.isEmpty()) {
                %>
                <tr>
                    <td><%= imgId %></td>
                    <td><img src="Images/<%= imgFileName %>" style="width:500px;height:250px"></td>
                </tr>
                <% 
                }
                %>  
            </table>
        </form>
    </div>
</body>
</html>

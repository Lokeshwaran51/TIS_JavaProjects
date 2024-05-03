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
        <form action="DisplayServlet" method="post">
            <b>Enter Image Id:</b> <input type="number" name="id" /> <input type="submit">
            <hr />
        </form>
        
        <table border="1" style="width: 500px">
            <tr>
                <th>Image Id</th>
                <th>Image</th>
            </tr>
            <% 
            String imgFileName = (String) request.getAttribute("img");
            String imgId = (String) request.getAttribute("id");
            if (imgFileName != null && !imgFileName.isEmpty() && imgId != null && !imgId.isEmpty()) {
            %>
            <tr>
                <td><%= imgId %></td>
                <td><img src="Images/<%= imgFileName %>" style="width:300px;height:250px"></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>

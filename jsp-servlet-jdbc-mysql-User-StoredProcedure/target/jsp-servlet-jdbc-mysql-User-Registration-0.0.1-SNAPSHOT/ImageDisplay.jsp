<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Image</title>
</head>
<body>
    <div align="center">
        <table>
            <tr>
                <th>ImageName</th>
            </tr>
            <% 
            String imgFileName = (String) request.getAttribute("ImageName");     
            if (imgFileName != null && !imgFileName.isEmpty()) {
            %>
            <tr>
                <td><img src="Files/<%= imgFileName %>" style="width: 500px; height: 550px;"></td>
            </tr>
            <% 
            } else {
            %>
            <tr>
                <td>No image found.</td>
            </tr>
            <% 
            }
            %>
        </table>
    </div>
</body>
</html>

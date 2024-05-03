<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload to DataBase</title>
</head>
<body>
    <center>
        <h1>File Upload</h1>
        <form method="post" action="ImageServlet" enctype="multipart/form-data">
            <table>
				<tr>
                    <td>First Name: </td>
                    <td><input type="text"name="firstName"></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><input type="text"name="lastName"></td>
                </tr>
                <tr>
                    <td>Upload File: </td>
                    <td><input type="file"name="photo" multiple></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit"value="Save">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
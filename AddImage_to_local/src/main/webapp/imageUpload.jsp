<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload to Local System</title>
</head>
<body>
<h1 align="center">Upload Your Files Here..</h1>
<hr/>
	<div align="center">
		<form action="AddImageServlet" method="post"
			enctype="multipart/form-data">
			<b>Upload File:</b><input type="file" name="files" multiple/> <input
				type="submit" name="submit" value="upload" />
				</form>
	</div>	
</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload Example in JSP and Servlet - Java web
	application</title>
</head>
<body>
	<div>
		<h3>Choose File to Upload in Server</h3>
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" /> <input type="submit"
				value="upload" />
		</form>
	</div>
</body>
</html>
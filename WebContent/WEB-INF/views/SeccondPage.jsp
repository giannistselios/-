<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
	<div align = "center">
		<h1>Product successfully added</h1>
		<form action = "<%= request.getContextPath() %>/add?action=firstpage" method = "get">
			<input type="submit" value="Go back to add a product!"/>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align = "center">
		<h1>Product already exists in our database!</h1>
		<form action = "<%= request.getContextPath() %>/PrdctServletJPA?action=thirdpage" method = "post">
			<input type="submit" value="View the Database"/>
		</form>
		<h1>             </h1>
		<form action = "<%= request.getContextPath() %>/PrdctServletJPA?action=firstpage" method = "get">
			<input type="submit" value="Go back to add a product!"/>
		</form>
	</div>
</body>
</html>
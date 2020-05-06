<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color:red">${message}</h1>
	<h2>Login Here</h2>

	<form action="" method="post">
		<label for="username">Username: </label>
		<input type="text" name="username">
		<br><br>
		<label for="password">Password: </label>
		<input type="password" name="password">
		<br><br>
		<input type="submit" name="submit">
	
	</form>
</body>
</html>
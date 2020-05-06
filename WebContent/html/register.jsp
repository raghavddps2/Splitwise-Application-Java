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
	
	<h2>Register here</h2>
	
	<form action="register" method="post">
	
		<label for="name">Name: </label>
		<input type="text" name="name">
		<br><br>
		<label for="email">Email: </label>
		<input type="email" name="email">
		<br><br>
		<label for="username">Username: </label>
		<input type="text" name="username">
		<br><br>
		<label for="mobile">Mobile No: </label>
		<input type="number" name="mobile">
		<br><br>
		<label for="password">Password: </label>
		<input type="password" name="password">
		<br><br>
		<label for="confirm_password">Confirm Password: </label>
		<input type="password" name="confirm_password">	
		<br><br>
		<input type="submit" name="submit" value="Register">	
		
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${message }</h1>
	<form action="joinGroup" method="POST">
		<label for="groupId">Enter groupId: </label>
		<input type="text" name="groupId">
		<br><br>
		
		<input type="submit" value="Join Group">
	</form>
</body>
</html>
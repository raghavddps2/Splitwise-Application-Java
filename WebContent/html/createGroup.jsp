<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create A Group</title>
</head>
<body>
	
	<form action="createGroup" method="post">
	
		<label for="name">Group Name: </label>
		<input type="text" name="name">
		<br><br>
		
		
		<label for="description">Group Description: </label>
		<input type="text" name="description">
		
		<br><br>
		<input type="submit" name="submit" value="Create Group">	
		
		
	</form>
	
</body>
</html>
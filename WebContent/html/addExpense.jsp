<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.test.beans.Group"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add expense.</title>
</head>
<body>
	<h1 style="color:red">${message}</h1>

	<h2 style="color:blue">Add expense for the group: ${groupId}</h2>	
	<%
	String groupId = (String)request.getAttribute("groupId");
	System.out.println("hello123"+groupId);
	%>
	<form action="addTransaction" method="post">
		
		<input type="hidden" value="<%= groupId %>" name="groupId" hidden><br><br>
		<label for="transactionName">Expense For: </label>
		<input type="text" name="transactionName">
		<br><br>
		
		<label for="transactionDescription">Transaction Description</label>
		<input type="text" name="transactionDescription">
		<br><br>
		
		
		<label for="transactionMoney">Transaction Money(Rs)</label>
		<input type="number" name="transactionMoney">
		<br><br>
		<input type="checkbox" name="selected" value="Select All">
		<label for="selectForAll">Split among all</label>
		<br>
		
		<br>
		<p><b>Select the friends individually if you don't want to split among all friends.</b></p>
		<%
			ArrayList<String> members = (ArrayList<String>)request.getAttribute("members");
				String username = (String)request.getSession().getAttribute("username");
				
				System.out.println(username);
			Iterator<String> iterator = members.iterator();
				while(iterator.hasNext()){
					String member = iterator.next();
					if(!member.equals(username)){
					
		%>
			<input type="checkbox" name="selected" value="<%= member %>">
			<label for="<%= member %>"><%= member %></label>
			<br>
		<%	
					}
				}

		%>
		<br>
		<input type="submit" value="Add Transaction">
	</form>
	
<script>


</script>	
	
</body>

</html>
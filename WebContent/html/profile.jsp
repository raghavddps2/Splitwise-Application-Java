<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.test.beans.Group"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color:red">${message}</h1>
	<%
		String username = (String)request.getSession().getAttribute("username");
	%>
	<h1>Hello ${username}</h1>
	
	<button><a href="logout">Logout</a></button>

	<button><a href="createGroup">Create A Group</a></button>
	<button><a href="joinGroup">Join A Group</a></button>
	
	<h2>Groups created by you</h2>
	
	<table border=2>
  <tr><th>GroupId</th>
    <th>GroupName</th>
    <th>Creation Date</th>
    <th>Action</th>
  </tr>
	<%
		ArrayList<Group> user_made = (ArrayList)request.getAttribute("user_made_grp");
		if(user_made.size() > 0){
			Iterator<Group> iterator = user_made.iterator();
			System.out.println(user_made.size());
			while(iterator.hasNext()){
			Group group = iterator.next();
	%>
    <tr>
      <td><%= group.getGroupId() %></td>
      <td><%= group.getGroupName() %></td>
      <td><%= group.getGroupCreatedDate() %></td>
      <td><button type="submit">Add or View Transactions</button></td>
    </tr>
    <%
		}
		}
    %>
	</tbody>
</table>
	
	
	<h2>Groups you joined</h2>
	
	<table border=2>
  <tr><th>GroupId</th>
    <th>GroupName</th>
    <th>Creation Date</th>
    <th>Group owner</th>
    <th>Action</th>
  </tr>
	<%
		ArrayList<Group> user_joined = (ArrayList)request.getAttribute("user_joined_grp");
		if(user_joined.size() > 0){
			Iterator<Group> iterator1 = user_joined.iterator();
			System.out.println(user_joined.size());
			while(iterator1.hasNext()){
			Group group = iterator1.next();
	%>
    <tr>
      <td><%= group.getGroupId() %></td>
      <td><%= group.getGroupName() %></td>
      <td><%= group.getGroupCreatedDate() %></td>
       <td><%= group.getGroupOwner() %></td>
      <td><button type="submit">Add or View Transactions</button></td>
    </tr>
    <%
		}
		}
	%>
	</tbody>
</table>
	
</body>
</html>
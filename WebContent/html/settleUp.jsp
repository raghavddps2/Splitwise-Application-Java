<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.test.beans.Group"%>
<%@page import="com.test.beans.CashFlowDisplay"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Settle Up</title>
</head>
<body>
	<h2 style="color:red">Hey, please note, you can settle up all the bills only if you are the owner 
	of the group. If you are not the owner the button will be disabled for you</h2>
	<%
		String username = (String) request.getSession().getAttribute("username");
			System.out.println(username+"user");
		String groupOwner = (String)request.getAttribute("groupOwner");
			System.out.println(groupOwner+"owner");
		String groupId = (String)request.getAttribute("groupId");
		System.out.println(groupId+"Id");
		if(username.equals(groupOwner)){
			
	%>
		<h3>Please Note: Once, you press settle up, all the bills will be settled up and this cannot be undone.</h3>
	<form action="settleUp" method="post">
		<input type="hidden" value="<%=groupId %>" name="groupId">
		<button type="SettleUp" id="settleUp">Settle Up</button>
	</form>	
	<%
		}
		else{
	%>
		<a href="SettleUp?groupId=<%= groupId %>"><button disabled>Settle Up</button></a>
	<%
		}
	%>
	
	<h1>Settle Up Status: </h1>
	<%
		ArrayList<CashFlowDisplay> arr=(ArrayList<CashFlowDisplay>) request.getAttribute("settlement");
		Iterator<CashFlowDisplay> iterator = arr.iterator();
		while(iterator.hasNext()){
		CashFlowDisplay cfd = iterator.next();
		String ans = cfd.getPay_by()+" has to pay Rs. "+cfd.getMoney()+" to " +cfd.getPay_to();
		if(cfd.getMoney() != 0){
		
	%>
		<h1><b><%= ans %></b></h1>	
		<h4>Paid To: <%= cfd.getPay_to() %></h4>
		<h4>Paid By: <%= cfd.getPay_by() %></h4>
		<h4>Money: Rs <%= cfd.getMoney() %></h4>
		<h4>Paid To: <%= cfd.getPay_to() %></h4>

		<hr>
	<%
		}
		}
	%>
	
<script>

</script>
</body>
</html>
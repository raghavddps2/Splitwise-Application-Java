<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.test.beans.Group"%>
<%@page import="com.test.beans.Transaction"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

/*PEN STYLES*/

* {
  box-sizing: border-box;
}

body {
  background: #f1f1f1;
  margin: 2rem;
}

$color_white: #fff;
$color_prime: #5ad67d;
$color_grey: #e2e2e2;
$color_grey_dark: #a2a2a2;

.blog-card {
  display: flex;
  flex-direction: column;
  margin: 1rem auto;
  box-shadow: 0 3px 7px -1px rgba(#000, .1);
  margin-bottom: 1.6%;
  background: $color_white;
  line-height: 1.4;
  font-family: sans-serif;
  border-radius: 5px;
  overflow: hidden;
  z-index: 0;
  a {
    color: inherit;
    &:hover {
      color: $color_prime;
    }
  }
  &:hover {
    .photo {
      transform: scale(1.3) rotate(3deg);
    }
  }
  .meta {
    position: relative;
    z-index: 0;
    height: 200px;
  }
  .photo {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-size: cover;
    background-position: center;
    transition: transform .2s;
  }
  .details,
  .details ul {
    margin: auto;
    padding: 0;
    list-style: none;
  }

  .details {
    position: absolute;
    top: 0;
    bottom: 0;
    left: -100%;
    margin: auto;
    transition: left .2s;
    background: rgba(#000, .6);
    color: $color_white;
    padding: 10px;
    width: 100%;
    font-size: .9rem;
    a {
      text-decoration: dotted underline
    }
    ul li {
      display: inline-block;
    }
    .author:before {
      font-family: FontAwesome;
      margin-right: 10px;
      content: "\f007";
    }

    .date:before {
      font-family: FontAwesome;
      margin-right: 10px;
      content: "\f133";
    }

    .tags {
      ul:before {
        font-family: FontAwesome;
        content: "\f02b";
        margin-right: 10px;
      }
      li {
        margin-right: 2px;
        &:first-child {
          margin-left: -4px;
        }
      }
    }
  }
  .description {
    padding: 1rem;
    background: $color_white;
    position: relative;
    z-index: 1;
    h1,
    h2 {
      font-family: Poppins, sans-serif;
    }
    h1 {
      line-height: 1;
      margin: 0;
      font-size: 1.7rem;
    }
    h2 {
      font-size: 1rem;
      font-weight: 300;
      text-transform: uppercase;
      color: $color_grey_dark;
      margin-top: 5px;
    }
    .read-more {
      text-align: right;
      a {
        color: $color_prime;
        display: inline-block;
        position: relative;
        &:after {
          content: "\f061";
          font-family: FontAwesome;
          margin-left: -10px;
          opacity: 0;
          vertical-align: middle;
          transition: margin .3s, opacity .3s;
        }

        &:hover:after {
          margin-left: 5px;
          opacity: 1;
        }
      }
    }
  }
  p {
    position: relative;
    margin: 1rem 0 0;
    &:first-of-type {
      margin-top: 1.25rem;
      &:before {
        content: "";
        position: absolute;
        height: 5px;
        background: $color_prime;
        width: 35px;
        top: -0.75rem;
        border-radius: 3px;
      }
    }
  }
  &:hover {
    .details {
      left: 0%;
    }
  }


  @media (min-width: 640px) {
    flex-direction: row;
    max-width: 700px;
    .meta {
      flex-basis: 40%;
      height: auto;
    }
    .description {
      flex-basis: 60%;
      &:before {
        transform: skewX(-3deg);
        content: "";
        background: #fff;
        width: 30px;
        position: absolute;
        left: -10px;
        top: 0;
        bottom: 0;
        z-index: -1;
      }
    }
    &.alt {
      flex-direction: row-reverse;
      .description {
        &:before {
          left: inherit;
          right: -10px;
          transform: skew(3deg)
        }
      }
      .details {
        padding-left: 25px;
      }
    }
  }
}
</style>
</head>
<body>
	
	<h1 style="color:red">${message}</h1>
	
	<h1>Group details for: <%= request.getAttribute("groupId") %></h1>
	<a href="addTransaction?groupId=<%= request.getAttribute("groupId") %>"><button>Add Expense</button></a>
	
	<h2>Group Details</h2>
	<div>
		
		<%
			Group group = (Group)request.getAttribute("groupDetails");
		%>
		<h3>Group Id: <%= group.getGroupId() %></h3>
		<h3>Group Name: <%= group.getGroupName() %></h3>
		<h3>Group Owner: <%= group.getGroupOwner() %></h3>
		<h4>Group Description: <%= group.getGroupDescription() %></h4>
		<h4>GroupId: <%= group.getGroupId() %></h4>
		<h4>Group creation date: <%= group.getGroupCreatedDate() %></h4>
		
	</div>
	<hr>
	<hr>
	<h2>Group Members</h2>
	<ul>
	<%
		ArrayList<String> members = (ArrayList<String>)request.getAttribute("members");
		Iterator<String> iterator = members.iterator();
		System.out.println(members.size());
		while(iterator.hasNext()){
		String member = iterator.next();
	%>
		<li><%= member %></li>
	<%
		}
	%>
	</ul>
	
	
	<hr><hr>
	
	
	<h2>Group Transactions </h2>
	<%
		ArrayList<Transaction> transactions = (ArrayList<Transaction>)request.getAttribute("transactions");
		Iterator<Transaction> iterator2 = transactions.iterator();
		while(iterator2.hasNext()){
		Transaction transaction = iterator2.next();
	%>
	<div class="blog-card">
    	<div class="description">
    	<h3>Transaction For: <%= transaction.getTransactionFor() %></h3>
      	<h4>Transaction Id:<%= transaction.getTransactionId() %></h4>
      	<h4>Transaction Money:<%= transaction.getMoney() %></h4>
      	<h4>Transaction Done by:<%= transaction.getDoneBy() %></h4>
      	<h4>Transaction Done At: <%= transaction.getTime() %></h4>
      	<p>Transaction Description: <%= transaction.getTransactionDescription() %></p>
     	<p>People involved in transaction</p>
     	<ul>
     	<%

     	ArrayList<String> membersInvolved = transaction.getMembersInvolved();
		Iterator<String> iterator3 = membersInvolved.iterator();
		while(iterator3.hasNext()){
		String member = iterator3.next();
     	%>
     			<li><%= member %></li>
     	<%
			}
     	%>
     	</ul>
     	<hr><hr>
     </div>
  </div>
  
	<%
		}
	%>
	
</body>
</html>
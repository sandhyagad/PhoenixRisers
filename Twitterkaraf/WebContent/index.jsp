<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="styles/main.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Welcome!!!</h2>
<div class="twittercontent">
<div class="left-content">
	<form class="formfields" action="TwitterServlet?name=gettimeline" method="post">
	<button type="submit"><span class="glyphicon glyphicon-home"></span></button>
	</form>
	<form class="formfields" action="TwitterServlet?name=getfavoritetweets" method="post">
	
		<button type="submit"><span class="glyphicon glyphicon-heart"></span></button>
	</form>
	<form class="formfields" action="TwitterServlet?name=gettrends" method="post">
	<button type="submit">Trends</button>
	</form>	
<form class="formfields" action="TwitterServlet?name=followerslist" method="post">
		<input type="text" name="twitterid" placeholder="twitterid" value="">
		<button type="submit">Followers</button>
	</form>
	
	
	</div>
	<div class="middle-content">
	<form class="formfields" action="TwitterServlet?name=statusupdate" method="post">
	<input type="text" placeholder="What's happening?" name="status">
		<button type="submit">Tweet</button>
	</form>
	</div>
	<div class="right-content">
	<form class="formfields" action="TwitterServlet?name=blockuser" method="post">
	    <input type="text" placeholder="twitterid" name="twitterid">
		<button type="submit">Block this user</button>
	</form>
	<form class="formfields" action="TwitterServlet?name=blocklist" method="post">
		<button type="submit">Block list</button>
	</form>
<form class="formfields" action="TwitterServlet?name=getlanguage" method="post">
	<button type="submit">Get Languages</button>
	</form>
	</div>
	</div>
<div class="response">
		<%
			try {
				
				if (session.getAttribute("response") != null) { %>
					<h3> ${name} </h3>
				<% 	List<String> responses = (List<String>) session.getAttribute("response");
					for (String a : responses) {
		%>
		<div class="getresponse"><%=a%></div>
		<hr>
		<%
			}
				}
			} catch (Exception e) {
			}
		%>
	</div>

</body>
</html>
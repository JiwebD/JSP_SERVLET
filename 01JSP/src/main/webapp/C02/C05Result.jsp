<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String col1 = request.getParameter("col1");
    String col2 = request.getParameter("col2");
    String col3 = request.getParameter("col3");
    String col4 = request.getParameter("col4");
    String style = request.getParameter("style");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	ul{
		list-style:none;
		padding:0;
		margin:0;
	}
	a{text-decoration:none;}
		header{
		width:100%;
	}
.wrapper>header>nav>ul{<%=style%>}
</style>
<body>
	

	<div class="wrapper">
	<header>
		<div class="top-header"></div>
		<nav>
			<ul>
	    	<li><a href=""><%=col1 %></a></li>
      	  	<li><a href=""><%=col2 %></a></li>
      	  	<li><a href=""><%=col3 %></a></li>
      	  	<li><a href=""><%=col4 %></a></li>
			</ul>
		</nav>
	</header>
		<main>
			<section></section>
		</main>
		<footer></footer>
	</div>
	
	<script>
		
	</script>
</body>
</html>
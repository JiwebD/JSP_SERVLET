<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	:root{}
	html{}
	*{	 box-sizing:border-box;}
	body{padding:0;margin : 0;}
	ul{list-style:none;margin:0;padding:0;}
	a{text-decoration:none; color:black;}
	.wrapper{background-color: lightgray;}
	.wrapper>header{height:80px;}
	.wrapper>nav{height:50px;}
	.wrapper>main{ height :calc(100vh - 80px - 50px - 80px);}
	.wrapper>main h2{
		text-align:center;
		font-size:1.8rem;
		font-weight:400;
		
	}
	.wrapper>main table{
		border:1px solid;
		border-collapse:collapse;
		min-width:500px;
		min-height:350px;
		margin: 0 auto;
		
		
	}
	.wrapper>main table th,
	.wrapper>main table td{
		min-width:80px !important;
		min-height:25px !important;
		border:1px solid;
		text-align:center;
	}
	.wrapper>main table th{
		background-color:lightgray;
	}
	.wrapper>footer{height:80px;}
	
</style>
<body>

	<div class="wrapper">
		<header>
		<!--  -->
		<%@include file="/layouts/Header.jsp" %>
		
		</header>
		
		<nav>
		<!--  -->
		<%@include file="/layouts/Nav.jsp" %>
		
		</nav>
		
		<main>
			<h2>과정평가형 자격 CBQ</h2><br />

		</main>
		
		<footer>
		<!--  -->
		<%@include file="/layouts/Footer.jsp" %>
		
		</footer>
	</div>

</body>
</html>
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
	*{ box-sizing:border-box;}
	body{padding:0;margin:0;}
	ul{list-style:none;margin:0;padding:0;}
	a{text-decoration:none; color:black;}
	.wrapper{}
	.wrapper>header{height:80px;}
	.wrapper>nav{height:50px;}
	.wrapper>main{height : calc(100vh - 80px - 50px - 80px);}
	.wrapper>main table{
		border:1px solid;
		border-collapse:collapse;
	}
	.wrapper>main table th,
	.wrapper>main table td{
		min-width:80px;
		min-height:35px;
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
			<h3>무언가의 내용이 들어있음.</h3><br />
			<h3>내용</h3><br />
			<h3>내용</h3><br />
			<h3>내용</h3><br />
			<h3>내용</h3>
		</main>
		
		<footer>
		<!--  -->
		<%@include file="/layouts/Footer.jsp" %>
		
		</footer>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	:root{}
	*{}
	a{}
	ul{}
	body{}
	.wrapper{}
	.wrapper>header{}
	.wrapper>header>.top-header{}
	.wrapper>header>.nav{}
	.wrapper>main{}
	.wraper>section{}
	.wrapper>footer{}
	
</style>
</head>
<body>

	<div class="wrapper">
		<header>
			<!-- topHeader -->
			<%@ include file="./layouts/topHeader.jsp" %>
			<!-- Nav -->
			<%@ include file="./layouts/Nav.jsp" %>
			
		</header>
		<main>
			<section>MAIN>SECTRON 영역</section>
		</main>
		<!-- Footer -->
		<%@ include file="./layouts/Footer.jsp" %>
		
	</div>
	

</body>
</html>
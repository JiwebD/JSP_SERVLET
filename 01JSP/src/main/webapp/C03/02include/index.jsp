<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	:root{}
	*{box-sizing:border-box;padding:10px;border:1px solid;}
	a{}
	ul{}
	body{padding : 0; margin:0;}
	.wrapper{}
	.wrapper>header{min-height:100px;}
	.wrapper>header>.top-header{min-height:25px;}
	.wrapper>header>.nav{min-height:75px;}
	.wrapper>main{min-height:calc(100vh - 350px);background-color: lightgray;}
	.wrapper>main>section{}
	.wrapper>footer{min-height:250px;}
	
</style>
</head>
<body>

<!-- 
include지시자
- 여러 jsp 페이지에서 특정 파일의 내용을 삽입하고자 할때 사용
- 보통 jsp 여러 페이지에서 공통적으로 포함하는 내용이 있을때 활용
 -->

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
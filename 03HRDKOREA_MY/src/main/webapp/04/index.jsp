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
		/* border-collapse:collapse; */
		min-width:500px;
/* 		min-height:350px; */
		margin: 0 auto;
	}
	.wrapper>main table th,
	.wrapper>main table td{
		min-width:80px ;
		height:25px ;
		border:1px solid;
		text-align:center;
	}
	.wrapper>main table input,
	.wrapper>main table select{
		min-width: 200px;
	}
	
	.wrapper>footer{height:80px;}
</style>
<body>

<!-- 
		select c.regist_month, m.c_no, m.c_name, t.class_name, c.class_area, c.tuition, m.grade
		from TBL_MEMBER_202201 m
		join TBL_CLASS_202201 c
		on m.c_no=c.c_no
    	join TBL_TEACHER_202201 t
	    on c.teacher_code = t.teacher_code;
 -->
	<%@page import="java.text.*,java.time.*,java.time.format.*,Utils.*,java.util.*" %>
	<%
	//모든 회원정보 가져오기
			List<ClassJoinTeacher> join_list = DBUtils.getInstance().selectAllClassTeacher();
	%>
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
			<h2>강사매출현황</h2><br />
			
			<table>
				<tr>
					<th>강사코드</th>
					<th>강의명</th>
					<th>강사명</th>
					<th>총매출</th>
				</tr>
				<%
				for(ClassJoinTeacher dto : join_list)
				{
				%>
				<tr>
					<td><%=dto.getTeacher_code() %></td>
					<td><%=dto.getClass_name() %></td>
					<td style="text-align:right;"><%=dto.getTeacher_name() %></td>
					
					<%
						int tuition = Integer.parseInt(dto.getTuition());
						DecimalFormat formetter = new DecimalFormat("###,###");
					%>
					<td style="text-align:right;"><%="\\"+formetter.format(tuition) %></td>
				</tr>
				<%
				}
				%>
			</table>

		</main>
		
		<footer>
		<!--  -->
		<%@include file="/layouts/Footer.jsp" %>
		
		</footer>
	</div>

</body>
</html>
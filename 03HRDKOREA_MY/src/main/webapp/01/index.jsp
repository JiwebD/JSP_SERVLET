<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Utils.DBUtils"%>
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
	.wrapper{}
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
			<h2>강사조회</h2><br />
			<!--  -->
			
			<%@page import="java.time.*,java.text.DecimalFormat,Utils.*,java.util.*" %>
			<%
				List<TeacherDto> list = DBUtils.getInstance().selectAllTeacher();
			%>
			<table>
				<tr>
					<th>강사코드</th>
					<th>강사명</th>
					<th>강의명</th>
					<th>수강료</th>
					<th>강사자격취득일</th>
				</tr>
				
				<%
					for(TeacherDto teacherDto : list){
				%>		
					<tr>
						<td><%=teacherDto.getTeacher_code() %></td>
						<td><%=teacherDto.getTeacher_name() %></td>
						<td><%=teacherDto.getClass_name() %></td>
						<%
							int price = Integer.parseInt(teacherDto.getClass_price());	
							DecimalFormat formatter = new DecimalFormat("###,###");
							
							String formaprice = formatter.format(price);
						%>
						<td><%="\\"+formaprice %></td>
						
						<%
							String date = teacherDto.getTeacher_regist_date();
							//INFMT
							DateTimeFormatter inFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
							LocalDate localDate = LocalDate.parse(date,inFmt);
							//OUTFMT
							DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
							String formatdate = outFmt.format(localDate);
							/* out.print("<td>"+localDate.format(outFmt)+"</td>"); */
						%>
						<td><%=formatdate %></td>
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
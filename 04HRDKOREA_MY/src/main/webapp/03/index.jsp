<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
		min-height:35px !important;
		border:1px solid;
		text-align:center;
	}
	.wrapper>main table th{
		background-color:lightgray;
	}
	.wrapper>footer{height:80px;}
	
</style>


</head>
<body>

	<%@page import="Utils.*,java.util.*" %>
	
	<!-- 파라미터 받기(액션 태그) -->
	<jsp:useBean id="voteDto2" class="Utils.VoteDto" scope="request" />
	<jsp:setProperty name="voteDto2" property="*" />
	
	<div class="wrapper">
		<!--  -->
		<%@include file="/layouts/Header.jsp" %>
		
		<!--  -->
		<%@include file="/layouts/Nav.jsp" %>
		
		<main>
			
			<h2>투표검수조회</h2>
			<table>
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>나이</th>
					<th>성별</th>
					<th>후보번호</th>
					<th>투표시간</th>
					<th>유권자확인</th>
				</tr>
				<%@page import="Utils.*,java.time.LocalTime,java.time.Period,java.time.LocalDate,java.time.format.DateTimeFormatter" %>
				
				<%
					List<VoteDto> list = DBUtils.getInstance().selectAllVote();
				%>
				
				<%
					for(VoteDto votedto : list){
				%>
				
				<tr>
					<td><%=votedto.getV_name() %></td>
					
					<%
						String birthYear = votedto.getV_jumin().substring(0, 6);
						System.out.println("birthYear : " + birthYear);
						
						int yy = Integer.parseInt(birthYear.substring(0,2));
						int now = LocalDate.now().getYear()%100;
						System.out.println("now : " + now);
						if(yy > 0 && yy <=now){	//01년생 - 24년생
							birthYear = "20"+birthYear;
						}
						else{
							birthYear = "19"+birthYear;
						}
						
						
						//입력 포맷(yyMMdd)
						DateTimeFormatter in = DateTimeFormatter.ofPattern("yyyyMMdd");
						LocalDate inbirthYear = LocalDate.parse(birthYear,in);
						//출력 포맷(yyyy년MM월dd일생)
						DateTimeFormatter outformat = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
						String outbirthYear = inbirthYear.format(outformat);


						
						out.print("<td>" + outbirthYear + "</td>");
					%>

					
					<%
/* 						String age = voteDto2.getV_jumin().substring(0, 6);	
						System.out.println("age : " + age);
						
						age = "19" + age;	//19 + 860918 */
						DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyyMMdd");
						LocalDate date = LocalDate.parse(birthYear,formatter3);
						System.out.println("date : " + date);
						
						//현재 날짜
						LocalDate today = LocalDate.now();
						
						int manAge = Period.between(date,today).getYears();
						out.println("<td>만 "+manAge+ "세</td>");
					%>
					
					
					<%
						char gender = votedto.getV_jumin().charAt(6);
						System.out.println("gender : " +gender);
						if(gender%2==0)
							out.print("<td>여자</td>");
						else
							out.print("<td>남자</td>");
					%>
					<td><%=votedto.getM_no() %></td>
					<%
						String time = votedto.getV_time();
						DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("HHmm");
						DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("HH:mm");
						
						LocalTime parsedtime = LocalTime.parse(time,formatter4);
						String formattedTime = parsedtime.format(formatter5);
					%>
					<td><%=formattedTime %></td>
					
					<%
						String confirm = votedto.getV_confirm();
						
						if(confirm.equals("Y"))
						{
							confirm = "확인";
						}
						else{
							confirm = "미확인";
						}
					%>
					<td><%=confirm %></td>
				</tr>
				<%
					}
				%>
			</table>

			
		</main>
		
		<!--  -->
		<%@include file="/layouts/Footer.jsp" %>
	
	</div>
	

</body>
</html>
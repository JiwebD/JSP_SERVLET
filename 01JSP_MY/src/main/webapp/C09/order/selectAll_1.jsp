<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 
	품목별 총합 (총합별 내림차순)
	select category, sum(price*quantity) from tbl_order
	group by category
	having sum(price*quantity) >= 50000
	order by sum(price*quantity)desc;
 -->    
 
<%@page import="C04.UserDto,C09.*,java.util.*,java.time.format.DateTimeFormatter" %>

<%
	List<OrderDto> list = DBUtils.getInstance().selectAllOrder();
	List<OrderDto2> list2 = DBUtils.getInstance().selectDate_Avg();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
	table, th, td {border:1px solid black; border-collapse : collapse;}
</style>
<body>

	<h1> 품목별 총액 (50000만원 이상)</h1>
	<%
	
	%>
	<table>
		<tr>
			<th>품목</th>
			<th>총합</th>
		</tr>
		
		<%
			for(OrderDto orderDto : list)
			{
		%>	
			<tr>
				<th><%=orderDto.getCATEGORY() %></th>
				<th><%=orderDto.getSUM() %></th>
			</tr>
			
		<%
			}
		%>
		
	</table>
	
		<h1>날짜별 구매 총평균</h1>
		<!-- 
			select order_date,round(avg(price*quantity),2)
			from tbl_order
			group by order_date;
		 -->
		 <table>
		 	<tr>
		 		<th>날짜</th>
		 		<th>평균</th>
		 	</tr>
		 	
		 	<%
		 	for(OrderDto2 orderDto : list2)
		 	{
		 	%>
		 	
		 	<tr>
		 		<th><%= orderDto.getOrder_date() %></th>
		 		<th><%= orderDto.getAverage() %></th>
		 	</tr>
		 	
		 	<%
		 	}
		 	%>
		 </table>
		 
		 
		<h1>날짜 포맷</h1>
		<!-- 
			select order_date,round(avg(price*quantity),2)
			from tbl_order
			group by order_date;
		 -->
		 <table>
		 	<tr>
		 		<th>날짜</th>
		 		<th>평균</th>
		 	</tr>
		 	
		 	<%
		 	for(OrderDto2 orderDto : list2)
		 	{
		 		//yyyy.MM.dd
		 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		 	%>
		 	
		 	<tr>
		 		<th><%= orderDto.getOrder_date().format(formatter) %></th>
		 		<th><%= orderDto.getAverage() %></th>
		 	</tr>
		 	
		 	<%
		 	}
		 	%>
		 </table>

</body>
</html>
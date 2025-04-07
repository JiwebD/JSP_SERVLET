<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 
		지역_날짜별 구매총액
		select u.addr , o.order_date, sum(o.price*o.quantity)
		from tbl_user u
		join tbl_order o
		on u.userid=o.userid
		group by u.addr, o.order_date
		order by u.addr asc , sum(o.PRICE*o.QUANTITY) desc;
		
		구매하지 않은 고객
		INNER JOIN
		select *
		from TBL_USER
		left outer join tbl_order
		on tbl_user.userid=tbl_order.userid
		where tbl_order.userid is null;
 -->
 
<%@ page import="C04.*,C09.*,java.util.*" %>

<%
	List<OrderDto3> list = DBUtils.getInstance().selectAllOrder3();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
		<h1>지역_날짜별 구매총액</h1>
		<table>
			<tr>
				<th>지역</th>
				<th>날짜</th>
				<th>총액</th>
			</tr>
			
			<%
				for(OrderDto3 orderDto3 : list)
				{			
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			%>
			
			<tr>
				<td><%=orderDto3.getAddr() %></td>
				<td><%=orderDto3.getOrder_date().format(format) %></td>
				<td><%=orderDto3.getSum_price() %></td>
			</tr>
			
			<%
				}
			%>
				
				
		</table>
</body>
</html>
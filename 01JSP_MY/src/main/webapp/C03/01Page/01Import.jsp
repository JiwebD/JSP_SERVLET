<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Scanner,C03.SimpleDto" %>
<%-- 
<%
	java.util.Scanner sc = new java.util.Scanner(System.in);
	java.util.Scanner 경로 import하면 아래처럼 귀찮게 경로 안적어도 됨.
	=> Scanner sc = new Scanner(System.in);
%>
 --%>
<%
	Scanner sc = new Scanner(System.in);

	SimpleDto dto = new SimpleDto("홍길동", 55, "대구");
	System.out.println("DTO : " + dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 name : <%=dto.getName() %> <br/>
 age : <%=dto.getAge() %> <br/>
 addr : <%=dto.getAddr() %> <br/>
 

</body>
</html>
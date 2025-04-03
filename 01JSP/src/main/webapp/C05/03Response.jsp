<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		response : 
		타입(클래스) : HttpServletResponse
		설명 : 클라이언트에게 응답을 보내는 객체
	 -->
<%@ page import="java.io.*" %>
 <%
 	//리다이렉트
 	//response.sendRedirect("./02Request.jsp");
 	//에러페이지로보냄 HttpServletResponse클래스 안에 여러 가지 함수 있음
 	
 	//408 에러 - 요청 제한 시간 초과
 	//response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT);
 	
 	//404 Error - 찾을 수 없음
 	//response.sendError(HttpServletResponse.SC_NOT_FOUND,"해당 페이지를 찾을수가 없습니다.");
 	//403 Error - 금지됨
 	//response.sendError(HttpServletResponse.SC_FORBIDDEN,"접근금지됨!");
 	//5xx 에러 - 잘못된 게이트웨이
 	//response.sendError(HttpServletResponse.SC_BAD_GATEWAY,"서버 장애 발생!");
 	
 	/* 새로고침 */
 	// response.setIntHeader("Refresh",3);
 	
 	/* OutStream 추출 */
/*  ServletOutputStream bout = response.getOutputStream();
 	bout.write('a');
 	bout.write(98);	//b
 	bout.flush();
 	bout.close(); */
 	
 	PrintWriter o = response.getWriter();
 	o.write("<h1>HELLO WORLD</h1>");
 %>
 
 	<%@ page import="java.util.*" %>
 	<%=new Date() %>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	//<<내장 객체의 영역>> 
	
	
	// PageContext : JSP 페이지의 모든 영역에 접근할 수 있는 객체
	//좁은 영역의 객체는 더 넓은 영역의 객체를 꺼내올 수 있다.
	System.out.println("pageContext : " + pageContext);
	System.out.println("pageContext's get request : " + pageContext.getRequest());
	System.out.println("pageContext's get response : " + pageContext.getResponse());
	System.out.println("pageContext's get session : " + pageContext.getSession());
	System.out.println("pageContext's get application : " + pageContext.getServletContext());

	System.out.println("project path : " + pageContext.getServletContext().getContextPath()); //project경로
	
%>
	
	<!-- 표현식 -->
	PROJECTPATH : <%=pageContext.getServletContext().getContextPath() %>
	<hr/>
	<!-- EL -->
	PROJECTPATH(EL) : ${ pageContext.request.contextPath }

</body>
</html>
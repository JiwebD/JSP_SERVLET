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
	EL(Expression Language) : 내장객체의 Scope 참조
	EL을 사용해서 여러 결과값들을 꺼내올 수 있다.
	pageScope : 현재 JSP 페이지 내에서만 사용가능한 속성/기능 (관련 내장객체 : pageContext)
	requestScope : 하나의 요청 처리동안 (관련 내장객체 : request)
	sessionScope : 일정기간 동안(지정된 혹은 기본값) (관련 내장객체 : session)
	applicationScope : 서버종료시 동안 (관련 내장 객체 application)
 -->
 <%@page import="C04.UserDto" %>
 <%
 // 중복값이 아닐때
 //page
 	pageContext.setAttribute("P_ATTR", "P_ATTR_VALUE");
 //request
 	request.setAttribute("R_ATTR", "R_ATTR_VALUE");
 //session
 	session.setAttribute("S_ATTR", "S_ATTR_VALUE");
 //application
 	application.setAttribute("A_ATTR", "A_ATTR_VALUE");
 
 // 중복 속성이 존재할 경우 가장 좁은(우선순위 높은) 영역의 값부터 찾는다.
 //page
	//pageContext.setAttribute("DUP", "PAGECONTEXT_VALUE");
 //request
 	//request.setAttribute("DUP", "REQUEST_VALUE");
 //session
 	//session.setAttribute("DUP", "SESSION_VALUE");
 //application
 	application.setAttribute("DUP", "APPLICATION_VALUE");
 

 %>

<!-- EL : PARAM -->
USERNAME : ${param.username} <br />
PASSWORD : ${param.password} <br />
<hr />
<!-- EL : ATTRIBUTE(중복값이 아닐때) -->
<!-- pageScope -->
PAGECONTEXT's ATTR : ${P_ATTR} <br />
PAGECONTEXT's ATTR : ${pageScope.P_ATTR} <br />

<!-- requestScope -->
REQUEST_CONTEXT's ATTR : ${R_ATTR} <br />
REQUEST_CONTEXT's ATTR : ${requestScope.R_ATTR} <br />

<!-- sessionScope -->
SESSION_CONTEXT's ATTR : ${S_ATTR} <br />
SESSION_CONTEXT's ATTR : ${sessionScope.S_ATTR} <br />

<!-- applicationScope -->
APPLICATION_CONTEXT's ATTR : ${A_ATTR} <br />
APPLICATION_CONTEXT's ATTR : ${applicationScope.A_ATTR} <br />
<hr />
<hr />
<!-- EL : ATTRIBUTE(중복값일때) -->
DUPLICATED VALUE : ${DUP} <br />
<hr />

<!-- EL : OBJECT -->
<%
	UserDto userDto = new UserDto("user1","1234","ROLE_USER");
	request.setAttribute("userDto", userDto);
%>
표현식 : <%=userDto.getUserid() %> <br />
EL : ${userDto.userid } <br />

<!-- EL : 연산자 -->
연산 : <%= 1+"1"%> <br />
EL은 +연산자를 문자열 연결용으로 쓰이지 않음 1+"1" 자동으로 숫자로 변환하려 시도 <br />
EL : ${1+"1"} <br />	

<hr />
<!-- NULL CHECK   empty 값 유무를 체크 == 값이있냐? null이면 true, 값이 있으면 false  -->
NULL : ${null} <br />
empty NULL : ${empty null} <br />

empty NULL : ${empty TEST} <br />
empty not NULL : ${!empty null} <br />




</body>
</html>
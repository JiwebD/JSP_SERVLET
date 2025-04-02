<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--     
	<< 지시자 >>
	목적 : 0JSP 페이지에 대한 전역적인 설정을 지정할 때 사용.
	표현식 : 
		<%@ ... %>
		JSP 컨테이너에게 페이지 처리 방식 등을 알려주는 역할
	
	주요 지시자 3가지
		- page	  : <%@ page 속성="값" %>
					 JSP 페이지의 전체적인 설정 지정(인코딩, import, 에러 페이지 등)
					자주 사용하는 속성
					- language	  : 사용할 스크립트 언어(기본값:java)
					- contoentType: MIME 타입 문자 인코딩 설정
					- import	  : 자바 클래스 import (여러 개 가능)
					- session	  : 세션 사용 여부 (기본값: true)
					- errorPage	  : 에러 발생 시 이동할 페이지
					- isErrorPage : 현재 페이지가 에러 처리 페이지인지 여부
					
					 
		- include : <%@ include file="파일명" %>
					 정적인 리소스파일을 JSP 페이지에 포함시키는 작업 (컴파일 시 포함)
					 ex) <%@ include file="header.jsp" %>
						
		- taglib  : <%@ taglib prefix="접두어" uri="태그라이브러리URI" %>
					 커스텀 태그 라이브러리 사용을 위한 태그 라이브러리 선언
					 ex) <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 --%>

</body>
</html>
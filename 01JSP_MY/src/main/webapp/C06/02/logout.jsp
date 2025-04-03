<%@page import="org.apache.jasper.tagplugins.jstl.core.Remove"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%

	session.invalidate();
	out.println("<script>alert('LOGOUT 성공! Login PAGE로 이동합니다.');location.href='./login_form.jsp?message=Logout_Successful'</script>");


		
	//01 ID 확인(DB 조회 - 생략)
	//02 PW 확인(일치여부 확인)
	//03 사용자 상태정보(인증됨)를 Session 저장
	//04 로그인 처리후 MAIN PAGE REDIRECT

%>
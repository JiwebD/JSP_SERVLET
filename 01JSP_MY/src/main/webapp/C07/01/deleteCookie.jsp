<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    

<%
	String cookieName = request.getParameter("cookieName");
	if(cookieName!=null){
		Cookie cookie = new Cookie(cookieName,null);	//빈 Value의 쿠키 생성
		cookie.setMaxAge(0);	//쿠키유지시간0초(만료설정)
	 	cookie.setPath("/");	// 이 쿠키는 루트("/") 이하 모든 경로에서 접근 가능함
        						// 단, 삭제하려면 생성 시 path와 동일해야 함 
		response.addCookie(cookie);//만료처리 전달
	}
	
	out.println("<script>alert('쿠키삭제완료!'); location.href='getCookie.jsp';</script>");
	//만료쿠키 전달 후 다음이동 위치 전달(REDIRECT)
	//response.sendRedirect("./getCookie.jsp");
%>
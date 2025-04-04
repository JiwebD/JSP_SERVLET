<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
    <!-- 
    	Cookie
    	문자열 데이터만 저장가능
    	4Kbyte 이하의 공간을 차지
    	여러개의 쿠키 설정가능(최대 3000개)
    	도메인당 20개까지 저장가능
    	저장한도를 초과하면 최근에 사용되지 않는 쿠키부터 자동삭제
     -->
     
<%
	Cookie cookie1 = new Cookie("myCookie1","myCookie1Value");
	cookie1.setMaxAge(30);	//쿠키 유지 시간(-1:기본값 : 파일생성 x, 브라우저가 종료될때까지 쿠키 저장)
							//30초 설정
	cookie1.setPath("/");
	
	Cookie cookie2 = new Cookie("myCookie2","myCookie2Value");
	cookie2.setMaxAge(60*5);//5분 설정
	cookie2.setPath("/");
	
	response.addCookie(cookie1);
	response.addCookie(cookie2);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<a href="./getCookie.jsp">쿠키확인하기</a>

</body>
</html>
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
		request : 
		타입(클래스) : HttpServletRequest
		설명 : 클라이언트 요청 정보를 담고 있는 객체
	 -->
    <%
    	String protocol = request.getProtocol();
    	String serverName = request.getServerName();
    	int serverPort = request.getServerPort();
    	String removeAddr = request.getRemoteAddr();
    	String remoteHost = request.getRemoteHost();
    	String method = request.getMethod();
    	StringBuffer requestURL = request.getRequestURL();
    	String requestURI = request.getRequestURI();
    	String Browser = request.getHeader("User-Agent");
    	String fileType = request.getHeader("Accept");
    	
    %>
    
    	프로토콜 : <%=protocol %><br>
    	서버이름 : <%=serverName %><br>
    	서버포트 : <%=serverPort %><br>
    	고객주소 : <%=removeAddr %><br>
    	고객이름 : <%=remoteHost %><br>
    	요청함수 : <%=method %><br>
    	요청경로 : <%=requestURL %>      전체 URL을 StringBuffer로 반환<br>	
    	요청경로 : <%=requestURI %>      서버 주소 뒤 경로만 문자열(String)로 반환<br>	
    	브라우저 : <%=Browser %><br>
    	파일타입 : <%=fileType %><br>

</body>
</html>
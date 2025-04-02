
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
	
	
	CLIENT (VIEW(요청)) -> Server컴퓨터(Apache							 + Tomcat)
									 Apache는 정적 파일처리(html,css등) 	   Tomcat은 JSP/Servlet 같은 동적 웹 어플리케이션 처리
									  .jsp
									  <html>
									  ...JAVA스크립트코드 		-> 		해석기(Tomcat 내의JSP 엔진) Servlet(Java코드)파일로 변환 
									  								-> JAVA파일변환(Servlet) => .jsp>.java 
									  								-> 컴파일 .java파일을 .class로 변환
									  								-> .class 
									  								-> JVM에서 .class 실행 > HTML코드 생성
									  								-> 클라이언트에 HTML 결과 응답 동착처리
									  
									  </html>
									  
									 
	//jsp 코드들은 웹 브라우저에서 개발자환경도구에서 안보인다. 표현식 안의 값만 보인다.
	서블릿 파일 생성위치
	workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\01JSP...

			
	<<JSP 기본>>	선언문 / 표현식 / 스크립틀릿 / 주석	
							 
	 1. 선언부 : 
	 		JSP 내에 멤버 변수나 메서드 선언이 필요할 때 사용합니다. 페이지 전체에서 공유됨.
	 		선언문(서블릿으로 변환되는 자바파일의 속성/기능을 추가)
		class Simppe{
				//속성    -  선언문을 통해 속성 부분을 만들 수 있다.
				
				//기능	 -	선언문을 통해 기능 부분을 만들 수 있다.
		}

	  -->

	<%!//서블릿파일의 멤버변수(속성)
	String name = "HONG GIL DONG";

	//서블릿파일의 멤버함수(기능)
	public String testFunc() {
		System.out.println("선언문 TEST");
		return "name : " + name;
	}%>


<%-- 	
	2.	표현식 :
		(SERVLET 파일(JAVA파일) 안의 값을 FRONTEND로 전달 표현할때 사용
		out.print()자동
		표현식<%=name %>을 EL방식으로 간단하게 표현할 수 있다. 
--%>


	NAME :
	<%=name%>
	<br /> testFunc() :
	<%=testFunc()%><br />



</body>
</html>
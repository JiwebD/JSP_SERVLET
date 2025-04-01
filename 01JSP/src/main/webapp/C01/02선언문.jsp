<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!
	int n = 0;
	public int countUp(){
		n++;
		return n;
	}
/* 	for(int i=0;i<10;i++){
		System.out.println("!");
	}		
	선언부에는 변수, 메서드만 입력 가능하다.
*/
%>

<%=countUp() %>
</body>
</html>
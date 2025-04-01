<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String msg1 = "HELLO 1";
	String msg2 = "HELLO 2";
	String msg3 = "HELLO 3";
	String msg4 = "HELLO 4";
	request.setAttribute("message","TEST!!");
	request.setAttribute("message2","TEST!!_2");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<script>
	/* 꼭 '' 적어 줘야함 안 적으면 에러 */
<%-- 	
	const message1 = '<%=msg1%>';
	const message2 = '<%=msg2%>';
	const message3 = '<%=msg3%>';
	const message4 = '<%=msg4%>';
	 --%>
	 
	/* EL사용 request.setAttribute()에 저장한 값 */
	const message1 = '${message}';		//EL
	const message2 = '${message2}';		//EL
	const message3 = `${message}`;		//`` 자바스크립트코드 보간법(JSP 의 message값을 리터럴 형태로 변환)
										// JPA작업할때 에러날 수 있기 때문에 ``사용 않하는게 좋음.
	
	console.log(message1);
	console.log(message2);
	console.log(message3);
//	console.log(message4);	
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JOIN PAGE</h1>
	<form action="${pageContext.request.contextPath}/join.do" method="post">
		이름<input name="username" /><br/>
		아이디<input name="userid" /><br/>
		패스워드<input name="password" /><br/>
		<button>회원가입</button>
	</form>

</body>
</html>
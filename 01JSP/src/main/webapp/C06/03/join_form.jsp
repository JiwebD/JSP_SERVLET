<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	div{
	display:block;
	}
</style>
<body>
	<h1>JOIN FORM</h1>
	<form action="${pageContext.request.contextPath}/C06/03/join.jsp">
	<div>
		<label>아이디 :</label><br/>
		<input type="text" name="username" />
	</div>
	<div>
		<label>패스워드 :</label><br/>
		<input type="text" name="password" />
	</div>
	<div>
		<button>회원가입</button>
	</div>
	</form>

</body>
</html>
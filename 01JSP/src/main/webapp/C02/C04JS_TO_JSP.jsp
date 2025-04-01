<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--isInit 플래그가 true일 때, <script>에서 자동으로 폼 값을 설정하고 submit() 함.
		폼이 다시 서버에 요청을 보내면서 flag=true와 사용자 정보 전달.
		서버는 flag 값을 보고 더 이상 자동 실행하지 않도록 isInit을 false로 바꿈.
		그 후 전달된 사용자 정보가 브라우저에 출력됨.
	 -->
	<%!
		boolean isInit=true;
	%>
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String flag = request.getParameter("flag");
		System.out.println("FLAG : " + flag);
		System.out.println("isInit = " + isInit);
		if(flag!=null && flag.equals("true")){
			isInit=false;
		}
			
	
	%>

	USERNAME :
	<%=username%><br> PASSWORD :
	<%=password%><br> ROLE :
	<%=role%><br>


	<form action="C04JStoJSP.jsp" name="myForm">
		<input name="username" type="hidden" /> 
		<input name="password" type="hidden" /> <input name="role" type="hidden" /> 
		<input name="flag" value="true" type="hidden" />
		
	</form>
	<script>
	//	폼 찾기
		const form = document.myForm;
		const flag = '<%=isInit%>';
		console.log("flag",flag);
		if(flag == 'true'){
			form.username.value = "홍길동";
			form.password.value = "1234";
			form.role.value = "ROLE_USER";
			form.submit();
		}
	</script>
	
</body>
</html>

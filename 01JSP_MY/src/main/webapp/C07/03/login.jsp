<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String idSave = request.getParameter("idSave");		// 체크하냐안하냐는 유효성 체크 필요없음
	String pwSave = request.getParameter("pwSave");		// 체크하냐안하냐는 유효성 체크 필요없음
	System.out.println("idSave : " + idSave);
	System.out.println("pwSave : " + pwSave);
	// 파라미터 유효성 체크
	if(username.isEmpty()){
		//out.println("<script>alert('username을 입력하세요');location.href='./login_form.jsp'</script>");
		request.setAttribute("username_msg","username을 입력하세요");
				
	}
	if(password.isEmpty()){
		//out.println("<script>alert('password을 입력하세요');location.href='./login_form.jsp'</script>");
		request.setAttribute("password_msg","password을 입력하세요");

	}	
	
	if(username.isEmpty()||password.isEmpty()){
		request.getRequestDispatcher("./login_form.jsp").forward(request,response);
		return ;
	}
	//01 ID 확인(DB 조회 - 생략)
	if(!username.equals("admin")){
		request.setAttribute("username_msg","해당 ID는 존재하지않습니다");
		request.getRequestDispatcher("./login_form.jsp").forward(request,response);
	}
	
	//02 PW 확인(일치여부 확인)
	if(!password.equals("1234")){
		request.setAttribute("password_msg","해당 패스워드가 일치하지 않습니다.");
		request.getRequestDispatcher("./login_form.jsp").forward(request,response);
	}
	//03 사용자 상태정보(인증됨)를 Session 저장
	session.setAttribute("isAuth", true);
	session.setAttribute("role", "ROLE_ADMIN");
	session.setMaxInactiveInterval(30);	//30초 (기본 1800초 : 30분)
	
	//쿠키 설정
	if(idSave!=null){
		Cookie cookieid = new Cookie("username",username);
		cookieid.setMaxAge(60*5);
		cookieid.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookieid);

		
		Cookie cookieidchk = new Cookie("idchk","on");
		cookieidchk.setMaxAge(60*5);
		cookieidchk.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookieidchk);

	}
	if(idSave==null){
		Cookie cookieid = new Cookie("username",null);
		cookieid.setMaxAge(0);
		cookieid.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookieid);	
		
		Cookie cookieidchk = new Cookie("idchk",null);
		cookieidchk.setMaxAge(0);
		cookieidchk.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookieidchk);	

	}
	if(pwSave!=null){
		Cookie cookiepw = new Cookie("password",password);
		cookiepw.setMaxAge(60*5);
		cookiepw.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookiepw);
			
		Cookie cookiepwchk = new Cookie("pwchk","on");
		cookiepwchk.setMaxAge(60*5);
		cookiepwchk.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookiepwchk);
	}	
	if(pwSave==null){
		Cookie cookiepw = new Cookie("password",null);
		cookiepw.setMaxAge(0);
		cookiepw.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookiepw);
		
		Cookie cookiepwchk = new Cookie("pwchk",null);
		cookiepwchk.setMaxAge(0);
		cookiepwchk.setPath("/01JSP_MY/C07/03/login_form.jsp");
		response.addCookie(cookiepwchk);
	}
	//04 로그인 처리후 MAIN PAGE REDIRECT
	out.println("<script> alert('로그인 성공! MAIN page로 이동합니다.');location.href='main.jsp'</script>");
%>
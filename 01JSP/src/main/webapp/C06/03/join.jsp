<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="C04.UserDto" %>

    <%
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	//파라미터 유효성 체크
    	if(username.isEmpty()){
    		out.println("<script>alert('ID를 입력하세요');location.href='./login_form.jsp'</script>");
    	}
    	if(password.isEmpty()){
    		out.println("<script>alert('PW를 입력하세요');location.href='./login_form.jsp'</script>");
    	}
    	
    	UserDto userDto = new UserDto(username,password,"ROLE_USER");
    	
    	//request 내장객체 userDto 추가
    	request.setAttribute("userDto", userDto);
    	

	
		request.setAttribute("isconfirm",true);
		
		//forwarding
		request.getRequestDispatcher("./dbUtils.jsp").forward(request,response);
		

    
    
    %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="C04.UserDto,C09.DBUtils,java.util.*" %>
<%
	String userid = request.getParameter("userid");

	int result = DBUtils.getInstance().deleteUser(userid);
	
	//잘 처리됐는지 메시지 출력

	
	//RE + FR
	response.sendRedirect("./selectAll.jsp");
%>


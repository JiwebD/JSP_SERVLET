<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!-- 파라미터 받기 --> 
<%@page import="Utils.*" %>   

<!-- 파라미터 받기(액션 태그) -: useBean + :setProperty -->
<jsp:useBean id="classDto" class="Utils.ClassDto" scope="request" />
<jsp:setProperty name="classDto" property="*" />

<%
	System.out.println(classDto);
  	int result = DBUtils.getInstance().insertClass(classDto);
 	
 	if(result>0){
 		String code = "<script>";
 		code +="alert('수강신청 완료!');";
 		code +="location.href='../index.jsp'";
 		code +="</script>";
 		
 		out.println(code);
 		
 		  out.println("<script>alert('수강신청이 정상적으로 완료되었습니다!');location.href='../index.jsp'</script>"); 
 	}
  	else{
 		String code = "<script>";
 		code +="alert('수강신청 실패!');";
 		code +="location.href='./index.jsp'";
 		code +="</script>";
 		
 		out.println(code);
 	}  
%>


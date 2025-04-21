<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FILE LIST</h1>
	<%@page import="java.util.*,java.io.*" %>
	<%
		Map<String,List<File>> map = request.getAttribute("map")!=null?
									(Map<String,List<File>>)request.getAttribute("map")
									:null;
	
	if(map!=null)
	{
		for(String folder : map.keySet()){
	%>
		<h3>폴더명 : <%=folder %></h3>
			
	<%		
			List<File> list = map.get(folder);
			for(File file : list)
			{
	%>
				<!-- 프로젝트할때에는 경로를 db로 넣어줘야한다. -->
				<a href="${pageContext.request.contextPath }/file/download?folder=<%=folder%>&filename=<%=file.getName()%>"><%=file.getName() %></a><br />			
	<%			
			}
			out.println("<hr />");
		}
	}
	%>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	java.util.Scanner sc = new java.util.Scanner(System.in);
    	System.out.print("N단 입력 : ");
    	int n = sc.nextInt();
    	System.out.printf("%d 단\n",n);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tbody>
			<% 
			for(int i=1;i<=9;i++)
			{
			%>
			<tr>

				<td>
					<%=i+" x "+n+" = "+(i*n) %>
				</td>
							
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>
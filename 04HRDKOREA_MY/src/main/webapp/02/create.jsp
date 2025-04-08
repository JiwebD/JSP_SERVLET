<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!-- 파라미터 받기 --> 
<%@page import="Utils.*" %>   
<%
	/* 문자셋 설정 */
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	/* 파라미터 받기 */
	String jumin = request.getParameter("v_jumin");
	String name = request.getParameter("v_name");
	String no = request.getParameter("m_no");
	String time = request.getParameter("v_time");
	String area = request.getParameter("v_area");
	String confirm = request.getParameter("v_confirm");
	
	/* 전달받은 파라미터로 VoteDto 객체 생성 */
	VoteDto voteDto = new VoteDto(jumin,name,no,time,area,confirm);
	System.out.println("voteDto : " + voteDto);
%>

<!-- 파라미터 받기(액션 태그) 보내는 파라미터, 받는파라미터, Dto변수명 모두 같고 Dto에 디폴트생성자,모든인자생성자 있어야 조건 성립 --> 
<!-- :useBean + :setProperty-->
<%-- 
<% VoteDto voteDto2 = new VoteDto(); %>
  == 
<jsp:useBean id="voteDto2" class="Utils.VoteDto" scope="request" />
 --%>
<jsp:useBean id="voteDto2" class="Utils.VoteDto" scope="request" />
 <!-- voteDto2라는 이름으로 Utils.VoteDto 클래스의 객체 성성 이미 존재하면 재사
	  scope="request" : request 범위에서만 유효
 -->
<jsp:setProperty name="voteDto2" property="*" />
 <!-- 클라이언트가 보낸 request 파라미터 중 이름이 
 	  voteDto2 객체의 멤버 변수 이름과 같은 것이 있으면 자동으로 그 값을 set
 	  
	  name="xxx" 형식의 파라미터가 있으면
	  Utils.VoteDto 안에 setXxx()라는 메서드가 있으면
	  자동으로 setXxx(파라미터값) 호출	  
 -->

<%
	System.out.println("voteDto2 : " + voteDto2);
	int result = DBUtils.getInstance().insertVote(voteDto2);
	
	if(result > 0){
		out.println("<script>alert('투표 성공!')</script>");
		request.getRequestDispatcher("./read.jsp").forward(request,response);
	}
	else{
		out.println("<script>alert('투표 실패! 다시 진행해 주세요');location.href='./index.jsp'</script>");
	}
%>


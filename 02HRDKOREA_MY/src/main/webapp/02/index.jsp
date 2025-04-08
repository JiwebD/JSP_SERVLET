<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	:root{}
	html{}
	*{	 box-sizing:border-box;}
	body{padding:0;margin : 0;}
	ul{list-style:none;margin:0;padding:0;}
	a{text-decoration:none; color:black;}
	.wrapper{}
	.wrapper>header{height:80px;}
	.wrapper>nav{height:50px;}
	.wrapper>main{ height :calc(100vh - 80px - 50px - 80px);}
	.wrapper>main h2{
		text-align:center;
		font-size:1.8rem;
		font-weight:400;
		
	}
 	.wrapper>main>form{
		border:1px solid;
		width:500px;
		min-height:250px;
		margin: 0 auto;
		padding : 0;
	}
 	.wrapper>main>form>div{
		border-bottom:1px solid;
		padding: 0;
	}
	.wrapper>main>form>div>label{
		display:block;
		width:100px;
		border-right: 1px solid;

		
	}
	
	.wrapper>main table{
		border:1px solid;
		border-collapse:collapse;
		min-width:500px;
		min-height:350px;
		margin: 0 auto;
	}
	.wrapper>main table th,
	.wrapper>main table td{
		min-width:80px !important;
		min-height:35px !important;
		border:1px solid;
		text-align:center;
	}
	.wrapper>main table th{
		background-color:lightgray;
	}
	.wrapper>footer{height:80px;}
	
</style>


</head>
<body>
	
	<!--  
		select M.M_NO,M.M_NAME,P.P_NAME,M.P_SCHOOL,M.M_JUMIN,M.M_CITY,P.P_TEL1,P.P_TEL2,P.P_TEL3
		from TBL_MEMBER_202005 M
		join TBL_PARTY_202005 P
		on M.P_CODE=P.P_CODE;
	-->
	<%@page import="Utils.*,java.util.*" %>
	<%
		List<MemberDto> list = DBUtils.getInstance().selectAllMember();
	%>
	<div class="wrapper">
		<!--  -->
		<%@include file="/layouts/Header.jsp" %>
		
		<!--  -->
		<%@include file="/layouts/Nav.jsp" %>
		
		<main>
			
			<h2>투표하기</h2>
			<form name="vote_form" action="./create.jsp" method="post" onsubmit="return false">
									<!-- onsubmit="return false" == submit기능을 끔 -->
				<div>
					<label>주민번호</label>	<input name="v_jumin"/>
				</div>
				<div>
					<label>성명</label>	<input name="v_name"/>
				</div>
				<div>
					<label>투표번호</label>	<input name="m_no"/>
				</div>
				<div>
					<label>투표시간</label>	<input name="v_time"/>
				</div>
				<div>
					<label>투표장소</label>	<input name="v_area"/>
				</div>
				<div>
					<label>유권자확인</label>
						<input type="radio"	name="v_confirm" value="Y"/> 확인
						&nbsp;&nbsp;	<!-- &nbsp : Non-breaking Space 줄바꿈없는 공백 -->
						<input type="radio"	name="v_confirm" value="N"/> 미확인
				</div>
				<div>
					<button type="submit" onclick="isValid()">투표하기</button>
					<button type="reset">다시하기</button>
				</div>
			</form>
	

				
				
			</table>
			
		</main>
		
		<!--  -->
		<%@include file="/layouts/Footer.jsp" %>
	
	</div>
	
	<script>
		function isValid(){
			//form요소 찾기
			var form = document.vote_form;
			//유효성검사
			//v_jumin
			if(form.v_jumin.value===""){
				alert("주민번호가 입력되지 않았습니다!");
				form.v_jumin.focus();
				return ;
			}
			//v_name
			
			//m_no
			//v_time
			//v_area
			//v_confirm
			if(form.v_confirm.value===""){
				alert("유권자 확인이 선택되지 않았습니다!");
				form.v_confirm.focus();
				return ;
			}
			//submit처리
			return form.submit();
		}
	</script>

</body>
</html>
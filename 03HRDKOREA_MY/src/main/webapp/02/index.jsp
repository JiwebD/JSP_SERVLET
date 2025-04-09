<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	:root{}
	html{}
	*{	 box-sizing:border-box;}
	body{padding:0;margin : 0;}
	ul{list-style:none;margin:0;padding:0;}
	a{text-decoration:none; color:black;}
	.wrapper{background-color: lightgray;}
	.wrapper>header{height:80px;}
	.wrapper>nav{height:50px;}
	.wrapper>main{ height :calc(100vh - 80px - 50px - 80px);}
	.wrapper>main h2{
		text-align:center;
		font-size:1.8rem;
		font-weight:400;
		
	}
	.wrapper>main table{
		border:1px solid;
		/* border-collapse:collapse; */
		width:550px;
/* 		min-height:350px; */
		margin: 0 auto;
		padding: 1px;
	}
	.wrapper>main table th,
	.wrapper>main table td{
		min-width:80px ;
		height:25px ;
		border:1px solid;
		text-align:center;
		padding: 0;
	}
	.wrapper>main table input,
	.wrapper>main table select{
		height: 100%;
		min-width: 200px;
	}
	
	.wrapper>main table th{
		text-align:left;
	}
	.wrapper>footer{height:80px;}
	
</style>
<body>
	<%@page import="Utils.*,java.util.*" %>
	<%
		//모든 회원정보 가져오기
		List<MemberDto> member_list = DBUtils.getInstance().selectAllMember();
	
		//모든 강의정보 가져오기
		List<TeacherDto> teacher_list = DBUtils.getInstance().selectAllTeacher();
	%>

	<div class="wrapper">
		<header>
		<!--  -->
		<%@include file="/layouts/Header.jsp" %>
		
		</header>
		
		<nav>
		<!--  -->
		<%@include file="/layouts/Nav.jsp" %>
		
		</nav>
		
		<main>
			<h2>수강신청</h2><br />
			<form action="./create.jsp" name="registForm" onsubmit="return false">
				<table>
					<tr>
						<td>수강월</td>
						<th><input name="regist_month" /> 예_2022년03월 → 202203</th>
					</tr>
					<tr>
						<td>회원명</td>
						<th>
							<select name="c_name">
								<option value="">회원명</option>
							<%
								for(MemberDto dto : member_list)
								{
							%>
								<option data-no=<%=dto.getC_no() %> value=<%=dto.getC_name()%>><%=dto.getC_name() %></option>
							<%
								}
							%>
							</select>
						</th>
					</tr>
					<tr>
						<td>회원번호</td>
						<th><input name="c_no" /></th>
					</tr>
					<tr>
						<td>강의장소</td>
						<th><input name="class_area" /></th>
					</tr>
					<tr>
						<td>강의명</td>
						<th>
							<select name="class_name">
								<option>강의신청</option>
								<%
									for(TeacherDto dto : teacher_list)
									{
								%>
									<option data-teacherCode=<%=dto.getTeacher_code() %> data-price=<%=dto.getClass_price() %> value="<%=dto.getClass_name()%>"><%=dto.getClass_name() %></option>
								<%
									}
								%>
							</select>
						</th>
						
					</tr>
					<tr>
						<td>수강료</td>
						<th>
						<input name="tuition" /> 원
						<input type="hidden" name="teacher_code" />
						</th>
					</tr>
					<tr>
						<td colspan=2>
							<button type="submit" onclick="isValid()">수강신청</button>
							<button onclick="resetFunc()">다시쓰기</button>
						</td>
					</tr>
				</table>
			</form>

		</main>
		
		<footer>
		<!--  -->
		<%@include file="/layouts/Footer.jsp" %>
		
		</footer>
	</div>
	
	<script>
		// name="registForm" 폼 객체 참조
		const form = document.registForm;
		// Select onchange Event 셀렉트 박스 변경 감지
		// "회원명" 셀렉트 박스 변경 이벤트 리스너 등록
		let cno;
		let price;	
		// "강의신청" 셀렉트 박스 변경 이벤트 리스너 등록
		form.c_name.addEventListener('change',function(e){
			const selectEl = e.target;
			const seletedOption = selectEl.options[selectEl.selectedIndex];
			cno = seletedOption.getAttribute("data-no");	
			console.log(e.target.value,cno);
			form.c_no.value=cno;
			
			if(price!=null || price!=undefined)
				{
					if(cno<20000){
						form.tuition.value=price;
					}
					else{
						form.tuition.value=(price/2);
					}
				}	
		});
		form.class_name.addEventListener('change',function(e){
			const selectEl = e.target;
			const seletedOption = selectEl.options[selectEl.selectedIndex];
			
			price = seletedOption.getAttribute("data-price");
			
			const teacherCode = seletedOption.getAttribute("data-teacherCode")
			form.teacher_code.value=teacherCode;
			console.log(e.target.value,price,teacherCode);
			
			if(cno<20000){
				form.tuition.value=price;
			}
			else{
				form.tuition.value=(price/2);
			}

		});
		
		function isValid(){
			if(form.regist_month.value===""){
				alert("수강월을 입력하세요 ex)202401")
				form.regist_month.focus();
				return ;
			}
			if(form.c_name.value===""){
				alert("회원명을 선택하세요")
				form.c_name.focus();
				return ;
			}
			if(form.c_no.value===""){
				alert("회원번호를 입력하세요 ex)10001")
				form.c_no.focus();
				return ;
			}
			if(form.class_area.value===""){
				alert("강의장소를 입력하세요")
				form.class_area.focus();
				return ;
			}
			if(form.class_name.value===""){
				alert("강의명을 선택하세요")
				form.class_name.focus();
				return ;
			}
			if(form.tuition.value===""){
				alert("수강료를 입력하세요")
				form.tuition.focus();
				return ;
			}
			else{
				form.submit();
			}
		}
		
		function resetFunc(){
			alert("다시쓰기 합니다!");
			form.reset();
		}
		
		
	</script>

</body>
</html>
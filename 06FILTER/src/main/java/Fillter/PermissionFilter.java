package Fillter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Type.Role;

//@WebFilter(urlPatterns = {"/admin_main","/manager_main","/user_main"}) //여러개 등록
public class PermissionFilter implements Filter{
	
	//키값을 사용해 등급 저장을 하기위한 저장 객체
	//URL : Permission Value(enum열거형클래스에 저장된 상수를 사용)
	private Map<String,Role> pageGradeMap = new HashMap();
	
	
	//필터가 처음 등록될 때 한 번 실행되는 초기설정
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// 현재 프로젝트의 URL 경로 가져오기.
		String projectPath = filterConfig.getServletContext().getContextPath();
		
		// /admin_main		필요한 권한값(3) - 권한값이 3이상 : ROLE_ADMIN
		// /manager_main	필요한 권한값(2) - 권한값이 2이상 : ROLE_MANAGER
		// /user_main		필요한 권한값(1) - 권한값이 1이상 :  ROLE_USER		
		// 열거형(enum) 으로 관리
		// enum : 클래스의 한 종류 (상수 집합 표현에 특화됨)
		// 1, 2, 3, 으로 관리하면 각 숫자가 뭘 의미하는지 해깔릴 수 있는데 열거형에 상수를 저장해놓고 꺼내쓰면 좋음
		
		// 각 URL에 권한 지정
		// ROLE_ADMIN 같은 형식으로 저장하면 나중에 접근 페이지마다 어떤 권한이 필요한지 조회하기 쉽다.
		pageGradeMap.put(projectPath+"/admin_main", Role.ROLE_ADMIN);
		pageGradeMap.put(projectPath+"/manager_main", Role.ROLE_MANAGER);
		pageGradeMap.put(projectPath+"/user_main", Role.ROLE_USER);
		

	}

	
	
	//클라이언트가 요청을 보낼 때마다 매번 실행되는 필터 핵심 로직
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest	request	= (HttpServletRequest)req;
		HttpServletResponse	response	= (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		
		//로그인 시 세션에 저장된 내 권한(role)을 꺼냄
		String myRole = (String)session.getAttribute("role");
		
		//role값이 없는 경우 /admin_main, /maneger_main, /user_main 에 접속 시도시 로그인창으로 이동
		//로그인하지 않은 사용자면 로그인 페이지로 강제 이동시킴
		if(myRole==null) {
			response.sendRedirect(request.getContextPath()+"/login.do?msg=not authenticated...");
			return ;
		}
		//문자열 "ROLE_USER" → enum Role.ROLE_USER 로 변환
		//나중에 ordinal()을 쓰기 위해 enum으로 바꾸는 작업 ordinal()은 enum에서 제공하는 매서드로,
		//enum 열거형 클래스에 저장된 값의 순서대로 0,1,2,3....으로 반환해준다.
		Role my = null;
		switch(myRole)
		{
			case "ROLE_USER":
				my = Role.ROLE_USER;		//1
				break;
			case "ROLE_MANAGER":
				my = Role.ROLE_MANAGER;		//2
				break;
			case "ROLE_ADMIN":
				my = Role.ROLE_ADMIN;		//3
				break;
			default :
				my = Role.ROLE_ANONYMOUS;	//0
				break;
		}
		
		//현재 요청한 URL의 요구 권한 확인
		
		//현재 접속하려는 URL을 가져옴
		//클라이언트가 요청한 전체URI반환 클라이언트가 /admin_main페이지 접속요청을하면 그 경로를 저장.
		String requestUri = request.getRequestURI(); 	//	/project/admin_main..
		//URL이 어떤 권한을 요구하는지 pageGradeMap에서 꺼냄
		// 위에 init에서 저장했던 Map(URL : 권한)에서
		// 키값(URL)에 클라이언트가 요청한 URL와 같은 키의 값(권한)을 꺼내서 Role pageRole에 저장
		Role pageRole = pageGradeMap.get(requestUri);	//
		
		System.out.printf("URL : %s, MyRole : %d, PageRole : %d\n", requestUri,my.ordinal(),pageRole.ordinal());
		
		//권한 비교 ordinal() 매서드를 사용해 enum Role에 저장된 값의 순서대로 정수 반환.
		//로그인한 아이디의 권한이 접근하려는 페이지권한보다 낮으면 예외처리.
		if(my.ordinal()<pageRole.ordinal()) {
			throw new ServletException("해당 권한으로는 접근이 불가능한 페이지입니다.");
		}
		
		
		System.out.println("[Filter perform Start]");
		
		//권한이 충분하면 다음 필터나 실제 서블릿으로 요청을 전달함
		chain.doFilter(req,resp);
		
		System.out.println("[Filter perform End]");
	}
	

}

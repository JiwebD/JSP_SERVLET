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
	//URL : Permission Value
	private Map<String,Role> pageGradeMap = new HashMap();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//프로젝트 경로정보 저장 객체
		String projectPath = filterConfig.getServletContext().getContextPath();
		
		// /admin_main		필요한 권한값(3) - 권한값이 3이상 : ROLE_ADMIN
		// /manager_main	필요한 권한값(2) - 권한값이 2이상 : ROLE_MANAGER
		// /user_main		필요한 권한값(1) - 권한값이 1이상 :  ROLE_USER		
		// 열거형(enum) 으로 관리
		// enum : 클래스의 한 종류 (상수 집합 표현에 특화됨)
		// 1, 2, 3, 으로 관리하면 각 숫자가 뭘 의미하는지 해깔릴 수 있는데 열거형에 상수를 저장해놓고 꺼내쓰면 좋음
		pageGradeMap.put(projectPath+"/admin_main", Role.ROLE_ADMIN);
		pageGradeMap.put(projectPath+"/manager_main", Role.ROLE_MANAGER);
		pageGradeMap.put(projectPath+"/user_main", Role.ROLE_USER);
		

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest	request	= (HttpServletRequest)req;
		HttpServletResponse	response	= (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		
		String myRole = (String)session.getAttribute("role");
		//role값이 없는 경우 /admin_main, /maneger_main, /user_main 에 접속 시도시 로그인창으로 이동
		if(myRole==null) {
			response.sendRedirect(request.getContextPath()+"/login.do?msg=not authenticated...");
			return ;
		}
		//
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
		
		//role값이 있는 경우
		//Page Role Value 꺼내기
		String requestUri = request.getRequestURI(); 	//	/project/admin_main..
		Role pageRole = pageGradeMap.get(requestUri);
		
		System.out.printf("URL : %s, MyRole : %d, PageRole : %d\n", requestUri,my.ordinal(),pageRole.ordinal());
		
		System.out.println("[Filter perform Start]");
		chain.doFilter(req,resp);
		System.out.println("[Filter perform End]");
	}
	

}

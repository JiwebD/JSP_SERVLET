package Fillter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Type.Role;

//@WebFilter("/login.do")
public class LoginRedirectFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		//request전
		//요청이 필터에 도달했을 때 콘솔에 로그 출력
		System.out.println("[Filter REDIRECT FILETER START...]");
		
		chain.doFilter(req, resp);

		//response후
		//서블릿에서 응답 처리가 끝난 후, 다시 필터로 되돌아왔을 때 출력됨
		System.out.println("[Filter REDIRECT FILETER END...]");
		//여기부터는 response 작업 후에 할 수 있는 일을 처리하는 공간
		
		// request, response, method 획득
		//ServletRequest는 HttpServletRequest로 다운캐스팅해야 메서드, 세션 등 사용가능.
		HttpServletRequest request = (HttpServletRequest) req;	
		HttpServletResponse response = (HttpServletResponse) resp;
		String method = request.getMethod();	//GET or POST

		HttpSession session = request.getSession();
		
		String role = (String) session.getAttribute("role");	//내장객체인 Attribute는 기본 Object형이기에 String형으로 변환

		if(method.contains("POST") && (role!=null))
		{
			System.out.println("[FILTER] 로그인된 상태 확인! role : " + role);
			switch(role)
			{
			case "ROLE_USER":
				response.sendRedirect(request.getContextPath()+"/user_main");
				//break;
				return ;
			case "ROLE_MANAGER":
				response.sendRedirect(request.getContextPath()+"/manager_main");
				//break;
				return ;
			case "ROLE_ADMIN":
				response.sendRedirect(request.getContextPath()+"/admin_main");
				//break;
				return ;
			default :
				response.sendRedirect(request.getContextPath()+"/main.do");
				//break;
				return ;
			}
		}
		
		

		


	}

	// FILTER URL : login.do
	// 로그인 성공시 redirect 경로를 role별로 해줍니다.
	// Role_ADMIN -> /admin_main 으로 리다이렉트
	// Role_MANAGER -> /manager_main 으로 리다이렉트
	// Role_USER -> /user_main 으로 리다이렉트

}

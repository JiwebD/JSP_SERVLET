package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//http://localhost:8090/04SERVLET_INIT/TEST_01
@WebServlet("/TEST_01") //어노테이션 기반 맵핑처리
public class C01Servlet_Test extends HttpServlet{
	//Override 메소드 받기
	@Override
	public void init() throws ServletException {
		System.out.println("INIT() invoke...");
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("SERVICE() invoke...");
	}

	@Override
	public void destroy() {	//파일이 수정되면 실행
		System.out.println("DESTROY() invoke...");
	}
}

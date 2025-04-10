package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


//web.xml에 명시적으로 매핑 설정함 (어노테이션 없음)

// servlet 등록
// webapp/WEB-INF/lib에 web.xml 에 
// <servlet>
// 	<servlet-name>C02Servlet</servlet-name>   //이름등록
// 	<servlet-class>Servlet.C02Servlet_Test</servlet-class>    //사용할 서블릿 클래스경로
// </servlet>
// <servlet-mapping>		//서블릿 맵핑
// 	<servlet-name>C02Servlet</servlet-name>	//맵핑할 서블릿 이름
// 	<url-pattern>/TEST_02</url-pattern>		//URL 지정
// </servlet-mapping>
public class C02Servlet_Test extends HttpServlet{
	//- init() 서블릿 객체가 생성될 때 한 번만 실행
	// 		   주로 초기 설정, 리소스 준비 등 초기화 코드 작성
	@Override
	public void init() throws ServletException {
		System.out.println("INIT() invoke...");
	}
	// service() 클라이언트가 요청할 때마다 실행됨
	//			 
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("SERVICE() invoke...");
	}
	// destroy()
	// 서버 종료나 서블릿 재배포 시 호출
	// 사용한 자원 반납 등 마무리 작업용
	@Override
	public void destroy() {	//파일이 수정되면 실행
		System.out.println("DESTROY() invoke...");
	}


}

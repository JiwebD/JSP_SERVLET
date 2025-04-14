package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.user.UserCreateController;

//모든요청에 실행되도록 "/"매핑
@WebServlet("/")
public class FrontController extends HttpServlet{
	//서브컨트롤러 저장 자료구조("/endPoint" : 서브컨트롤러객체)
	private Map<String,SubController> map = new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		try {
			// 기본경로
			map.put("/", new HomeController());
			map.put("/index.do", new HomeController());
			
			//인증작업(/user/*) - 회원CRUD , 로그인 , 로그아웃
			map.put("/user/create", new UserCreateController());
			
			//UserCreateController에서 던진 예외 처리
		System.out.println("INIT SIZE : " + map.size());
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException("서브컨트롤러 등록오류");
		}
		
		//도서(/book/*) - 도서CRUD
	}

	//모든 요청을 받아서 SubController로 보내기위해  service매서드사용
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		System.out.println("[FC] Service...");
		String endPoint = req.getServletPath();
		System.out.println("[FC] endpoint ..." + endPoint);
		SubController controller = map.get(endPoint);	//요청사항을 처리할 SubController
		controller.execute(req,resp);
		
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandler(e,req);
			req.getRequestDispatcher("/WEB-INF/View/error.jsp").forward(req, resp);
		}
	}

	// 예외처리함수
	public void exceptionHandler(Exception e,HttpServletRequest req) {

		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);

	}
	
	
}

package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/session/remove", "/session/attr/add", "/session/attr/replace", "/session/attr/remove" })
public class C03ListnerTest extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[SERVLET] C03ListenerTest service");
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		

		// 세션제거
		if (uri.contains("/session/remove")) {
			session.invalidate();
		}
		// 세션속성추가
		else if (uri.contains("/session/attr/add")) {
			session.setAttribute("S_KEY", "S_VAL");
		}
		// 세션속성수정
		else if (uri.contains("/session/attr/replace")) {
			session.setAttribute("S_KEY", "S_VAL_2");
		}
		// 세션속성삭제
		else if (uri.contains("/session/attr/remove")) {
			session.removeAttribute("S_KEY");
		}

	}
}

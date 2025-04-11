package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/app/add","/app/replace","/app/remove"})
public class C02ListenerTest extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[SERVLET] C02ListenerTest service");
		String uri = req.getRequestURI();
		
		//Attribute추가
		if(uri.contains("/app/add")) {
			req.getServletContext().setAttribute("CTX_KEY", "CTX_VALUE");
		}
		//Attribute수정
		else if(uri.contains("/app/replace")) {
			req.getServletContext().setAttribute("CTX_KEY", "CTX_VALUE_2");
			
		}
		//Attribute삭제
		else {
			req.getServletContext().removeAttribute("CTX_KEY");
			
		}
	}

}

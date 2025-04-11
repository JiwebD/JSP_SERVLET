package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.MysqlDbUtils;
import Utils.UserDto;

@WebServlet("/join.do")
public class Join extends HttpServlet{

	private MysqlDbUtils dbutils;
	
	@Override
	public void init() throws ServletException {
		
		try {
			dbutils = MysqlDbUtils.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//GET - 	/join.do - /WEB-INF/user/join.jsp 연결
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET /join.do...");
		req.getRequestDispatcher("/WEB-INF/user/join.jsp").forward(req,  resp);
	}

	//POST - /join.do - 회원가입처리(username,password 받아 DBUtils를 이용한 DB INSERT)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST /join.do");
		//파라미터
		String username = req.getParameter("username");
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		
		//유효성
		
		//처리작업
		UserDto userdto = new UserDto(username,userid,password,"ROLE_USER");
		
		int result = 0;
		try {
			result = dbutils.getInstance().insert(userdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		if(result>0)
			resp.sendRedirect(req.getContextPath()+"/login.do");
		else
			req.getRequestDispatcher("/WEB-INF/user/join.jsp").forward(req, resp);
		
	}
	
	
	
	
	
	//테이블 : tbl_user(요청 파라미터에 맞게 적절히 생성)
	//성공시 - /login.do 로 리다이렉트
	//실패시 - /join.do 로 포워딩
	
	
}

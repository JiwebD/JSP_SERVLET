package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.UserDto;
import Domain.Service.BookServiceImpl;

public class BookDeleteController implements SubController {
	private HttpServletRequest req;
	private HttpServletResponse resp;

	private BookServiceImpl bookService;

	public BookDeleteController() throws Exception {
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookDeleteController execute..");

		try {
			// 요청받은 페이지의 methode를 가져와서 문자형 uri 변수에 저장
			String uri = req.getMethod();
			// Methode가 GET이면 해당하는 페이지 매핑, 포워딩
			if (uri.equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/book/delete.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
			} catch (Exception e2) {
			}

		}

	}

	private boolean isValid(UserDto userDto) {

		return true;
	}

	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}
}

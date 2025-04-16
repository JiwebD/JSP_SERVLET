package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.UserDto;
import Domain.Service.BookServiceImpl;

public class BookUpdateController implements SubController {

	private HttpServletRequest req;
	private HttpServletResponse resp;

	private BookServiceImpl bookService;

	public BookUpdateController() throws Exception {
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookUpdateController execute..");

		try {

			String uri = req.getMethod();
			if (uri.equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/book/update.jsp").forward(req, resp);
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

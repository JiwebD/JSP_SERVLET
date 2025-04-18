package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.BookDto;
import Domain.Dto.UserDto;
import Domain.Service.BookServiceImpl;

public class BookCreateController implements SubController {

	private HttpServletRequest req;
	private HttpServletResponse resp;

	private BookServiceImpl bookService;

	public BookCreateController() throws Exception {
		this.bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookCreateController execute..");

		try {
			// 요청받은 페이지의 methode를 가져와서 문자형 uri 변수에 저장
			String uri = req.getMethod();
			System.out.println(uri);
			// Method가 GET이면 해당하는 페이지 매핑, 포워딩
			if (uri.equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/book/create.jsp").forward(req, resp);
				return;
			}
			
			//post
			//파라미터
			String bookCode = req.getParameter("bookCode");
			String bookName = req.getParameter("bookName");
			String publisher = req.getParameter("publisher");
			String isbn = req.getParameter("isbn");
			
			BookDto bookDto = new BookDto(bookCode,bookName,publisher,isbn);
			
			//유효성
			if(!isValid(bookDto)){
				req.getRequestDispatcher("/WEB-INF/view/book/create.jsp").forward(req, resp);
				return ;
			}
			//서비스
			
			boolean isadded = bookService.bookRegistration(bookDto);
			//뷰
			if(isadded) {
				resp.sendRedirect(req.getContextPath() + "/book/list");
			}else {
				req.getRequestDispatcher("/WEB-INF/view/book/create.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
			} catch (Exception e2) {
			}

		}

	}

	private boolean isValid(BookDto bookDto) {
		if(bookDto.getBookCode().isEmpty()) {
			req.setAttribute("bookCode", "BookCode를 입력하세요");
		}
		if(bookDto.getBookName().isEmpty()) {
			req.setAttribute("bookName", "bookName를 입력하세요");
		}
		if(bookDto.getPublisher().isEmpty()) {
			req.setAttribute("publisher", "출판사명을 입력하세요");
		}
		if(bookDto.getIsbn().isEmpty()) {
			req.setAttribute("isbn", "isbn을 입력하세요");
		}
		
		if(
				bookDto.getBookCode().isEmpty() ||
				bookDto.getBookName().isEmpty() ||
				bookDto.getPublisher().isEmpty() ||
				bookDto.getIsbn().isEmpty() 
				) {
			return false;
		}
			
		return true;
	}

	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}
}

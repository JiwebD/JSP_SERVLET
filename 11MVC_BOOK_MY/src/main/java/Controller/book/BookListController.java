package Controller.book;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.PageDto;
import Domain.Dto.UserDto;
import Domain.Service.BookServiceImpl;

public class BookListController implements SubController {

	// 게시판형식으로 도서정보 조회할 수 있는 페이지 로 페이징처리도 함. 리스트중 하나 클릭하면 BookRead로 넘어감

	private HttpServletRequest req;
	private HttpServletResponse resp;

	private BookServiceImpl bookService;

	public BookListController() throws Exception {
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookListController execute..");

		try {

			//list는 get처리만 있기때문에 get methode받아서 처리하는 작업 필요 없음. 
			
			//파라미터
			String pageno = req.getParameter("pageno");
			String amount = req.getParameter("amount");
			String type = req.getParameter("typer");
			String keyword = req.getParameter("keyword");
			
			Criteria criteria = null;
			if(pageno==null) {
				criteria = new Criteria();	//최초 접속했을때 - pageno=1, amount=10, type=null,keyword=null
			}else {
				
			}
			
			//입력값검증
			
			//서비스
			Map<String,Object> serviceResponse = bookService.getAllBooks(criteria);
			Boolean status = (Boolean) serviceResponse.get("status");
			PageDto pageDto = (PageDto)serviceResponse.get("pageDto");
			
			
			//뷰
			if(status) {
				List<BookDto> list = (List<BookDto>) serviceResponse.get("list");
				req.setAttribute("list", list);
				req.setAttribute("pageDto", pageDto);
			}else {
				;
			}
			req.getRequestDispatcher("/WEB-INF/view/book/list.jsp").forward(req, resp);
			
			

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

package Domain.Dto;

public class Criteria {
	private int pageno; 	//현재 페이지
	private int amount;		//페이지 당 보여줄 게시물 건수
	private String type;	//타입(도서명 , 도서코드 , 출판사)
	private String keyword;	//키워드
	
	public Criteria() {
		//처음페이지로 들어왔을떄 기본값
		this.pageno = 1;
		this.amount = 10;
	}
	
	//타입, 키워드 선택하고 페이지 이동할려 할때
	public Criteria(String pageno, int amount, String type, String keyword) {
		super();
		this.pageno = Integer.parseInt(pageno);
		this.amount = amount;
		this.type = type;
		this.keyword = keyword;
	}
	
	// 타입, 키워드 선택안하고 그냥 페이지 이동할려할때
	public Criteria(String pageno, int amount) {
		this.pageno = Integer.parseInt(pageno) ;
		this.amount = amount ;
	}

	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Criteria [pageno=" + pageno + ", amount=" + amount + ", type=" + type + ", keyword=" + keyword + "]";
	}

	//toString
	//getter and setter
	//생성자(디폴트,모든인자)
	
	
}

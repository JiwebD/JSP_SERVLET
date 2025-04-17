package Domain.Dto;

public class PageDto {
	private static final long serialVersionUID = 5L;

	//
	private long totalCount;
	//페이지정보(전체페이지,현재페이지)
	private int totalpage;			//총게시물건수 / amount
	private Criteria criteria;		//현재페이지,한페이지당 읽을 게시물의 건수가 저장되어있음
	
	//블럭정보
	private int pagePerBlock;		//블럭에 표시할 페이지개수(15건 지정)
	private int totalBlock;			//totalpage / pagePerBlock
	private int nowBlock;			//현재페이지번호 /pagePerBlock
	
	//표시할 페이지(블럭에표시할 시작페이지-마지막페이지)
	private int startPage;
	private int endPage;
	
	//NextPrev 버튼 표시여부
	private boolean prev,next;
	
	
	public PageDto() {}
	
	public PageDto(long totalcount,Criteria criteria) {
		
		this.setTotalCount(totalcount);
		this.criteria = criteria;
		
		//전체페이지 계산  Math.ceil올림처리해는 이유 페이지 수는 정수만가능.
		totalpage =(int)Math.ceil((1.0*totalcount)/criteria.getAmount());
		
		//블럭계산
		pagePerBlock=15;	//페이지네이션에 몇개의 페이지 숫자버튼 표시할건지줄껀지 
		totalBlock = (int)Math.ceil( (1.0*totalpage) / pagePerBlock );	//전체 100페이지 있으면 15개로 나누면 6.x인데 올림처리해서 7로 정해줌
		nowBlock =  (int)Math.ceil ((1.0*criteria.getPageno()) / pagePerBlock);	//
		
		//Next,Prev 버튼 활성화 유무
		prev=nowBlock>1;
		next=nowBlock<totalBlock;
		
		//블럭에 표시할 페이지 번호 계산
		//1~6번째 블록일땐 각 블록 마지막 블록숫자가 끝 페이지 번호가 되고, 7번째 블록이면 마지막 페이지숫자가 105가아니라 totalpage(ex100)이되도록삼항연산자
		this.endPage = (nowBlock * pagePerBlock<totalpage) ? nowBlock * pagePerBlock : totalpage ;	
		
		this.startPage=nowBlock * pagePerBlock -pagePerBlock + 1;//블록 시작페이지 번호 계산
		 
		
	}
	

	@Override
	public String toString() {
		return "PageDto [totalCount=" + totalCount + ", totalpage=" + totalpage + ", criteria=" + criteria
				+ ", pagePerBlock=" + pagePerBlock + ", totalBlock=" + totalBlock + ", nowBlock=" + nowBlock
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + "]";
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public long getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
}

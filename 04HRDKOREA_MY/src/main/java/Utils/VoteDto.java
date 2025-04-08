package Utils;



public class VoteDto {
	//create.jsp의 받는 파라미터 값광 동일한 명으로 변수생성
	
	private String v_jumin;
	private String v_name;
	private String m_no;
	private String v_time;
	private String v_area;
	private String v_confirm;

	//생성자(디폴트 , 모든인자)
	public VoteDto() {}
	public VoteDto(String v_jumin, String v_name, String m_no, String v_time, String v_area, String v_confirm) {
		super();
		this.v_jumin = v_jumin;
		this.v_name = v_name;
		this.m_no = m_no;
		this.v_time = v_time;
		this.v_area = v_area;
		this.v_confirm = v_confirm;
	}
	
	//getter and setter
	public String getV_jumin() {
		return v_jumin;
	}
	public void setV_jumin(String v_jumin) {
		this.v_jumin = v_jumin;
	}
	public String getV_name() {
		return v_name;
	}
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}
	public String getM_no() {
		return m_no;
	}
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getV_time() {
		return v_time;
	}
	public void setV_time(String v_time) {
		this.v_time = v_time;
	}
	public String getV_area() {
		return v_area;
	}
	public void setV_area(String v_area) {
		this.v_area = v_area;
	}
	public String getV_confirm() {
		return v_confirm;
	}
	public void setV_confirm(String v_confirm) {
		this.v_confirm = v_confirm;
	}
	
	//to String
	@Override
	public String toString() {
		return "VoteDto [v_jumin=" + v_jumin + ", v_name=" + v_name + ", m_no=" + m_no + ", v_time=" + v_time
				+ ", v_area=" + v_area + ", v_confirm=" + v_confirm + "]";
	}
	
	
	
}

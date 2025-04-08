package Utils;

public class JoinDto {
	private String m_no;
	private String m_name;
	private String count;
	
	public JoinDto() {}
	public JoinDto(String m_no, String m_name, String count) {
		super();
		this.m_no = m_no;
		this.m_name = m_name;
		this.count = count;
	}
	public String getM_no() {
		return m_no;
	}
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "joinDto [m_no=" + m_no + ", m_name=" + m_name + ", count=" + count + "]";
	}
	
	

}

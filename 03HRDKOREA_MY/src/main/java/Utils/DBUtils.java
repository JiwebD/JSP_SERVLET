package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DBUtils {


	//
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "system";
	private String pw = "1234";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//싱글톤 패턴 처리
	private static DBUtils instance;
	private DBUtils() throws Exception{	//생성자
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
	}
	public static DBUtils getInstance() throws Exception{
		if(instance==null)
			instance = new DBUtils();
		return instance;
	}
	
	public List<MemberDto> selectAllMember() throws Exception{
		
		//sqlquery
//		select M.M_NO,M.M_NAME,P.P_NAME,M.P_SCHOOL,M.M_JUMIN,M.M_CITY,P.P_TEL1,P.P_TEL2,P.P_TEL3
//		from TBL_MEMBER_202005 M
//		join TBL_PARTY_202005 P
//		on M.P_CODE=P.P_CODE;

		String sql = "select M.M_NO,M.M_NAME,P.P_NAME,M.P_SCHOOL,M.M_JUMIN,M.M_CITY,P.P_TEL1,P.P_TEL2,P.P_TEL3"
				+ " from TBL_MEMBER_202005 M"
				+ " join TBL_PARTY_202005 P"
				+ " on M.P_CODE=P.P_CODE";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		List<MemberDto> list = new ArrayList();
		MemberDto dto = null;
		
		if(rs!=null) {
			while(rs.next()) {
				dto = new MemberDto();
				dto.setM_no(rs.getString(1));
				dto.setM_name(rs.getString(2));
				dto.setP_name(rs.getString(3));
				dto.setP_school(rs.getString(4));
				dto.setM_jumin(rs.getString(5));
				dto.setM_city(rs.getString(6));
				dto.setP_tel1(rs.getString(7));
				dto.setP_tel2(rs.getString(8));
				dto.setP_tel3(rs.getString(9));
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
	
	public int insertVote(VoteDto dto) throws Exception {
		
		String sql = "insert into TBL_VOTE_202005 values(?,?,?,?,?,?)";
		
		// 쿼리문 실행 준비 쿼리문 넣기
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getV_jumin());
		pstmt.setString(2, dto.getV_name());
		pstmt.setString(3, dto.getM_no());
		pstmt.setString(4, dto.getV_time());
		pstmt.setString(5, dto.getV_area());
		pstmt.setString(6, dto.getV_confirm());
		
		//쿼리문이 실행(pstmt.executeUpdate())되고 적용되는 행의 개수를 받는 변수 지정
		int result = pstmt.executeUpdate();
		
		
		pstmt.close();
		return result;
		
	}
	
	public List<VoteDto> selectAllVote() throws Exception{
		
		//sqlquery
//		select M.M_NO,M.M_NAME,P.P_NAME,M.P_SCHOOL,M.M_JUMIN,M.M_CITY,P.P_TEL1,P.P_TEL2,P.P_TEL3
//		from TBL_MEMBER_202005 M
//		join TBL_PARTY_202005 P
//		on M.P_CODE=P.P_CODE;

		String sql = "select * from TBL_VOTE_202005";

		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		List<VoteDto> list = new ArrayList();
		VoteDto dto = null;

		if(rs!=null) {
			while(rs.next()) {
				dto = new VoteDto();
				dto.setV_jumin(rs.getString(1));
				dto.setV_name(rs.getString(2));
				dto.setM_no(rs.getString(3));
				dto.setV_time(rs.getString(4));
				dto.setV_area(rs.getString(5));
				dto.setV_confirm(rs.getString(6));
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
	
	public List<JoinDto> selectAllcount() throws Exception{
		
		//sqlquery
//			select m.m_no, m.m_name,count(*)
//			from tbl_member_202005 m
//			join tbl_vote_202005 v
//			on m.m_no=v.m_no
//        	group by m.m_no, m.m_name
//			order by count(*) desc;
		
		String sql = "select m.m_no, m.m_name,count(m.m_no)"
				+ " from tbl_member_202005 m"
				+ " join tbl_vote_202005 v"
				+ " on m.m_no=v.m_no"
				+ " group by m.m_no, m.m_name"
				+ " order by count(*) desc";

		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		List<JoinDto> list = new ArrayList();
		JoinDto dto = null;

		if(rs!=null) {
			while(rs.next()) {
				dto = new JoinDto();
				dto.setM_no(rs.getString(1));
				dto.setM_name(rs.getString(2));
				dto.setCount(rs.getString(3));
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		return list;
	}

	public List<TeacherDto> selectAllTeacher() throws Exception{
		
		String sql = "select * from TBL_TEACHER_202201";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<TeacherDto> list = new ArrayList();
		
		TeacherDto dto = null;
		
		if(rs!=null) {
			while(rs.next()) {
				dto = new TeacherDto();
				dto.setTeacher_code(rs.getString(1));
				dto.setTeacher_name(rs.getString(2));
				dto.setClass_name(rs.getString(3));
				dto.setClass_price(rs.getString(4));
				dto.setTeacher_regist_date(rs.getString(5));
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
}

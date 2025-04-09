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
	
	public List<MemberDto> selectAllMember() throws Exception {
		//쿼리문 받는 문자열 변수 생성
		String sql = "select * from TBL_MEMBER_202201";
		//쿼리문 실행 준비
		pstmt = conn.prepareStatement(sql);
		//쿼리문 실행
		rs = pstmt.executeQuery();
		
		//select실행 행 받을 list생성
		List<MemberDto> list = new ArrayList();
		
		//반복해서 list배열에 추가할 객체 생성 기본 null값
		MemberDto dto = null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new MemberDto();
				dto.setC_no(rs.getString(1));
				dto.setC_name(rs.getString(2));
				dto.setPhone(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setGrade(rs.getString(5));
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
	
	public List<ClassDto> selectAllClass() throws Exception{
		
		//쿼리문 받는 문자열 변수 생성
		String sql = "select * from TBL_CLASS_202201";
		//쿼리문 실행 준비
		pstmt = conn.prepareStatement(sql);
		//쿼리문 실행
		rs = pstmt.executeQuery();
		
		//select실행 행 받을 list생성
		List<ClassDto> list = new ArrayList();
		
		//반복해서 list배열에 추가할 객체 생성 기본 null값
		ClassDto dto = null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new ClassDto();
				dto.setRegist_month(rs.getString(1));
				dto.setC_no(rs.getString(2));
				dto.setClass_area(rs.getString(3));
				dto.setTuition(rs.getString(4));
				dto.setTeacher_code(rs.getString(5));
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		return list;
	}
	
	public int insertClass(ClassDto classDto) throws Exception{
		
		pstmt = conn.prepareStatement("insert into TBL_CLASS_202201 values(?,?,?,?,?)");
		pstmt.setString(1, classDto.getRegist_month());
		pstmt.setString(2, classDto.getC_no());
		pstmt.setString(3, classDto.getClass_area());
		pstmt.setString(4, classDto.getTuition());
		pstmt.setString(5, classDto.getTeacher_code());
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	public List<ClassJoinMemberTeacher> selectAllClassMember() throws Exception{
//		select c.regist_month, m.c_no, m.c_name, t.class_name, c.class_area, c.tuition, m.grade
//		from TBL_MEMBER_202201 m
//		join TBL_CLASS_202201 c
//		on m.c_no=c.c_no
//	    join TBL_TEACHER_202201 t
//	    on c.teacher_code = t.teacher_code;
		
		String sql = "select c.regist_month, m.c_no, m.c_name, t.class_name, c.class_area, c.tuition, m.grade"
				+ " from TBL_MEMBER_202201 m"
				+ " join TBL_CLASS_202201 c"
				+ " on m.c_no=c.c_no"
				+ " join TBL_TEACHER_202201 t"
				+ " on c.teacher_code = t.teacher_code";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<ClassJoinMemberTeacher> list = new ArrayList();
		ClassJoinMemberTeacher dto = null;
		if(rs!=null)
			while(rs.next()) {
				dto = new ClassJoinMemberTeacher();
				dto.setRegist_month(rs.getString(1));
				dto.setC_no(rs.getString(2));
				dto.setC_name(rs.getString(3));
				dto.setClass_name(rs.getString(4));
				dto.setClass_area(rs.getString(5));
				dto.setTuition(rs.getString(6));
				dto.setGrade(rs.getString(7));
				list.add(dto);
			}
		rs.close();
		pstmt.close();
		return list;
	}
	
	public List<ClassJoinTeacher> selectAllClassTeacher() throws Exception{
//		select t.teacher_code,t.class_name,t.teacher_name,sum(c.tuition) 
//		from TBL_CLASS_202201 c
//		join TBL_TEACHER_202201 t
//		on c.teacher_code=t.teacher_code
//		group by t.teacher_code, t.class_name, t.teacher_name
//		order BY sum(c.tuition)desc;
		
		String sql = "select t.teacher_code,t.class_name,t.teacher_name,sum(c.tuition) "
				+ " from TBL_CLASS_202201 c"
				+ " join TBL_TEACHER_202201 t"
				+ " on c.teacher_code=t.teacher_code"
				+ " group by t.teacher_code, t.class_name, t.teacher_name"
				+ " order BY sum(c.tuition)desc";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<ClassJoinTeacher> list = new ArrayList();
		ClassJoinTeacher dto = null;
		if(rs!=null)
			while(rs.next()) {
				dto = new ClassJoinTeacher();
				dto.setTeacher_code(rs.getString(1));
				dto.setClass_name(rs.getString(2));
				dto.setTeacher_name(rs.getString(3));
				dto.setTuition(rs.getString(4));
				list.add(dto);
			}
		rs.close();
		pstmt.close();
		return list;
	}

}

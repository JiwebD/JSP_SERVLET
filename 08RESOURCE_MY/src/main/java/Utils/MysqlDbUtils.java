package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MysqlDbUtils {
	
	private String url = "jdbc:mysql://localhost/testDB";
	private String id = "root";
	private String pw = "1234";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	
	

	//이 클래스 내부에서 사용할 데이터베이스 커넥션 풀 객체를 저장할 변수 선언
	//JNDI를 통해 lookup("jdbc/MysqlDB")로 찾아온 자원을 여기에 할당하게 됨.
	private DataSource dataSource;
	
	//싱글톤 
	private static MysqlDbUtils instance;
	private MysqlDbUtils() throws Exception {
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		conn = DriverManager.getConnection(url, id, pw);
		
		
		//JNDI의 시작점이 되는 객체를 생성함
		//context.xml에 등록된 리소스를 찾기 위한 준비 단계
		Context initContext = new InitialContext();
		//Tomcat에서 웹 애플리케이션이 사용할 수 있도록 설정된 환경(Context) 정보를 가져옴
		//java:/comp/env는 톰캣의 표준 JNDI 기본 경로
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		//context.xml에 등록된 "jdbc/MysqlDB" 이름의 DataSource 객체를 찾아옴
		//이 이름은 context.xml 안의 <Resource name="jdbc/MysqlDB" ... /> 와 일치해야 함
		dataSource = (DataSource)envContext.lookup("jdbc/MysqlDB");	//
		conn = dataSource.getConnection();
		
	}
	public static MysqlDbUtils getInstance() throws Exception {
		if(instance==null)
			instance = new MysqlDbUtils();
		return instance;
	}
	
	public int insert(UserDto userdto) throws Exception {
		String sql = "insert into tbl_user values(?,?,?,?)";	
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userdto.getUsername());
		pstmt.setString(2, userdto.getUserid());
		pstmt.setString(3, userdto.getPassword());
		pstmt.setString(4, userdto.getRole());
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	public UserDto selectOne(String userid) throws Exception {
		pstmt = conn.prepareStatement("select * from tbl_user where userid=?");
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		
		
		UserDto userDto = null;
		if(rs!=null) {
			rs.next();
			userDto = new UserDto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}
		
		return userDto;
	}
	
	

}








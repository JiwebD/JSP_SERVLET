package C09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import C04.UserDto;

public class DBUtils {
/* 
 	1. 
 */
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
	
	//3-4
	public int insertUser(UserDto userDto) throws Exception {
		pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?)");
		pstmt.setString(1, userDto.getUserid());
		pstmt.setString(1, userDto.getPassword());
		pstmt.setString(1, userDto.getRole());
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		return result;
	}

}

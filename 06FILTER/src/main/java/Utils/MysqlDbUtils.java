package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlDbUtils {
	
	private String url = "jdbc:mysql://localhost/testDB";
	private String id = "root";
	private String pw = "1234";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	
	
	//싱글톤 
	private static MysqlDbUtils instance;
	private MysqlDbUtils() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
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
		pstmt = conn.prepareStatement("select * from tbl_user where username=?");
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		
		
		UserDto userDto = null;
		if(rs!=null) {
			rs.next();
			userDto = new UserDto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}
		
		rs.close();
		pstmt.close();
		return userDto;
	}
	
	

}








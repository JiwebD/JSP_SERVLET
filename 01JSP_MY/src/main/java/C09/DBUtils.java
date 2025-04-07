package C09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	//c
	public int insertUser(UserDto userDto) throws Exception {
		pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?)");
		pstmt.setString(1, userDto.getUserid());
		pstmt.setString(2, userDto.getPassword());
		pstmt.setString(3, userDto.getRole());
		int result = pstmt.executeUpdate();
		
		conn.commit();
		pstmt.close();
		return result;
	}
	//u
	public int updateUser(UserDto userDto) throws Exception{
		pstmt = conn.prepareStatement("update tbl_user set password=?,role=? where userid=?");
		pstmt.setString(3, userDto.getUserid());
		pstmt.setString(1, userDto.getPassword());
		pstmt.setString(2, userDto.getRole());
		int result = pstmt.executeUpdate();
		
		conn.commit();
		pstmt.close();
		return result;
	}
	//r
	public List<UserDto> selectAllUser() throws Exception{
		pstmt = conn.prepareStatement("select * from tbl_user");
		rs = pstmt.executeQuery();
		
		List<UserDto> list = new ArrayList();	//리턴값 저장용 새로운 객체
		UserDto userDto = null;	//
		if(rs!=null) {
			while(rs.next()) {
				userDto = new UserDto();
				userDto.setUserid(rs.getString("userid"));
				userDto.setPassword(rs.getString("password"));
				userDto.setRole(rs.getString("role"));
				list.add(userDto);
			}
			
		}
		rs.close();
		pstmt.close();
		return list;
		
	}
	

	
	public UserDto selectOneUser(String userid) throws Exception{
		pstmt = conn.prepareStatement("select * from tbl_user where userid=?");
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		UserDto userDto = null;	//
		if(rs!=null) {
			rs.next();
			userDto = new UserDto();
			userDto.setUserid(rs.getString("userid"));
			userDto.setPassword(rs.getString("password"));
			userDto.setRole(rs.getString("role"));
							
		}
		rs.close();
		pstmt.close();
		return userDto;
	}
	
	public int deleteUser(String userid) throws Exception {
		pstmt = conn.prepareStatement("delete from tbl_user where userid=?");
		pstmt.setString(1, userid);
		
		int result = pstmt.executeUpdate();
		
		conn.commit();
		pstmt.close();
		return result;
	}
	
	
	//selectAllOrder
	public List<OrderDto> selectAllOrder() throws Exception{
		//SQL
//		select category, sum(price*quantity) from tbl_order
//		group by category
//		having sum(price*quantity) >= 50000
//		order by sum(price*quantity)desc;
		
		String sql = "select category, sum(price*quantity) from tbl_order"
				+ " group by category"
				+ " having sum(price*quantity) >= 50000"
				+ " order by sum(price*quantity)desc";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<OrderDto> list = new ArrayList();	//리턴값 저장용 새로운 객체
		OrderDto orderDto = null;	//
		if(rs!=null) {
			while(rs.next()) {
				orderDto = new OrderDto();
				
				orderDto.setCATEGORY(rs.getString(1));
				orderDto.setSUM(rs.getInt(2));
				list.add(orderDto);
				
			}
			
		}
		rs.close();
		pstmt.close();
		return list;
		
	}
	
	//selectDate_Avg
	
	public List<OrderDto2> selectDate_Avg() throws Exception{
		//sql
//		select order_date,round(avg(price*quantity),2)
//		from tbl_order
//		group by order_date;
		
		String sql = "select order_date,round(avg(price*quantity),2)"
				+ " from tbl_order"
				+ " group by order_date";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<OrderDto2> list = new ArrayList<OrderDto2>();
		OrderDto2 orderDto2 = null;
		
		if(rs!=null) {
			while(rs.next()) {
				orderDto2 = new OrderDto2();
				orderDto2.setOrder_date(rs.getDate(1).toLocalDate());
				orderDto2.setAverage(rs.getDouble(2));
				list.add(orderDto2);
			}
		}
		rs.close();
		pstmt.close();
		return list;
	}
	
	//selectAddr_Date_Sum
	public List<OrderDto3> selectAllOrder3() throws Exception{
		//SQL
//		select u.addr , o.order_date, sum(o.price*o.quantity)
//		from tbl_user u
//		join tbl_order o
//		on u.userid=o.userid
//		group by u.addr, o.order_date;
		
		String sql = "select u.addr , o.order_date, sum(o.price*o.quantity)"
				+ " from tbl_user u"
				+ " join tbl_order o"
				+ " on u.userid=o.userid"
				+ " group by u.addr, o.order_date"
				+ " order by u.addr asc , sum(o.PRICE*o.QUANTITY) desc";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<OrderDto3> list = new ArrayList();	//리턴값 저장용 새로운 객체
		OrderDto3 orderDto3 = null;	//
		
		if(rs!=null) {
			while(rs.next()) {
				orderDto3 = new OrderDto3();
				
				orderDto3.setAddr(rs.getString(1));
				orderDto3.setOrder_date(rs.getDate(2).toLocalDate());
				orderDto3.setSum_price(rs.getInt(3));
				
				list.add(orderDto3);
				
			}
			
		}
		rs.close();
		pstmt.close();
		return list;
		
	}


}

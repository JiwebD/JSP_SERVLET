package Domain.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Domain.Dao.ConnectionPool.ConnectionItem;
import Domain.Dao.ConnectionPool.ConnectionPool;
import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.UserDto;

public class BookDaoImpl implements BookDao {		
	
	
	
	//DB Attr
//	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
//	private String id = "root";
//	private String pw = "1234";
//	private String url = "jdbc:mysql://localhost:3306/bookDB";
	
	
	//CONNECTION POOL
	private ConnectionPool connectionPool;
	private ConnectionItem connectionItem;
	//싱글톤 패턴처리
	private static BookDao instance;
	private BookDaoImpl() throws ClassNotFoundException, SQLException {
//		System.out.println("[SERVICE] BookServiceImpl init...");
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("BookDaoImpl DB Connection Success");
		
		connectionPool = ConnectionPool.getInstance();
	};
	
	public static BookDao getInstance() throws ClassNotFoundException, SQLException {
		if(instance==null)
			instance = new BookDaoImpl();
		return instance;
	}
	
	//CRUD
	@Override
	public int insert(BookDto bookDto) throws Exception {
		
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
			pstmt.setString(1, bookDto.getBookCode());
			pstmt.setString(2, bookDto.getBookName());
			pstmt.setString(3, bookDto.getPublisher());
			pstmt.setString(4, bookDto.getIsbn());
			
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's INSERT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e) {}
		}
	}
	
	@Override
	public int update(BookDto bookDto) throws Exception {
		
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("update tbl_book set bookName=?, publisher=?, isbn=? where bookCode=?");
			pstmt.setString(1, bookDto.getBookName());
			pstmt.setString(2, bookDto.getPublisher());
			pstmt.setString(3, bookDto.getIsbn());
			pstmt.setString(4, bookDto.getBookCode());
						
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's UPDATE SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}
	}
	@Override
	public int delete(String bookCode) throws Exception {
		
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();		
			
			pstmt = conn.prepareStatement("delete from tbl_book where bookCode=?");
			pstmt.setString(1, bookCode);
			
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's DELETE SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}

	}
	//단건조회
	@Override
	public BookDto select(BookDto bookDto) {	
		return null;
	}
	//다건조회
	@Override
	public List<BookDto> selectAll() throws Exception {	
		List<BookDto> list = new LinkedList();
		BookDto dto = null;
		try {
			//connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("select * from tbl_book");

			rs=pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					dto = new BookDto();
					dto.setBookCode(rs.getString(1));
					dto.setBookName(rs.getString(2));
					dto.setPublisher(rs.getString(3));
					dto.setIsbn(rs.getString(4));
					
					list.add(dto);
					
				}
			}
			
			return list;
			

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e2) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	@Override
	public List<BookDto> selectAll(int offset, int amount) throws Exception {
		List<BookDto> list = new LinkedList();
		BookDto dto = null;
		try {
			//connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("select * from tbl_book order by bookCode desc limit ?,?");
			pstmt.setInt(1,  offset);
			pstmt.setInt(2,  amount);
			
			
			rs=pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					dto = new BookDto();
					dto.setBookCode(rs.getString(1));
					dto.setBookName(rs.getString(2));
					dto.setPublisher(rs.getString(3));
					dto.setIsbn(rs.getString(4));
					
					list.add(dto);
					
				}
			}
			
			return list;
			

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e2) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}
	}	
	
	//전체 건수 조회
	@Override
	public long count() throws Exception{
		long count = 0;	
		try {
			//connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("select count(*) from tbl_book");
			
			
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				count = rs.getLong(1);				
			}
			
			return count;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e2) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	@Override
	public BookDto select(String bookCode) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("select * from tbl_book where bookCode=?");
			pstmt.setString(1, bookCode);
			rs = pstmt.executeQuery();
			
			BookDto bookDto = null;
			if(rs!=null && rs.next()) {
				
				//방법1
				bookDto = new BookDto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				
				//방법2
//				userDto = new UserDto();
//				userDto.setPassword(rs.getString(1));
//				userDto.setUsername(rs.getString(2));
//				userDto.setRole(rs.getString(3));
			}
				
			return bookDto;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("USERDAO's INSERT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();}catch(Exception e2) {}
			//Connection release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	
	@Override
	public List<BookDto> selectAll(int offset, int amount, String type, String keyword) throws Exception {
		List<BookDto> list = new LinkedList();
		BookDto dto = null;
		try {
			//connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			pstmt = conn.prepareStatement("select * from tbl_book where "+type+" like '%"+keyword+"% ' order by bookCode desc limit ?,?");
			pstmt.setInt(1,  offset);
			pstmt.setInt(2,  amount);
			
			
			rs=pstmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					dto = new BookDto();
					dto.setBookCode(rs.getString(1));
					dto.setBookName(rs.getString(2));
					dto.setPublisher(rs.getString(3));
					dto.setIsbn(rs.getString(4));
					
					list.add(dto);
					
				}
			}
			
			return list;
			

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e2) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	@Override
	public long count(Criteria criteria) throws Exception {
		long count = 0;	
		try {
			//connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();
			
			String type = criteria.getType();
			String keyword = criteria.getKeyword();
			pstmt = conn.prepareStatement("select count(*) from tbl_book where "+type+" like '%"+keyword+"%'");
			
			
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				count = rs.getLong(1);				
			}
			
			return count;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (Exception e2) {}
			//Connectio release
			connectionPool.releaseConnection(connectionItem);
		}
	}
	
}

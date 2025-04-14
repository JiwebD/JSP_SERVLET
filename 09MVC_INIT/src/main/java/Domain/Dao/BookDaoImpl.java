//package Domain.Dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import Ch38.Domain.Dto.BookDto;
//import Domain.Dao.ConnectionPool.ConnectionItem;
//
//public class BookDaoImpl implements BookDao {		
//	
//	
//	
//	//DB Attr
////	private Connection conn;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//	
////	private String id = "root";
////	private String pw = "1234";
////	private String url = "jdbc:mysql://localhost:3306/bookDB";
//	
//	
//	//CONNECTION POOL
//	private ConnectionPool connectionPool;
//	private ConnectionItem connectionItem;
//	//싱글톤 패턴처리
//	private static BookDao instance;
//	private BookDaoImpl() throws ClassNotFoundException, SQLException {
////		System.out.println("[SERVICE] BookServiceImpl init...");
////		Class.forName("com.mysql.cj.jdbc.Driver");
////		conn = DriverManager.getConnection(url,id,pw);
////		System.out.println("BookDaoImpl DB Connection Success");
//		
//		connectionPool = ConnectionPool.getInstance();
//	};
//	
//	public static BookDao getInstance() throws ClassNotFoundException, SQLException {
//		if(instance==null)
//			instance = new BookDaoImpl();
//		return instance;
//	}
//	
//	//CRUD
//	@Override
//	public int insert(BookDto bookDto) throws Exception {
//		
//		try {
//			connectionItem = connectionPool.getConnection();
//			Connection conn = connectionItem.getConn();
//			
//			pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
//			pstmt.setString(1, bookDto.getBookCode());
//			pstmt.setString(2, bookDto.getBookName());
//			pstmt.setString(3, bookDto.getPublisher());
//			pstmt.setString(4, bookDto.getIsbn());
//			
//			//Connectio release
//			connectionPool.releaseConnection(connectionItem);
//			
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new SQLException("BOOKDAO's INSERT SQL EXCEPTION!!");
//		}finally {
//			try {pstmt.close();} catch (SQLException e) {}
//		}
//	}
//	
//	@Override
//	public int update(BookDto bookDto) {
//		return -1;
//	}
//	@Override
//	public int delete(BookDto bookDto) {
//		return -1;
//	}
//	//단건조회
//	@Override
//	public BookDto select(BookDto bookDto) {	
//		return null;
//	}
//	//다건조회
//	@Override
//	public List<BookDto> selectAll() {	
//		return null;
//	}	
//	
//}

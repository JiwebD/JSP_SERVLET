package Domain.Dao;

import java.sql.SQLException;
import java.util.List;

import Domain.Dto.BookDto;

public interface BookDao {
	//인터페이스화 하는법 BookDaoImpl.java 우클릭 >
	//Refator > Extract Interface..
	//인터페이스 이름 BookDao > select all선택 > OK

	//CRUD
	int insert(BookDto bookDto) throws Exception;

	int update(BookDto bookDto)	throws Exception;

	int delete(BookDto bookDto)	throws Exception;

	//단건조회
	BookDto select(BookDto bookDto) throws Exception;

	//다건조회
	public List<BookDto> selectAll() throws Exception;

	List<BookDto> selectAll(int offset, int amount) throws Exception;
	
	public long count() throws Exception;

}
package library.dao.repos.impl;

import java.sql.Connection;
import java.sql.SQLException;
import library.dao.mappers.IMapper;
import library.domain.Book;


public class BookRepository extends RepositoryBase<Book>{
	
	public BookRepository(Connection connection, IMapper<Book> mapper) throws SQLException{
			super(connection, mapper);
	}
	
	@Override
	protected String getInsertQuerySql() {
		return "INSERT INTO book(title, publisher, year, isAvailable) VALUES (?,?,?,?)";
	}
	@Override
	protected String getUpdateQuerySql() {
		return "UPDATE book SET (title, publisher, year, isAvailable) = (?,?, ?, ?) WHERE id=?";

	}
	@Override
	protected String createTableStatementSql() {
		return "CREATE TABLE book("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "title varchar(20),"
				+ "publisher varchar(50),"
				+ "year bigint,"
				+ "isAvailable boolean"
				+ ")";
	}
	@Override
	protected void setUpdate(Book book) throws SQLException {
		update.setString(1, book.getTitle());
		update.setString(2, book.getPublisher());
		update.setInt(3, book.getYear());
		update.setBoolean(4, book.isAvailable());
		update.setInt(5, book.getId());
	}
	
	@Override
	protected void setInsert(Book book) throws SQLException {
		insert.setString(1, book.getTitle());
		insert.setString(2, book.getPublisher());
		insert.setInt(3, book.getYear());
		insert.setBoolean(4, book.isAvailable());
	}
	
	@Override
	protected String getTableName() {
		return "person";
	}
}

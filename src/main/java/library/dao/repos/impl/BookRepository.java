package library.dao.repos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dao.mappers.IMapper;
import library.dao.repos.IBookRepository;
import library.dao.uow.IUnitOfWork;
import library.domain.Book;

public class BookRepository extends RepositoryBase<Book> implements IBookRepository {
	
	String selectByTitleSql = "SELECT * FROM book WHERE title=?";
	String selectByPublisherSql = "SELECT * FROM book WHERE publisher=?";
	String selectByAvailabilitySql = "SELECT * FROM book WHERE isAvailable=?";
	PreparedStatement selectByTitle;
	PreparedStatement selectByPublisher;
	PreparedStatement selectByAvailability;
	
	public BookRepository(Connection connection, IMapper<Book> mapper, IUnitOfWork uow) throws SQLException{
			super(connection, mapper, uow);
			selectByTitle = connection.prepareStatement(selectByTitleSql);
			selectByPublisher = connection.prepareStatement(selectByPublisherSql);
			selectByAvailability = connection.prepareStatement(selectByAvailabilitySql);
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
		update.setInt(1, book.getId());
		update.setString(2, book.getTitle());
		update.setString(3, book.getPublisher());
		update.setInt(4, book.getYear());
		update.setBoolean(5, book.isAvailable());
	
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
		return "book";
	}

	@Override
	public List<Book> withTitle(String title) {
		List<Book> result = new ArrayList<Book>();
		try {
			selectByTitle.setString(1, title);
			ResultSet rs = selectByTitle.executeQuery();
			while(rs.next()) result.add(mapper.map(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Book> withPublisher(String publisher) {
		List<Book> result = new ArrayList<Book>();
		try {
			selectByPublisher.setString(1, publisher);
			ResultSet rs = selectByPublisher.executeQuery();
			while(rs.next()) result.add(mapper.map(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Book> withAvailability(boolean isAvailable) {
		List<Book> result = new ArrayList<Book>();
		try {
			selectByAvailability.setBoolean(1, isAvailable);
			ResultSet rs = selectByAvailability.executeQuery();
			while(rs.next()) result.add(mapper.map(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

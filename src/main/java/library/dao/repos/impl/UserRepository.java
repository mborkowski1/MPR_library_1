package library.dao.repos.impl;

import java.sql.Connection;
import java.sql.SQLException;

import library.dao.mappers.IMapper;
import library.dao.uow.IUnitOfWork;
import library.domain.User;

public class UserRepository extends RepositoryBase<User>{

	public UserRepository(Connection connection, IMapper<User> mapper, IUnitOfWork uow) throws SQLException{
		super(connection, mapper, uow);
	}
	
	@Override
	protected String getUpdateQuerySql() {
		return ""
				+ "UPDATE user SET (id,email,login,password) = (?,?,?,?) WHERE id=?"
				+ "";
	}
	
	@Override
	protected String getInsertQuerySql() {
		return ""
				+ "INSERT INTO user(id,email,login,password) VALUES (?,?,?,?)"
				+ "";
	}
	
	@Override
	protected String getTableName() {
		return "user";
	}
	
	@Override
	protected String createTableStatementSql() {
		return "CREATE TABLE user("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "login varchar(20),"
				+ "password varchar(20),"
				+ "email varchar(50)"
				+ ")";
	}
	
	@Override
	protected void setUpdate(User user) throws SQLException {
		update.setInt(1, user.getId());
		update.setString(2, user.getLogin());
		update.setString(3, user.getPassword());
		update.setString(4, user.getEmail());
	}
	
	@Override
	protected void setInsert(User user) throws SQLException {
		insert.setString(1, user.getLogin());
		insert.setString(2, user.getPassword());
		insert.setString(3, user.getEmail());
	}	
}

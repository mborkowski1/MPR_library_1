package library.dao.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationItemRepository {
	
	String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	Connection connection;
	
	public ReservationItemRepository(){
		
		try {
			
			connection = DriverManager.getConnection(url);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(){
		
		String sql = "CREATE TABLE reservation_item("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "reservation_id bigint,"
				+ "book_id bigint"
				+ ")";
		
		try {
			Statement createTable = connection.createStatement();
			createTable.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
	static final String dbUrl = "jdbc:mysql://localhost:3306/SkyDiving";
	static final String usr = "root";
	static final String pwd = "";

	private static Connection myCon = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException {
		myCon = DriverManager.getConnection(dbUrl, usr, pwd);
		System.out.println("DB connection successful...");

		// create a statement
		stmt = myCon.createStatement();

		// execute the statement
		String sql = "select * from Session";
		rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			System.out.println(rs.getString("date") + ", " + rs.getString("booked"));
		}
		stmt.close();

		myCon.close();
	}
	
	
	
}

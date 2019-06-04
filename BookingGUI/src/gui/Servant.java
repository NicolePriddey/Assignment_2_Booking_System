package gui;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Servant extends UnicastRemoteObject implements Interface {
	
	private static final long serialVersionUID = -5830601784137874109L;
	static final String dbUrl = "jdbc:mysql://localhost:3306/SkyDiving";
	static final String usr = "root";
	static final String pwd = "";

	private static Connection myCon = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	String spaces = null;
	
	protected Servant() throws RemoteException {
		super();
	}

	@Override
	public String getTimes(String date) throws RemoteException, SQLException {
		connect();
		stmt = myCon.createStatement();
		String sql = "select * from Session where date = '" + date + "'";
		rs = stmt.executeQuery(sql);
		String s = "";
		
		while (rs.next()) {
			s += rs.getString("id") + "~" + rs.getString("date") + "~" + rs.getString("time") + "~" + rs.getString("height") + "~" + rs.getString("spaces") + "~" +rs.getString("price") + ";";
		}
		stmt.close();
		myCon.close();
		return s;
	}

	@Override
	public boolean checkPlaces(String id, String userPlaces) throws RemoteException, SQLException {
		connect();
		stmt = myCon.createStatement();
		String sql = "select spaces from Session where id = '" + id + "'";
		rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			spaces = rs.getString("spaces");
		}
		
		if (Integer.parseInt(userPlaces) <= Integer.parseInt(spaces)) return true;
		
		stmt.close();
		myCon.close();
		return false;
	}

	@Override
	public ResultSet view() throws RemoteException, SQLException {
		connect();
		stmt = myCon.createStatement();
	
		String sql = "SELECT * FROM session WHERE date = '2019-05-30'";
		rs = stmt.executeQuery(sql);
		System.out.println("hello");
		while (rs.next()) {
			System.out.println(rs.getString("date") + ", " + rs.getString("booked"));
		}
		stmt.close();
		myCon.close();
		return null;
	}

	

	@Override
	public void connect() throws RemoteException, SQLException {
		myCon = DriverManager.getConnection(dbUrl, usr, pwd);
		System.out.println("DB connection successful...");
	}

	@Override
	public boolean book(String id, String userPlaces) throws RemoteException, SQLException {
		if (checkPlaces(id, userPlaces)) {
			connect();
			stmt = myCon.createStatement();
			int newSpaces = Integer.parseInt(spaces) - Integer.parseInt(userPlaces);
			String sql = "UPDATE session SET spaces = '" + newSpaces + "' WHERE id = '" + id + "'";
			stmt.executeUpdate(sql);
			return true;
		}
		stmt.close();
		myCon.close();
		return false;
	}

	//INSERT INTO `Session` (`id`, `date`, `time`, `level`, `booked`) VALUES (NULL, '2019-05-30', '10:30:00', '1', '0')
}

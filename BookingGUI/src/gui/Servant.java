package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Servant extends UnicastRemoteObject implements IBooking {
	
	private static final long serialVersionUID = -5830601784137874109L;
	static final String dbUrl = "jdbc:mysql://localhost:3306/SkyDiving?useSSL=false";
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
		String results = "";
		
		while (rs.next()) {
			results += rs.getString("id") + "~" + rs.getString("date") + "~" + rs.getString("time") + "~" + rs.getString("height") + "~" + rs.getString("spaces") + "~" +rs.getString("price") + ";";
		}
		stmt.close();
		myCon.close();
		return results;
	}

	@Override
	public boolean checkPlaces(String id, String userPlaces) throws RemoteException, SQLException {
		connect();
		
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
	public void connect() throws RemoteException, SQLException {
		myCon = DriverManager.getConnection(dbUrl, usr, pwd);
		stmt = myCon.createStatement();
	}

	@Override
	public boolean book(String id, String userPlaces) throws RemoteException, SQLException {
		if (checkPlaces(id, userPlaces)) {
			connect();
			
			int newSpaces = Integer.parseInt(spaces) - Integer.parseInt(userPlaces);
			String sql = "UPDATE session SET spaces = '" + newSpaces + "' WHERE id = '" + id + "'";
			stmt.executeUpdate(sql);
			return true;
		}
		stmt.close();
		myCon.close();
		return false;
	}

	@Override
	public boolean cancel(String id, String userPlaces) throws RemoteException, SQLException {
		connect();
		
		String sql = "select spaces from Session where id = '" + id + "'";
		rs = stmt.executeQuery(sql);
		int spaces = 0;
		while (rs.next()) {
			spaces = Integer.parseInt(rs.getString("spaces"));
		}
		int newSpaces = spaces + Integer.parseInt(userPlaces);
		
		sql = "UPDATE session SET spaces = '" + newSpaces + "' WHERE id = '" + id + "'";
		stmt.executeUpdate(sql);

		stmt.close();
		myCon.close();
		return true;
	}

	@Override
	public String getWeek() throws RemoteException, SQLException {
		connect();
		String today = java.time.LocalDate.now().plusDays(0).toString();
		String weekStart = java.time.LocalDate.now().plusDays(-7).toString();
		stmt = myCon.createStatement();
		String sql = "select * from Session where date BETWEEN '" + weekStart +"' AND '" + today +"'";
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
	public void createFile() throws SQLException, IOException {
		String data = getWeek();
		String report = "ID\t\tDate\t\t\tTime\t\t\tHeight\tSpaces\tPrice\n";
		File file = new File("WeekReport.txt");
		FileOutputStream stream = new FileOutputStream(file);
		
		String[] sessionsArr = data.split(";");
		for(String s : sessionsArr) {
			String[] sessionElements = s.split("~");
				for (String se : sessionElements) {
					report += se + "\t\t";
			}
			report += "\n";
		}
		byte[] reportInBytes = report.getBytes();
		stream.write(reportInBytes);
		stream.flush();
		stream.close();
	}
}

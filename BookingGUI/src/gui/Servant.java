package gui;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

public class Servant extends UnicastRemoteObject implements Interface {
	
	private static final long serialVersionUID = -5830601784137874109L;
	static final String dbUrl = "jdbc:mysql://localhost:3306/SkyDiving";
	static final String usr = "root";
	static final String pwd = "";

	private static Connection myCon = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	
	protected Servant() throws RemoteException {
		super();
	}

	@Override
	public String getTimes(String date) throws RemoteException, SQLException {
		connect();
		stmt = myCon.createStatement();
		
		String sql = "select * from Session where date = '" + date + "'";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		String s = "";
		System.out.println("Hello?");
		while (rs.next()) {
			s += rs.getString("date") + "~" + rs.getString("time") + "~" + rs.getString("height") + "~" + rs.getString("spaces") + "~" +rs.getString("price") + ";";
		}
		stmt.close();
		myCon.close();
		return s;
	}

	@Override
	public boolean checkStatus(Date date, Time time) throws RemoteException {
		// TODO Auto-generated method stub
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
	public void book() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
	}

	//INSERT INTO `Session` (`id`, `date`, `time`, `level`, `booked`) VALUES (NULL, '2019-05-30', '10:30:00', '1', '0')
}

package gui;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public interface Interface extends Remote {
	public ResultSet getTimes(String date) throws RemoteException, SQLException;
	public boolean checkStatus(Date date, Time time) throws RemoteException;
	public ResultSet view() throws RemoteException, SQLException;
	public void connect() throws RemoteException, SQLException;
	//method signatures to do stuff need to throws RemoteException;
}

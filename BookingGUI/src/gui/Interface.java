package gui;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface Interface extends Remote {
	public String getTimes(String date) throws RemoteException, SQLException;
	boolean checkPlaces(String string, String string2) throws RemoteException, SQLException;
	public ResultSet view() throws RemoteException, SQLException;
	public void connect() throws RemoteException, SQLException;
	public void book() throws RemoteException, SQLException;
	//method signatures to do stuff need to throws RemoteException;
	
	
}

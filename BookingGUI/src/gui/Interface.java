package gui;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Interface extends Remote {
	public String getTimes(String date) throws RemoteException, SQLException;
	boolean checkPlaces(String id, String userPlaces) throws RemoteException, SQLException;
	public ResultSet view() throws RemoteException, SQLException;
	public void connect() throws RemoteException, SQLException;
	public boolean book(String id, String userPlaces) throws RemoteException, SQLException;	
	public boolean cancel(String id, String userPlaces) throws RemoteException, SQLException;	
}

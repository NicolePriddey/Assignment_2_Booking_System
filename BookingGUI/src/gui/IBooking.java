package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IBooking extends Remote {
	public String getTimes(String date) throws RemoteException, SQLException;
	boolean checkPlaces(String id, String userPlaces) throws RemoteException, SQLException;
	public void connect() throws RemoteException, SQLException;
	public boolean book(String id, String userPlaces) throws RemoteException, SQLException;	
	public boolean cancel(String id, String userPlaces) throws RemoteException, SQLException;
	public String getWeek() throws RemoteException, SQLException;
	public void createFile() throws RemoteException, SQLException, FileNotFoundException, IOException;
}

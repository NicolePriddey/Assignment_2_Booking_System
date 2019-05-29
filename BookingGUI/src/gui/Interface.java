package gui;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Time;

public interface Interface extends Remote {
	public Object[] getTimes(Date date) throws RemoteException;
	public boolean checkStatus(Date date, Time time) throws RemoteException;
	public Object[] view()throws RemoteException;
	//method signatures to do stuff need to throws RemoteException;
}

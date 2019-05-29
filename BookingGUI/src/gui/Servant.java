package gui;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.Time;

public class Servant extends UnicastRemoteObject implements Interface {

	protected Servant() throws RemoteException {
		super();
	}

	@Override
	public Object[] getTimes(Date date) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkStatus(Date date, Time time) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] view() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

		
	//@override and give functionality to methods in the interface
}

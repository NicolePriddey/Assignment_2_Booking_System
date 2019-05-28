package bookingSys;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servant extends UnicastRemoteObject implements Interface {

	protected Servant() throws RemoteException {
		super();
	}

		
	//@override and give functionality to methods in the interface
}

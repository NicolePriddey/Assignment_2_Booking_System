package bookingSys;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;


public class Server extends JFrame {
	public static void main(String[] args) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(1099);
		
		registry.rebind("passkey", new Servant());
		System.out.println("Server is ready");
	}
}

//C:\>netstat -aon | find "1099"
	//taskkill /pid 5264 /f
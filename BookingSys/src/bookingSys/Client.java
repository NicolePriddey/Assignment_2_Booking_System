package bookingSys;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;


public class Client extends JFrame{
	public static void main(String[] args) {
		try {
			Interface service = (Interface) Naming.lookup("rmi://localhost:1099/passkey");
			//getContentPane().add
			//like a normal call but use service. to get methods
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}

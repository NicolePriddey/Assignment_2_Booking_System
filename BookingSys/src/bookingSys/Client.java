package bookingSys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame{
	
	public Client() {
		super("Skydiving booking system");
		JTextField title = new JTextField();
		title.setEditable(false);
				
		//getContentPane().add(userText, BorderLayout.NORTH);
		//chatWindow = new JTextArea();
		//chatWindow.setForeground(Color.WHITE);
		//chatWindow.setBackground(new Color(0, 128, 0));
		getContentPane().add(title);
		setSize(400, 250);
		setVisible(true);

		
	}
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

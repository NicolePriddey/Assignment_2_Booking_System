package gui;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ServerGui {

	protected Shell shell;
	static Registry registry;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		//Registry registry;
		try {
			registry = LocateRegistry.createRegistry(1099);
			
			registry.rebind("passkey", new Servant());
			System.out.println("Server is ready");
			
			ServerGui window = new ServerGui();
			window.open();
			//free up the port
			if (registry != null) UnicastRemoteObject.unexportObject(registry, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Button btnStart = new Button(shell, SWT.NONE);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					registry = LocateRegistry.createRegistry(1099);
					registry.rebind("passkey", new Servant());
					System.out.println("Server is ready");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnStart.setBounds(37, 45, 85, 26);
		btnStart.setText("Start");
		
		Button btnStop = new Button(shell, SWT.NONE);
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (registry != null)
					try {
						UnicastRemoteObject.unexportObject(registry, true);
						System.out.println("Server stopped");
					} catch (NoSuchObjectException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnStop.setBounds(239, 45, 85, 26);
		btnStop.setText("Stop");

	}
}

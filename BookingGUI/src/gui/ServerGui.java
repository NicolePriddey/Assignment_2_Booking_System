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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Combo;

public class ServerGui {

	protected Shell shell;
	static Registry registry;
	private Table table;
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
		shell.setSize(785, 529);
		shell.setText("SWT Application");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(38, 130, 704, 217);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setText("ID");
		tableColumn.setResizable(false);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(127);
		tableColumn_1.setText("Date");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Time");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(146);
		tableColumn_3.setText("Jump Height ( km ) ");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(138);
		tableColumn_4.setText("Spaces available");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] {"Today", "This week", "Next week"});
		combo.setBounds(43, 73, 176, 25);
		combo.select(0);
		
		Button btnChangeSpaces = new Button(shell, SWT.NONE);
		btnChangeSpaces.setBounds(38, 396, 134, 26);
		btnChangeSpaces.setText("Change spaces");

	}
}

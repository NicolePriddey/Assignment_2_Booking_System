package gui;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ServerGui {

	protected Shell shell;
	static Registry registry;
	private Table tblShowSessions;
	private Text txtCancelSpaces;
	private Text txtBookSpaces;
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
		shell.setSize(1000, 700);
		shell.setText("SWT Application");
		
		tblShowSessions = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tblShowSessions.setLinesVisible(true);
		tblShowSessions.setHeaderVisible(true);
		tblShowSessions.setBounds(38, 130, 704, 217);
		
		TableColumn tableColumn = new TableColumn(tblShowSessions, SWT.NONE);
		tableColumn.setText("ID");
		tableColumn.setResizable(false);
		
		TableColumn tableColumn_1 = new TableColumn(tblShowSessions, SWT.NONE);
		tableColumn_1.setWidth(127);
		tableColumn_1.setText("Date");
		
		TableColumn tableColumn_2 = new TableColumn(tblShowSessions, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Time");
		
		TableColumn tableColumn_3 = new TableColumn(tblShowSessions, SWT.NONE);
		tableColumn_3.setWidth(146);
		tableColumn_3.setText("Jump Height ( km ) ");
		
		TableColumn tableColumn_4 = new TableColumn(tblShowSessions, SWT.NONE);
		tableColumn_4.setWidth(138);
		tableColumn_4.setText("Spaces available");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		combo.setItems(new String[] {"Today", "This week", "Next week"});
		combo.setBounds(43, 73, 176, 25);
		combo.select(0);
		
		Button btnBook = new Button(shell, SWT.NONE);
		btnBook.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = tblShowSessions.getSelection();
				if( selection.length == 0 ) 
					JOptionPane.showMessageDialog(null, "Please select a time from the list to book", "No time selected", JOptionPane.ERROR_MESSAGE);
				else {				
					try {
						if (((Servant) registry).book(selection[0].getText(),txtBookSpaces.getText())) 
							JOptionPane.showMessageDialog(null, "you have booked time succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "There are not enough places in this session", "No Space", JOptionPane.ERROR_MESSAGE);
					} catch (RemoteException | SQLException e1) { e1.printStackTrace(); }
					
				}
				
			}
		});
		btnBook.setBounds(268, 522, 134, 32);
		btnBook.setText("Book");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnCancel.setBounds(708, 523, 134, 31);
		btnCancel.setText("Cancel");
		
		Label lblBookSpaces = new Label(shell, SWT.NONE);
		lblBookSpaces.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblBookSpaces.setBounds(25, 525, 141, 31);
		lblBookSpaces.setText("Book spaces  x");
		
		Label lblCancelSpaces = new Label(shell, SWT.NONE);
		lblCancelSpaces.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblCancelSpaces.setBounds(467, 525, 176, 31);
		lblCancelSpaces.setText("Cancel spaces  x");
		
		txtCancelSpaces = new Text(shell, SWT.BORDER);
		txtCancelSpaces.setText("1");
		txtCancelSpaces.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		txtCancelSpaces.setBounds(627, 523, 64, 31);
		
		txtBookSpaces = new Text(shell, SWT.BORDER);
		txtBookSpaces.setText("1");
		txtBookSpaces.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		txtBookSpaces.setBounds(161, 525, 64, 32);
		
		Button btnToday = new Button(shell, SWT.RADIO);
		btnToday.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String today = java.time.LocalDate.now().toString();
				try {
					String queryResults = Servant.getTimes(today);
					String[] sessionsArr = queryResults.split(";");
					tblShowSessions.removeAll();
					
					for(String s : sessionsArr) {
						String[] sessionElements = s.split("~");
						TableItem item = new TableItem(tblShowSessions, SWT.NONE);
						int column = 0;
						for (String se : sessionElements) {
							item.setText(column, se);
							column++;
						}
					}
					
				} catch (RemoteException | SQLException e1) { e1.printStackTrace(); }
				//do the stuff to show booking for this date
				// if nothing say "no booking"?
			}
		});
		btnToday.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnToday.setBounds(253, 73, 120, 31);
		btnToday.setText("Today");

	}
}

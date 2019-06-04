package gui;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class Client {
	static Interface service;

	protected Shell shell;
	private Table tblShowTimes;
	private Text txtNumPpl;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			service = (Interface) Naming.lookup("rmi://localhost:1099/passkey");
			Client window = new Client();
			window.open();
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
		shell.setText("Booking System");
		
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		dateTime.setBounds(181, 193, 135, 27);
		
		Label lblBookingSystem = new Label(shell, SWT.NONE);
		lblBookingSystem.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblBookingSystem.setBounds(312, 25, 346, 73);
		lblBookingSystem.setText("Booking system");
		
		Label lblDate = new Label(shell, SWT.NONE);
		lblDate.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDate.setBounds(66, 193, 65, 33);
		lblDate.setText("Date");
		
		Label lblTimesAvailabile = new Label(shell, SWT.NONE);
		lblTimesAvailabile.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTimesAvailabile.setBounds(66, 290, 150, 27);
		lblTimesAvailabile.setText("Times Availabile");
		
		tblShowTimes = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tblShowTimes.setBounds(66, 323, 863, 215);
		tblShowTimes.setHeaderVisible(true);
		tblShowTimes.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(tblShowTimes, SWT.NONE);
		tblclmnId.setResizable(false);
		tblclmnId.setText("ID");
		
		TableColumn tblclmnDate = new TableColumn(tblShowTimes, SWT.NONE);
		tblclmnDate.setWidth(127);
		tblclmnDate.setText("Date");
		
		TableColumn tblclmnTime = new TableColumn(tblShowTimes, SWT.NONE);
		tblclmnTime.setWidth(100);
		tblclmnTime.setText("Time");
		
		TableColumn tblclmHeight = new TableColumn(tblShowTimes, SWT.NONE);
		tblclmHeight.setWidth(146);
		tblclmHeight.setText("Jump Height ( km ) ");
		
		TableColumn tblclmnSpaces = new TableColumn(tblShowTimes, SWT.NONE);
		tblclmnSpaces.setWidth(138);
		tblclmnSpaces.setText("Spaces available");
		
		TableColumn tblclmnPrice = new TableColumn(tblShowTimes, SWT.NONE);
		tblclmnPrice.setWidth(93);
		tblclmnPrice.setText("Price ( $ )");
		
		Button btnCheckAvalibility = new Button(shell, SWT.NONE);
		btnCheckAvalibility.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnCheckAvalibility.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String date = dateTime.getYear() + "-" + "0" + (dateTime.getMonth() + 1) + "-" + dateTime.getDay();

		        try {
					String queryResults = service.getTimes(date);
//					System.out.println("String: " + queryResults);
					
					String[] sessionsArr = queryResults.split(";");
					tblShowTimes.removeAll();
					
					for(int i = 0; i < sessionsArr.length; i++) {
//						System.out.println("Session arr before split: " + sessionsArr[i]);
						String[] sessionElements = sessionsArr[i].split("~");
						TableItem item = new TableItem(tblShowTimes, SWT.NONE);
						for (int j = 0; j < sessionElements.length; j++){
//							System.out.println("Session elements: " + sessionElements[j] );
							item.setText(j, sessionElements[j]);
						}
					}
					
				} catch (RemoteException | SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println(date);
			}
		});
		btnCheckAvalibility.setBounds(406, 193, 174, 33);
		btnCheckAvalibility.setText("Check Availability");

		
		txtNumPpl = new Text(shell, SWT.BORDER);
		txtNumPpl.setText("1");
		txtNumPpl.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		txtNumPpl.setBounds(167, 576, 65, 33);
		
		Label lblPeopleX = new Label(shell, SWT.NONE);
		lblPeopleX.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPeopleX.setBounds(66, 576, 95, 26);
		lblPeopleX.setText("People    x ");
		
		Button btnBookTime = new Button(shell, SWT.NONE);
		btnBookTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = tblShowTimes.getSelection();
				if( selection.length == 0 ) 
					JOptionPane.showMessageDialog(null, "Please select a time from the list to book", "No time selected", JOptionPane.ERROR_MESSAGE);
				else {
					//Gets the id
					System.out.println("table selection:  " + selection[0].getText());
					txtNumPpl.getText();
					
				}
					//if ( selected item spaces available is less thantxtNumPpl)
					//messages saying that there isn't that many spaces available 
					//else 
					//find in database and lower sessions by how many selected 
					JOptionPane.showMessageDialog(null, "you have booked 'this' time succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				
			
		});
		
		btnBookTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnBookTime.setBounds(261, 576, 126, 33);
		btnBookTime.setText("Book Time");

	}
}

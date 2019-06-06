package gui;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class Customer {
	static IBooking service;

	protected Shell shell;
	private Table tblShowTimes;
	private Text txtNumPpl;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			service = (IBooking) Naming.lookup("rmi://localhost:1099/sky");
			Customer window = new Customer();
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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(1000, 700);
		shell.setText("Atmosphere Plunge Skydiving");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		dateTime.setBounds(181, 193, 135, 27);
		
		Label lblBookingSystem = new Label(shell, SWT.NONE);
		lblBookingSystem.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblBookingSystem.setForeground(SWTResourceManager.getColor(0, 102, 51));
		lblBookingSystem.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblBookingSystem.setBounds(189, 20, 601, 73);
		lblBookingSystem.setText("Atmosphere Plunge Booking ");
		
		Label lblDate = new Label(shell, SWT.NONE);
		lblDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblDate.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDate.setBounds(66, 193, 65, 33);
		lblDate.setText("Date");
		
		Label lblTimesAvailabile = new Label(shell, SWT.NONE);
		lblTimesAvailabile.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblTimesAvailabile.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTimesAvailabile.setBounds(66, 290, 150, 27);
		lblTimesAvailabile.setText("Times Availabile");
		
		tblShowTimes = new Table(shell, SWT.FULL_SELECTION);
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
		
		txtNumPpl = new Text(shell, SWT.BORDER);
		txtNumPpl.setText("1");
		txtNumPpl.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		txtNumPpl.setBounds(167, 576, 65, 33);
		
		Label lblPeopleX = new Label(shell, SWT.NONE);
		lblPeopleX.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblPeopleX.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPeopleX.setBounds(66, 576, 95, 26);
		lblPeopleX.setText("People    x ");
		
		Button btnCheckAvalibility = new Button(shell, SWT.NONE);
		btnCheckAvalibility.setForeground(SWTResourceManager.getColor(0, 102, 51));
		btnCheckAvalibility.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnCheckAvalibility.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String date = dateTime.getYear() + "-" + "0" + (dateTime.getMonth() + 1) + "-" + dateTime.getDay();

		        try {
					String queryResults = service.getTimes(date);
					String[] sessionsArr = queryResults.split(";");
					tblShowTimes.removeAll();
					
					for(String s : sessionsArr) {
						String[] sessionElements = s.split("~");
						if(Integer.parseInt(sessionElements[4]) != 0) {
							TableItem item = new TableItem(tblShowTimes, SWT.NONE);
							int column = 0;
							for (String se : sessionElements) {
								item.setText(column, se);
								column++;
							}
						}
					}
				} catch (RemoteException | SQLException e1) { e1.printStackTrace(); }
			}
		});
		btnCheckAvalibility.setBounds(406, 193, 174, 33);
		btnCheckAvalibility.setText("Check Availability");

		Button btnBookTime = new Button(shell, SWT.NONE);
		btnBookTime.setForeground(SWTResourceManager.getColor(0, 102, 51));
		btnBookTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = tblShowTimes.getSelection();
				if( selection.length == 0 ) 
					JOptionPane.showMessageDialog(null, "Please select a time from the list to book", "No time selected", JOptionPane.ERROR_MESSAGE);
				else {				
					try {
						if (service.book(selection[0].getText(),txtNumPpl.getText())) 
							JOptionPane.showMessageDialog(null, "you have booked time succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "There are not enough places in this session", "No Space", JOptionPane.ERROR_MESSAGE);
					} catch (RemoteException | SQLException e1) { e1.printStackTrace(); }
				}
			}
		});
		btnBookTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnBookTime.setBounds(261, 576, 126, 33);
		btnBookTime.setText("Book Time");
	}
}

package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;

import java.rmi.Naming;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.List;

public class Client {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Interface service = (Interface) Naming.lookup("rmi://localhost:1099/passkey");
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
		
		Button btnCheckAvalibility = new Button(shell, SWT.NONE);
		btnCheckAvalibility.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnCheckAvalibility.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCheckAvalibility.setBounds(406, 193, 174, 33);
		btnCheckAvalibility.setText("Check Availability");
		
		Label lblTimesAvailabile = new Label(shell, SWT.NONE);
		lblTimesAvailabile.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTimesAvailabile.setBounds(66, 290, 150, 27);
		lblTimesAvailabile.setText("Times Availabile");
		
		Button btnBookTime = new Button(shell, SWT.NONE);
		btnBookTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnBookTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnBookTime.setBounds(66, 570, 126, 33);
		btnBookTime.setText("Book Time");
		
		List list = new List(shell, SWT.BORDER);
		list.setItems(new String[] {"time 1", "time 2"});
		list.setBounds(66, 339, 863, 193);

	}
}

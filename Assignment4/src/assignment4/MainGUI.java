package assignment4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame {

	private JPanel container, legend, btnPanel, status;
	private JLabel id, fName, lName, mi, address, city, state, tele, lblstatus;
	private JTextField txtID, txtfName, txtlName, txtMi, txtAddress, txtCity, txtState, txtTele;
	private JButton viewBtn, insertBtn, updateBtn, clearBtn;
	private ActionListener listener;
	private Statement stmt;

	public MainGUI() {

		// setting the Frame
		setTitle("Staff Information");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);

		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// Get the button that triggers the event
				JButton source = (JButton) e.getSource();
				// Check which button was pressed and call the corresponding methods below
				if (source.getText().equals("View")) {
					viewStaff();
				} else if (source.getText().equals("Insert")) {
					insertStaff();
				} else if (source.getText().equals("Update")) {
					updateStaff();
				} else if (source.getText().equals("Clear")) {
					clearStaff();
				}
			}
		}

		// Creating an instance of the listener class
		listener = new ButtonListener();
		
		// Have a container panel to add and arrange all other individual panels
		container = new JPanel();
		createFieldLegend();
		createButtonPanel();
		createStatusLabel();
		add(container);
		
		initializeDB();

	}

	public void initializeDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection conn;

		try {
			String pswd = "12345";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "n01538048", "oracle");
			System.out.println("Connection to DB is established successfully!");
			stmt = conn.createStatement();
			lblstatus.setText("Database Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//create the legend field as per example
	public void createFieldLegend() {
		legend = new JPanel();
		legend.setLayout(new GridBagLayout());
		legend.setBorder(new TitledBorder(new EtchedBorder(), "Staff Information"));
		
	//use GridBag to ensure position of the elements are uniformed
		GridBagConstraints gbc = new GridBagConstraints();

		// To ensure elements take up just the right amount of space
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Similar to padding in CSS, setting the spaces between each element
		gbc.insets = new Insets(5, 10, 5, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		id = new JLabel("ID:* ");
		legend.add(id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		txtID = new JTextField(10);
		legend.add(txtID, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		fName = new JLabel("First Name:* ");
		legend.add(fName, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		txtfName = new JTextField(10);
		legend.add(txtfName, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		lName = new JLabel("Last Name:* ");
		legend.add(lName, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		txtlName = new JTextField(10);
		legend.add(txtlName, gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		mi = new JLabel("mi: ");
		legend.add(mi, gbc);

		gbc.gridx = 5;
		gbc.gridy = 1;
		txtMi = new JTextField(3);
		legend.add(txtMi, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		address = new JLabel("Address: ");
		legend.add(address, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		txtAddress = new JTextField(10);
		legend.add(txtAddress, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		city = new JLabel("City: ");
		legend.add(city, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		txtCity = new JTextField(10);
		legend.add(txtCity, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		state = new JLabel("State: ");
		legend.add(state, gbc);

		gbc.gridx = 3;
		gbc.gridy = 3;
		txtState = new JTextField(3); //due to GridBag format, this textfield will expand accordingly
		legend.add(txtState, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		tele = new JLabel("Telephone:* ");
		legend.add(tele, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		txtTele = new JTextField(10);
		legend.add(txtTele, gbc);

		container.add(legend);
	}

	// adding the buttons to the container
	public void createButtonPanel() {
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridBagLayout());
		btnPanel.setPreferredSize(new Dimension(450, 50));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		viewBtn = new JButton("View");
		btnPanel.add(viewBtn, gbc);
		viewBtn.addActionListener(listener);

		gbc.gridx = 1;
		insertBtn = new JButton("Insert");
		btnPanel.add(insertBtn, gbc);
		insertBtn.addActionListener(listener);

		gbc.gridx = 2;
		updateBtn = new JButton("Update");
		btnPanel.add(updateBtn, gbc);
		updateBtn.addActionListener(listener);

		gbc.gridx = 3;
		clearBtn = new JButton("Clear");
		btnPanel.add(clearBtn, gbc);
		clearBtn.addActionListener(listener);

		container.add(btnPanel);

	}

	// create a label to ensure notify the user that the Database is connected
	public void createStatusLabel() {
		status = new JPanel();
		lblstatus = new JLabel("");
		status.add(lblstatus);
		container.add(status);
	}

	public void viewStaff() {
		
		// Get the staff ID from a text field
		String id = txtID.getText();

		// Construct a SQL Statement to retrieve staff information based on the ID
		String sql = "SELECT id, lastname, firstname, upper(mi) as mi, address, city, state, "
				+ " decode(telephone,null,telephone,substr(telephone,1,3)||'-'||substr(telephone,4,3)||'-'||substr(telephone,7,4)) as telephone "
				+ " FROM staff WHERE id = " + id;
		
		try {
			// Execute the SQL statement and get the result set
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				txtlName.setText(rs.getString("lastname"));
				txtfName.setText(rs.getString("firstname"));
				txtMi.setText(rs.getString("mi"));
				txtAddress.setText(rs.getString("address"));
				txtCity.setText(rs.getString("city"));
				txtState.setText(rs.getString("state"));
				txtTele.setText(rs.getString("telephone"));
				
				txtID.setBackground(Color.YELLOW);

			} else {
				// If a record Id was not found with the given ID
				JOptionPane.showMessageDialog(null, "Record Id " + id + " not found");
			}

		} catch (SQLException e1) {
			// If an SQL exception occurs, display an error message
			String errMsg = e1.getLocalizedMessage();
			if (errMsg.contains("ORA-00936"))
				JOptionPane.showMessageDialog(null, "Please enter the record id");
			e1.printStackTrace();
		}
	}

	public void insertStaff() {
		
		//Get the input values from GUI
		String id = txtID.getText();
		String lastName = txtlName.getText();
		String firstName = txtfName.getText();
		String mi = txtMi.getText();
		String address = txtAddress.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String telephone = txtTele.getText().replace("-","");
		
		// Validate input values to make sure that they are not empty, have the correct format, and meet any other requirements
		// Check that record id is entered
		if (id == null || id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter the record id.");
			return;
		}
		// Check the ID is 9 digits long
		if (id.length() != 9) {
			JOptionPane.showMessageDialog(null, "Please enter the record id with 9 digits");
			return;
		}
		// Check any other required fields are entered which are lastname, firstname, and telephone no 
		if (lastName == null || lastName.isEmpty() || firstName == null || firstName.isEmpty() || telephone == null || telephone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter the firstname, lastname and telephone.");
			return;
		}
		// Check the telephone is 10 digits long
		if (telephone.length() != 10) {
			JOptionPane.showMessageDialog(null, "Please enter the telephone no with 10 digits");
			return;
		}

		// Construct a SQL statement to insert a new row into database
		String sql = "INSERT INTO staff (id, lastname, firstname, mi, address, city, state, telephone) VALUES " + "('"
				+ id + "', '" + lastName + "', '" + firstName + "', '" + mi + "', '" + address + "', '" + city + "', '"
				+ state + "', '" + telephone + "')";

		try {
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected == 1) {
				JOptionPane.showMessageDialog(null, "The record " + id + " was succesfully inserted.");
				clearStaff();
			} else {
				JOptionPane.showMessageDialog(null, "Could not insert " + id + " record.");
			}

		} catch (SQLException e1) {
			// If an SQL exception occurs, display an error message based on the oracle error code, which relates to the schema of staff table
			String errMsg = e1.getLocalizedMessage();
			if (errMsg.contains("ORA-00001"))
				JOptionPane.showMessageDialog(null, "Could not insert record id " + id + " due to duplicate value");
			else if (errMsg.contains("ORA-01400"))
				JOptionPane.showMessageDialog(null, "Please enter the record id");
			else if (errMsg.contains("ORA-12899"))
				JOptionPane.showMessageDialog(null, "Could not insert the record due to the value too large for column.");
		}

	}

	public void updateStaff() {
		
		//Get the input values from GUI
		String id = txtID.getText();
		String lastName = txtlName.getText();
		String firstName = txtfName.getText();
		String mi = txtMi.getText();
		String address = txtAddress.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String telephone = txtTele.getText().replace("-","");
		
		// Check that record id is entered
		if (id == null || id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter the record id.");
			return;
		}
		// Check any other required fields are entered which are lastname, firstname, and telephone no 
		if (lastName == null || lastName.isEmpty() || firstName == null || firstName.isEmpty() || telephone == null || telephone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter the firstname, lastname and telephone.");
			return;
		}
		// Check the telephone is 10 digits long
		if (telephone.length() != 10) {
			JOptionPane.showMessageDialog(null, "Please enter the telephone no 10 digits");
			return;
		}
		
		// Construct the SQL update statement
		String sql = "UPDATE staff SET lastname = '" + lastName + "', firstname = '" + firstName + "', mi = '" + mi
				+ "', address = '" + address + "', city = '" + city + "', state = '" + state + "', telephone = '"
				+ telephone + "' WHERE id = " + id;

		try {
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected == 1) {
				JOptionPane.showMessageDialog(null, "The record " + id + " was successfully updated.");
				clearStaff();
			} else {
				JOptionPane.showMessageDialog(null, "Could not update record id " + id);
			}
		} catch (SQLException e1) {
			String errMsg = e1.getLocalizedMessage();
			if (errMsg.contains("ORA-00001"))
				JOptionPane.showMessageDialog(null, "Could not insert record id " + id + " due to duplicate value");
			else if (errMsg.contains("ORA-01400"))
				JOptionPane.showMessageDialog(null, "Please enter the record id");
			else if (errMsg.contains("ORA-12899"))
				JOptionPane.showMessageDialog(null, "Could not insert the record due to the value too large for column.");
		}
	}

	public void clearStaff() {
		// Clear all the data from text fields
		txtID.setText(null);
		txtlName.setText(null);
		txtfName.setText(null);
		txtMi.setText(null);
		txtAddress.setText(null);
		txtCity.setText(null);
		txtState.setText(null);
		txtTele.setText(null);
		txtID.setBackground(Color.white);
	}

}


/*************************************************************************************************
*  Course_Name – Assignment 4                                                                                                                                *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

*  (including web sites) or distributed to other students/social media.                                                       *
                                                                                                                                                                             
*  Name: __Amos Chew Syh Ern________ Student ID: ___N01533575_______________ Date: ___15 March 2023_______________ 
*
1.	Is your database created correctly? ____Yes________
2.	Can you create the UI? ___Yes_________
3.	Can you connect to the database? _Yes_____
4.	Is the View button implemented correctly? _Yes______________
5.	Is the Insert button implemented correctly? _Yes_______
6.	Is the Update button implemented correctly? _Yes_______
7.	Is the Clear button implemented correctly? _Yes_______
	
*/

/*************************************************************************************************
*  Course_Name – Assignment 4                                                                                                                                *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

*  (including web sites) or distributed to other students/social media.                                                       *
                                                                                                                                                                             
*  Name: __Ricardo Joaquin Hornedo Aldeco________ Student ID: ___N01538048___________ Date: ___15 March 2023_______________ 
*
1.	Is your database created correctly? ____Yes________
2.	Can you create the UI? ___Yes_________
3.	Can you connect to the database? _Yes_____
4.	Is the View button implemented correctly? _Yes______________
5.	Is the Insert button implemented correctly? _Yes_______
6.	Is the Update button implemented correctly? _Yes_______
7.	Is the Clear button implemented correctly? _Yes_______
	
*/


/*************************************************************************************************
*  Course_Name – Assignment 4                                                                                                                                *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

*  (including web sites) or distributed to other students/social media.                                                       *
                                                                                                                                                                             
*  Name: __Teerawut Sangpueng________ Student ID: ___N01547659___________ Date: ___15 March 2023_______________ 
*
1.	Is your database created correctly? ____Yes________
2.	Can you create the UI? ___Yes_________
3.	Can you connect to the database? _Yes_____
4.	Is the View button implemented correctly? _Yes______________
5.	Is the Insert button implemented correctly? _Yes_______
6.	Is the Update button implemented correctly? _Yes_______
7.	Is the Clear button implemented correctly? _Yes_______
	
*/
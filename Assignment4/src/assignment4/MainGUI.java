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
	private final String DB = "JavaDB";

	public MainGUI() {
		initializeDB();

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
		container = new JPanel();
		createFieldLegend();
		createButtonPanel();
		createStatusLabel();
		add(container);

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createFieldLegend() {
		legend = new JPanel();
		legend.setLayout(new GridBagLayout());
		legend.setBorder(new TitledBorder(new EtchedBorder(), "Staff Information"));

		GridBagConstraints gbc = new GridBagConstraints();

		// To ensure elements take up just the right amount of space
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Similar to padding in CSS, setting the spaces between each element
		gbc.insets = new Insets(5, 10, 5, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		id = new JLabel("ID: ");
		legend.add(id, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		txtID = new JTextField(10);
		legend.add(txtID, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		fName = new JLabel("First Name: ");
		legend.add(fName, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		txtfName = new JTextField(10);
		legend.add(txtfName, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		lName = new JLabel("Last Name: ");
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
		txtState = new JTextField(3);
		legend.add(txtState, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		tele = new JLabel("Telephone: ");
		legend.add(tele, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		txtTele = new JTextField(10);
		legend.add(txtTele, gbc);

		container.add(legend);
	}

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

	public void createStatusLabel() {
		status = new JPanel();
		lblstatus = new JLabel("Database Connected");
		status.add(lblstatus);
		container.add(status);
	}

	public void viewStaff() {
		String id = txtID.getText();

		String sql = "SELECT id, lastname, firstname, mi, address, city, state, telephone FROM staff WHERE id = " + id;

		try {
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				txtlName.setText(rs.getString("lastname"));
				txtfName.setText(rs.getString("firstname"));
				txtMi.setText(rs.getString("mi"));
				txtAddress.setText(rs.getString("address"));
				txtCity.setText(rs.getString("city"));
				txtState.setText(rs.getString("state"));
				txtTele.setText(rs.getString("telephone"));

			} else {
				JOptionPane.showMessageDialog(null, "ID not found");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void insertStaff() {
		String id = txtID.getText();
		String lastName = txtlName.getText();
		String firstName = txtfName.getText();
		String mi = txtMi.getText();
		String address = txtAddress.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String telephone = txtTele.getText();

		String sql = "INSERT INTO staff (id, lastname, firstname, mi, address, city, state, telephone) VALUES " + "('"
				+ id + "', '" + lastName + "', '" + firstName + "', '" + mi + "', '" + address + "', '" + city + "', '"
				+ state + "', '" + telephone + "')";

		try {
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected == 1) {
				JOptionPane.showMessageDialog(null, "The record " + id + " was succesfully inserted.");
			} else {
				JOptionPane.showMessageDialog(null, "Could not insert " + id + " record.");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void updateStaff() {
		String id = txtID.getText();
		String lastName = txtlName.getText();
		String firstName = txtfName.getText();
		String mi = txtMi.getText();
		String address = txtAddress.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String telephone = txtTele.getText();

		String sql = "UPDATE staff SET lastname = '" + lastName + "', firstname = '" + firstName + "', mi = '" + mi
				+ "', address = '" + address + "', city = '" + city + "', state = '" + state + "', telephone = '"
				+ telephone + "' WHERE id = " + id;

		try {
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected == 1) {
				JOptionPane.showMessageDialog(null, "The record " + id + " was successfully updated.");
			} else {
				JOptionPane.showMessageDialog(null, "Could not update " + id + " record.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void clearStaff() {

	}

}

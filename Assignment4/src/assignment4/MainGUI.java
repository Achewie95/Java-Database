package assignment4;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame{

	private JPanel container;
	private JPanel legend;
	private JLabel id;
	private JTextField txtID;
	private JLabel fName;
	private JTextField txtfName;
	private JLabel lName;
	private JTextField txtlName;
	private JLabel mi;
	private JTextField txtMi;
	private JLabel address;
	private JTextField txtAddress;
	private JLabel city;
	private JTextField txtCity;
	private JLabel state;
	private JTextField txtState;
	private JLabel tele;
	private JTextField txtTele;
	
	
	public MainGUI() {
		
		//setting the Frame
		setTitle("Staff Information");
		setSize(700,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		container = new JPanel();
		createFieldLegend();
		add(container);
	}
	
	public void createFieldLegend() {
		legend = new JPanel();
		legend.setLayout(new GridBagLayout());
		legend.setBorder(new TitledBorder(new EtchedBorder(), "Staff Information"));

		GridBagConstraints gbc = new GridBagConstraints();

		// To ensure elements take up just the right amount of space
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Similar to padding in CSS, setting the spaces between each element
		gbc.insets = new Insets(10, 15, 5, 20);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		id = new JLabel("ID: ");
		legend.add(id,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtID = new JTextField(10);
		legend.add(txtID,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		fName = new JLabel("First Name: ");
		legend.add(fName,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		txtfName = new JTextField(10);
		legend.add(txtfName,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		lName = new JLabel("Last Name: ");
		legend.add(lName,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		txtlName = new JTextField(10);
		legend.add(txtlName,gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 1;
		mi = new JLabel("mi: ");
		legend.add(mi,gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 1;
		txtMi = new JTextField(10);
		legend.add(txtMi,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		address = new JLabel("Address: ");
		legend.add(address,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		txtAddress = new JTextField(10);
		legend.add(txtAddress,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		city = new JLabel("City: ");
		legend.add(city,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		txtCity = new JTextField(10);
		legend.add(txtCity,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		state = new JLabel("State: ");
		legend.add(state,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		txtState = new JTextField(10);
		legend.add(txtState,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		tele = new JLabel("Telephone: ");
		legend.add(tele,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		txtTele = new JTextField(10);
		legend.add(txtTele,gbc);
		
		container.add(legend);
	}
}

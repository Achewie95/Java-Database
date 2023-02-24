package presentation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data.*;

public class MainGUI extends JFrame {

	private JPanel container;
	private JLabel recordLabel;
	public static JTextField recordTextField;
	private JLabel fnameLabel;
	public static JTextField fnameTextField;
	private JLabel lnameLabel;
	public static JTextField lnameTextField;
	private JLabel ageLabel;
	public static JTextField ageTextField;
	private JLabel phoneLabel;
	public static JTextField phoneTextField;
	private JButton addButton;
	private JButton findButton;
	private ActionListener listener;
	private JPanel buttonPanel;
	private JPanel layoutPanel;

	public MainGUI() {

		// Setting the Frame
		setTitle("Random File Processing");
		setSize(330, 310);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);

		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				JButton source = (JButton) e.getSource();
				if (source.getText().equals("Add")) {
					// call the method of RandomIO to add person
					RandomIO.addPerson();
				} else if (source.getText().equals("Find")) {
					// call the method of RandomIO to find a person
					RandomIO.findPerson();
				}
			}
		}

		listener = new ButtonListener();

		CreateFieldContainer();
		CreateButtonPanel();
		CreateLayout();

	}

	public void CreateFieldContainer() {
		// Setting the Contents within the Frame using a Panel
		container = new JPanel();
		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// To ensure elements take up just the right amount of space
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Similar to padding in CSS, setting the spaces between each element
		gbc.insets = new Insets(10, 15, 5, 20);

		// X represents the Horizontal axis, Y the vertical. From the codes below,
		// you'll be able to visualize where each element goes
		// Each element is then instantiated and added to the panel

		// adding Record
		gbc.gridx = 0;
		gbc.gridy = 0;
		recordLabel = new JLabel("Record #");
		container.add(recordLabel, gbc);

		// adding TextField
		gbc.gridx = 1;
		gbc.gridy = 0;
		recordTextField = new JTextField(10);
		container.add(recordTextField, gbc);

		// adding First Name
		gbc.gridx = 0;
		gbc.gridy = 1;
		fnameLabel = new JLabel("First Name");
		container.add(fnameLabel, gbc);

		// adding TextField
		gbc.gridx = 1;
		gbc.gridy = 1;
		fnameTextField = new JTextField(10);
		container.add(fnameTextField, gbc);

		// adding Last Name
		gbc.gridx = 0;
		gbc.gridy = 2;
		lnameLabel = new JLabel("Last Name");
		container.add(lnameLabel, gbc);

		// adding TextField
		gbc.gridx = 1;
		gbc.gridy = 2;
		lnameTextField = new JTextField(10);
		container.add(lnameTextField, gbc);

		// adding Last Name
		gbc.gridx = 0;
		gbc.gridy = 3;
		ageLabel = new JLabel("Age");
		container.add(ageLabel, gbc);

		// adding TextField
		gbc.gridx = 1;
		gbc.gridy = 3;
		ageTextField = new JTextField(10);
		container.add(ageTextField, gbc);

		// adding Phone
		gbc.gridx = 0;
		gbc.gridy = 4;
		phoneLabel = new JLabel("Phone");
		container.add(phoneLabel, gbc);

		// adding TextField
		gbc.gridx = 1;
		gbc.gridy = 4;
		phoneTextField = new JTextField(10);
		container.add(phoneTextField, gbc);
	}

	public void CreateButtonPanel() {
		// Creating add button
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 45, 30));
		addButton = new JButton("Add");
		addButton.setPreferredSize(new Dimension(95, 25));
		addButton.addActionListener(listener);
		buttonPanel.add(addButton);

		// Creating the find button
		findButton = new JButton("Find");
		findButton.setPreferredSize(new Dimension(95, 25));
		findButton.addActionListener(listener);
		buttonPanel.add(findButton);

	}

	public void CreateLayout() {
		// Creating a Panel for the layout
		layoutPanel = new JPanel();
		layoutPanel.setLayout(new BorderLayout());
		layoutPanel.add(container, BorderLayout.NORTH);
		layoutPanel.add(buttonPanel, BorderLayout.CENTER);
		add(layoutPanel);
	}
}

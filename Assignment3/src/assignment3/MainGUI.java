package assignment3;
//tom
import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame{
	
	private JPanel Top;
	private JPanel Container;
	private JLabel Record;
	private JTextField Rcd;
	private JLabel Fname;
	private JTextField First;
	private JLabel Lname;
	private JTextField Last;
	private JLabel Phone;
	private JTextField Ph;
	private JButton Add;
	private JButton Find;
	

	public MainGUI(){
		
		//Setting the Frame
		setTitle("Random File Processing");
		setSize(350,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Creating a Panel to add "Container" to the top of the Frame
		Top = new JPanel();
		Top.setLayout(new BorderLayout());
		
		
		//Setting the Contents within the Frame using a Panel
		Container = new JPanel();
		Container.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();

		// To ensure elements take up just the right amount of space
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Similar to padding in CSS, setting the spaces between each element
		gbc.insets = new Insets(20, 15, 5, 10);
		
		// X represents the Horizontal axis, Y the vertical. From the codes below,
		// you'll be able to visualize where each element goes
		// Each element is then instantiated and added to the panel
		
		//adding Record
		gbc.gridx = 0;
		gbc.gridy = 0;
		Record = new JLabel("Record #");
		Container.add(Record,gbc);
		
		//adding TextField
		gbc.gridx = 1;
		gbc.gridy = 0;
		Rcd = new JTextField(15);
		Container.add(Rcd,gbc);
		

		//adding First Name
		gbc.gridx = 0;
		gbc.gridy = 1;
		Fname = new JLabel("First Name");
		Container.add(Fname, gbc);
		
		//adding TextField
		gbc.gridx = 1;
		gbc.gridy = 1;
		First = new JTextField(15);
		Container.add(First,gbc);
		
		//adding Last Name
		gbc.gridx = 0;
		gbc.gridy = 2;
		Lname = new JLabel("Last Nme");
		Container.add(Lname,gbc);
		
		//adding TextField
		gbc.gridx = 1;
		gbc.gridy = 2;
		Last = new JTextField(15);
		Container.add(Last,gbc);
		
		//adding Phone
		gbc.gridx = 0;
		gbc.gridy = 3;
		Phone = new JLabel("Phone");
		Container.add(Phone,gbc);
		
		//adding TextField
		gbc.gridx = 1;
		gbc.gridy = 3;
		Ph = new JTextField(15);
		Container.add(Ph,gbc);
		
		//adding Add button
		gbc.gridx = 0;
		gbc.gridy = 4;
		Add = new JButton("Add");
		Container.add(Add,gbc);
		
		//adding Find
		gbc.gridx = 1;
		gbc.gridy = 4;
		Find = new JButton("Find");
		Container.add(Find, gbc);
		
		Top.add(Container, BorderLayout.NORTH);
		add(Top);
		
	};

}

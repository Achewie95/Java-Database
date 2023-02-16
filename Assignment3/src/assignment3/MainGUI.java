package assignment3;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame{
	
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
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//Setting the Contents within the Frame using a Panel
		Container = new JPanel();
		Container.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();

		// To ensure elements take up just the right amount of space
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Similar to padding in CSS, setting the spaces between each element
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// X represents the Horizontal axis, Y the vertical. From the codes below,
		// you'll be able to visualize where each element goes
		// Each element is then instantiated and added to the panel
		
		
	};

}

package assignment4;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame{

	private JPanel container;
	private JPanel legend;
	private JLabel titleLabel;
	
	public MainGUI() {
		
		//setting the Frame
		setTitle("Staff Information");
		setSize(500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		
		createFieldLegend();
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
		
		add(legend);
		
		
	
	}
}

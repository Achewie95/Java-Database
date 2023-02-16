package assignment3;

import java.awt.*;

public class Assignment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(() -> {
			
			// Creating an instance and initializing the frame, called from ProductMainGUI class
			MainGUI frame = new MainGUI();
			frame.setVisible(true);
		});
	}
}

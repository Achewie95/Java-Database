package assignment6;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class TaskThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JTextArea ta = new JTextArea();
		
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(3);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Concurrent Output");
		
		
		// Create a task
		Runnable printa = new PrintChar('a', 50, ta);
		Runnable printb = new PrintChar('b', 50, ta);
		Runnable print100 = new PrintNum(100, ta);
		ta.setLineWrap(true);
		frame.add(ta);
		
		// Create a thread

		Thread thread1 = new Thread(printa);
		Thread thread2 = new Thread(printb);
		Thread thread3 = new Thread(print100);

		thread1.setPriority(Thread.MAX_PRIORITY);

		thread1.start();
		thread2.start();
		thread3.start();
		
		//frame.add(panel);
		frame.setVisible(true);
	}

}

class PrintChar implements Runnable {
	private char CharToPrint;
	private int times;
	private JTextArea ta;
	

	// Construct a task
	public PrintChar(char c, int t, JTextArea ta) {
		CharToPrint = c;
		times = t;
		this.ta = ta;
	}

	@Override
	public void run() {
		String result = "";
		for (int i = 0; i < times; i++) {
			
			result += " " + CharToPrint;
		}
		ta.append(result);

	}
}

class PrintNum implements Runnable {
    private int lastNum;
    private JTextArea ta;

    public PrintNum(int n, JTextArea ta) {
        lastNum = n;
        this.ta = ta;
    }

    @Override
    public void run() {
        String result = "";
        for (int n = 0; n < lastNum; n++) {
            result += " " + n;
        }
        ta.append(result);
    }
}

package assignment6;

import java.util.HashSet;
import java.util.Iterator;

public class Question3 {

	// Declare a static HashSet to later fill with integers
	public static HashSet<Integer> hashSet = new HashSet<>();

	public static void main(String[] args) {
		// Create the first Thread
		new Thread1();
		// Create the second Thread
		new Thread2();
	}

	// Declare a static inner class for the first Thread, to implements the Runnable interface
	static class Thread1 implements Runnable {

		// Thread1 constructor
		public Thread1() {
			// Create a new Thread object with this Runnable object
			Thread thread = new Thread(this);
			// Start the thread
			thread.start();
		}

		@Override
		// Implement the run() method from the Runnable interface
		public void run() {
			// Loop from 0 to 9999
			for (int i = 0; i < 10000; i++) {
				// Add each number to the hashSet
				hashSet.add(i);
			}
			try {
				// Pause the thread for 1 second
				Thread.sleep(1000);
				// Print the stack trace of the Interrupted Exception occurs.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Declare a static inner class named Thread2 that implements the Runnable interface
	static class Thread2 implements Runnable {

		// Thread2 constructor
		public Thread2() {
			// Create a new Thread object with this Runnable object
			Thread thread = new Thread(this);
			// Start the thread
			thread.start();
		}

		// Implement the run() method from the Runnable interface
		@Override
		public void run() {
			try {
				// Loop indefinitely
				while (true) {
					// Loop through each element in hashSet
					for (Iterator<Integer> iterator = hashSet.iterator(); iterator.hasNext();) {
						// Access the next element in hashSet
						iterator.next();
					}

					// Pause the thread for 1 second
					Thread.sleep(1000);
				}
				// Print the stack trace of the Interrupted Exception occurs.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

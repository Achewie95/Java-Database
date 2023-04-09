package assignment6;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Question4 {
    
    // Use synchronizedSet method to create a synchronized HashSet
    public static Set<Integer> hashSet = Collections.synchronizedSet(new HashSet<Integer>());
    
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
                // Add each number to the synchronized hashSet
                hashSet.add(i);
                try {
                    // Pause the thread for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class Thread2 implements Runnable {
        
        // Thread2 constructor
        public Thread2() {
            // Create a new Thread object with this Runnable object
            Thread thread = new Thread(this);
            // Start the thread
            thread.start();
        }
        
        @Override
        // Implement the run() method from the Runnable interface
        public void run() {
            try {
                while (true) {
                    // Print the size of the synchronized hashSet
                    System.out.println(hashSet.size());
                    // Synchronize the hashSet to prevent modification while iterating over it
                    synchronized (hashSet) {
                        // Loop through each element in the synchronized hashSet
                        for (Iterator<Integer> iterator = hashSet.iterator(); iterator.hasNext();) {
                            // Access the next element in the synchronized hashSet and print it
                            System.out.print(iterator.next()+ " "); 
                        }
                    }
                    // Pause the thread for 1 second
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package assignment6;
import java.util.concurrent.*;

public class question2 {

	private static Account account = new Account();
	private static Account account2 = new Account();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 2000; i++) {
			executor.execute(new AddAPennyTask());
		}
		
		for (int i = 0; i < 2000; i++) {
			executor.execute(new AddAPennyTask2());
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("(With Synchronization) What is balance? " + account.getBalance());
		System.out.println("(Without Synchronization) What is balance? " + account2.getBalance());
	}

	private static class AddAPennyTask implements Runnable {
		public void run() {
			synchronized (account) {
				account.deposit(1);
			}
		}
	}

	private static class AddAPennyTask2 implements Runnable {
		public void run() {
			account2.deposit(1);
		}
	}


	private static class Account {
		private int balance = 0;

		public int getBalance() {
			return balance;
		}

		public void deposit(int amount) {
			int newBalance = balance + amount;
			balance = balance + amount;
			balance = newBalance;
		}
	}
	

}

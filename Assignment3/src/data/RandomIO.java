package data;

import business.*;
import presentation.*;
import assignment3.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class RandomIO {

	public static Person person;

	// Method to add person to the binary file.
	public static void addPerson() {
		try {
			RandomAccessFile file = new RandomAccessFile(AppConstants.FILE_NAME, "rw");

			// Move the file pointer to the end of the file
			file.seek(file.length());

			int recordId = Integer.parseInt(MainGUI.recordTextField.getText().trim());
			String firstName = MainGUI.fnameTextField.getText().trim();
			String lastName = MainGUI.lnameTextField.getText().trim();
			int age = Integer.parseInt(MainGUI.ageTextField.getText().trim());
			String phone = MainGUI.phoneTextField.getText().trim();

			// Validate first name, last name and age
			if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || age <= 0) {
				JOptionPane.showMessageDialog(null, "Invalid input. Please provide valid information!");
				clearValue();
				file.close();
				return;
			}

			// validate first name, last name , phone no should have length less than x
			// characters
			if (firstName.length() <= AppConstants.MAX_FIRST_NAME_SIZE
					&& lastName.length() <= AppConstants.MAX_LAST_NAME_SIZE
					&& phone.length() <= AppConstants.MAX_PHONE_SIZE) {
				// Assign 5 variables to object person
				person = new Person(recordId, firstName, lastName, age, phone);
			} else {
				JOptionPane.showMessageDialog(null, "The input provided is too long, please try with a shorter input");
				clearValue();
				file.close();
				return;
			}

			// Create a byte array with the data to write, which get data from object person
			byte[] fname = person.getFirstName().getBytes("UTF-8");
			byte[] lname = person.getLastName().getBytes("UTF-8");
			byte[] phoneNo = person.getPhone().getBytes("UTF-8");

			// Write the data for the new person to the file
			file.writeInt(person.getRecordId());
			file.write(Arrays.copyOfRange(fname, 0, AppConstants.MAX_FIRST_NAME_SIZE));
			file.write(Arrays.copyOfRange(lname, 0, AppConstants.MAX_LAST_NAME_SIZE));
			file.writeInt(person.getAge());
			file.write(Arrays.copyOfRange(phoneNo, 0, AppConstants.MAX_PHONE_SIZE));

			JOptionPane.showMessageDialog(null, "Your record id was succesfully added.");
			clearValue();

			file.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "While retrieving the data, an error ocurred.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid! Please enter a valid input.");
			return;
		}
	}

	public static int size(RandomAccessFile file) throws IOException {
		return (int) (file.length() / AppConstants.RECORD_SIZE);
	}

	// Method to find a person based on record number
	public static void findPerson() {
		try {
			RandomAccessFile file = new RandomAccessFile(AppConstants.FILE_NAME, "r");

			int recordId = Integer.parseInt(MainGUI.recordTextField.getText());
			String firstName;
			String lastName;
			int age;
			String phoneNo;

			// Clear all values when clicking Find button
			clearValue();

			for (int i = 0; i < size(file); i++) {

				// calculate the position of the record in the file
				long position = i * AppConstants.RECORD_SIZE;

				// move the file pointer to the position of the record
				file.seek(position);

				// get record id
				int id = file.readInt();

				// compare id between user's input and id in a file
				if (id == recordId) {

					// reads up to 20 bytes from a file into a byte array fname.
					byte[] fname = new byte[AppConstants.MAX_FIRST_NAME_SIZE];
					file.read(fname);
					// converts bytes to a String and trimming any leading or trailing whitespace
					firstName = new String(fname).trim();

					// reads up to 25 bytes from a file into a byte array lname.
					byte[] lname = new byte[AppConstants.MAX_LAST_NAME_SIZE];
					file.read(lname);
					// converts bytes to a String and trimming any leading or trailing whitespace
					lastName = new String(lname).trim();

					age = file.readInt();

					// reads up to 25 bytes from a file into a byte array phone.
					byte[] phone = new byte[AppConstants.MAX_PHONE_SIZE];
					file.read(phone);
					// converts bytes to a String and trimming any leading or trailing whitespace
					phoneNo = new String(phone).trim();

					// Display on the GUI
					MainGUI.recordTextField.setText(Integer.toString(id));
					MainGUI.fnameTextField.setText(firstName);
					MainGUI.lnameTextField.setText(lastName);
					MainGUI.ageTextField.setText(Integer.toString(age));
					MainGUI.phoneTextField.setText(phoneNo);

				}

			}

			file.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "While retrieving the data, an error ocurred.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid !!!. Please enter the valid values");
			return;
		}
	}

	public static void clearValue() {
		MainGUI.recordTextField.setText(null);
		MainGUI.fnameTextField.setText(null);
		MainGUI.lnameTextField.setText(null);
		MainGUI.ageTextField.setText(null);
		MainGUI.phoneTextField.setText(null);
	}

}
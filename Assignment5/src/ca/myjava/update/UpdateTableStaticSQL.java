package ca.myjava.update;

//Importing the libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.myjava.query.AppConstants;

public class UpdateTableStaticSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Loading the Oracle driver class
			Class.forName(AppConstants.DRIVER_CLASS_ORACLE);
			// Print message to console if driver loaded successfully
			System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
			// Print error stack trace if driver class not found
			e.printStackTrace();
		}
		// Printing messages to console to explain the changes
		System.out.println("This program will delete the last row of the countries table:");
		System.out.println();
		System.out.println("This is how it looks before the deletion:");
		// Call to method to show the current state of the countries table
		displayCountries();
		System.out.println("This is how it looks after the deletion:");
		// Call to method to delete the last row of the countries table
		deleteLastRow();
		// Call to method to show the final state of the countries table
		displayCountries();
	}

	// Method to delete the last row from the countries table
	public static void deleteLastRow() {
		// Initialize the database connection and statement objects to null
		Connection conn = null;
		Statement stmt = null;
		try {
			// Creating a connection to the database
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			// Creating a statement to execute SQL queries on the database
			stmt = conn.createStatement();
			// SQL query to delete the last row of the countries table
			String sql = "DELETE FROM countries_ WHERE countryid = (SELECT MAX(countryid) FROM countries_)";
			// Executing the SQL query
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle any SQL exceptions by printing the stack trace
			se.printStackTrace();
		} catch (Exception e) {
			// Handle any other exceptions by printing the stack trace
			e.printStackTrace();
		} finally {
			try {
				// Close the statement and connection objects in a finally block
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				// Print error stack trace if closing objects throws an SQL exception
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Create a method to display the countries table in the database
	public static void displayCountries() {
		// Initialize the database connection and statement objects to null
		Connection conn = null;
		Statement stmt = null;
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			// Define a SQL query to select all fields from the countries table
			String sql = "SELECT countryid, countryname, lifeexpectancy, countryregion, population FROM countries_";
			// Create a statement object to execute the SQL query
			stmt = conn.createStatement();
			// Execute the SQL query and get the result set
			ResultSet rs = stmt.executeQuery(sql);

			// Display the countries table with a formatted output
			System.out.println();
			System.out.println("Countries:");
			System.out.println("|-----------------------------------------------------------------------------------|");
			System.out.printf("| %-12s | %-15s | %-15s | %-15s | %-12s |\n", "COUNTRY ID", "COUNTRY NAME",
					"LIFE EXPECTANCY", "COUNTRY REGION", "POPULATION");
			System.out.println("|-----------------------------------------------------------------------------------|");

			while (rs.next()) {
				System.out.printf("| %-12s | %-15s | %-15s | %-15s | %-12s |\n", rs.getString("countryid"),
						rs.getString("countryname"), rs.getString("lifeexpectancy"), rs.getString("countryregion"),
						rs.getString("population"));
				System.out.println(
						"|-----------------------------------------------------------------------------------|");
			}

		} catch (SQLException se) {
			// Handle any SQL exceptions by printing the stack trace
			se.printStackTrace();
		} catch (Exception e) {
			// Handle any other exceptions by printing the stack trace
			e.printStackTrace();
		} finally {
			try {
				// Close the statement and connection objects in a finally block
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// Handle any SQL exceptions by printing the stack trace
				e.printStackTrace();
			}
		}
	}

	

}

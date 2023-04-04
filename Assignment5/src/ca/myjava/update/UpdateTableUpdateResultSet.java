package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ca.myjava.query.AppConstants;

public class UpdateTableUpdateResultSet {

	public static void main(String[] args) {
		// Attempt to load the Oracle driver class
		try {
			Class.forName(AppConstants.DRIVER_CLASS_ORACLE);
			System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Call the method to display the list of countries
		displayCountries();

		// Call the method to insert a new country
		insertCountry();

		// Call the method again to display the updated list of countries
		displayCountries();
	}

	// Inserting a new country into the database by getting input from the user
	public static void insertCountry() {
		// Initialize the database connection and statement objects to null
		Connection conn = null;
		Statement stmt = null;
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			System.out.println("Connection to DB is established successfully!");
			// Create a scanner object to get user input
			Scanner kbd = new Scanner(System.in);

			System.out.println();
			System.out.println("To include a new country in the database, add the following information:");

			// Get the country name from the user
			System.out.println("Country Name:");
			String name = kbd.next();

			// Get the life expectancy from the user
			System.out.println("Life Expectancy:");
			int lifeExpectancy = kbd.nextInt();

			// Get the country region from the user
			System.out.println("Country Region:");
			kbd.nextLine();
			String region = kbd.next();

			// Get the population from the user
			System.out.println("Population:");
			int population = kbd.nextInt();
			kbd.close();

			// Define an SQL query to insert the new country into the database
			String sql = String
					.format("INSERT INTO countries_ (countryname, lifeexpectancy, countryregion, population) "
							+ "VALUES ('%s', %d, '%s', %d)", name, lifeExpectancy, region, population);

			// Create a statement object to execute the SQL query
			stmt = conn.createStatement();
			// Execute the SQL query to insert the new country into the database
			stmt.executeUpdate(sql);

			System.out.println("Updated table:");

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

package ca.myjava.update;
//import libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ca.myjava.query.AppConstants;

public class UpdateTablePreparedStm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName(AppConstants.DRIVER_CLASS_ORACLE);
			System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		//Display countries table
		displayCountries();
		//Update the life Expectancy of one row
		updateLifeExpectancy();
		//Display updated version
		displayCountries();
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
	// Create a method to update life expectancy
	public static void updateLifeExpectancy() {
		// Initialize the database connection and statement objects to null
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			// Define a SQL query to update from the countries table
			String sql = "UPDATE countries_ SET lifeexpectancy = ? WHERE countryid = ?";
			pstmt = conn.prepareStatement(sql);

			// Get user input
			 Scanner kbd = new Scanner(System.in);
			 System.out.println();
			 System.out.println("To update the life expectancy, please enter the country id: ");
			 int countryID = kbd.nextInt();
			 System.out.println("Please enter max LifeExpectancy: ");
			 int lifeExpectancy = kbd.nextInt();
			 kbd.close();

			 // Set parameter values for prepared statement
			 pstmt.setInt(1, lifeExpectancy);
			 pstmt.setInt(2, countryID);

			 // Execute query
			 resultSet = pstmt.executeQuery();
		} catch (SQLException se) {
			// Handle any SQL exceptions by printing the stack trace
			se.printStackTrace();
		} catch (Exception e) {
			// Handle any other exceptions by printing the stack trace
			e.printStackTrace();
		} finally {
			try {
				// Close the statement and connection objects in a finally block
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// Handle any SQL exceptions by printing the stack trace
				e.printStackTrace();
			}
		}
	}

}

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
		// TODO Auto-generated method stub
		try {
			Class.forName(AppConstants.DRIVER_CLASS_ORACLE);
			System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		displayCountries();
		insertCountry();
		displayCountries();
	}
	public static void insertCountry() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			System.out.println("Connection to DB is established successfully!");
			Scanner kbd = new Scanner(System.in);

			System.out.println();
			System.out.println("To include a new country in the database, add the following information:");

			System.out.println("Country Name:");
			String name = kbd.next();

			System.out.println("Life Expectancy:");
			int lifeExpectancy = kbd.nextInt();

			System.out.println("Country Region:");
			kbd.nextLine();
			String region = kbd.next();

			System.out.println("Population:");
			int population = kbd.nextInt();
			kbd.close();

			String sql = String
					.format("INSERT INTO countries_ (countryname, lifeexpectancy, countryregion, population) "
							+ "VALUES ('%s', %d, '%s', %d)", name, lifeExpectancy, region, population);

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			System.out.println("Updated table:");

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void displayCountries() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			String sql = "SELECT countryid, countryname, lifeexpectancy, countryregion, population FROM countries_";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

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
				System.out.println("|-----------------------------------------------------------------------------------|");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

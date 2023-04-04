package ca.myjava.update;

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
			Class.forName(AppConstants.DRIVER_CLASS_ORACLE);
			System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("This program will delete the last row of the countries table:");
		System.out.println();
		System.out.println("This is how it looks before the deletion:");
		displayCountries();
		System.out.println("This is how it looks after the deletion:");
		deleteLastRow();
		displayCountries();
	}

	public static void deleteLastRow() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			stmt = conn.createStatement();
			String sql = "DELETE FROM countries_ WHERE countryid = (SELECT MAX(countryid) FROM countries_)";
			stmt.executeUpdate(sql);

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

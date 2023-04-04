package ca.myjava.update;

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

		displayCountries();
		updateLifeExpectancy();
		displayCountries();
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

	public static void updateLifeExpectancy() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.RICARDO_U, AppConstants.P);
			String sql = "UPDATE countries_ SET lifeexpectancy = ? WHERE countryid = ?";
			pstmt = conn.prepareStatement(sql);

			// get user info
			Scanner kbd = new Scanner(System.in);
			System.out.println();
			System.out.println("To update the life expectancy, please enter the country id: ");
			int countryID = kbd.nextInt();
			System.out.println("Please enter max LifeExpectancy: ");
			int lifeExpectancy = kbd.nextInt();
			kbd.close();

			pstmt.setInt(1, lifeExpectancy);
			pstmt.setInt(2, countryID);

			// execute Query
			resultSet = pstmt.executeQuery();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

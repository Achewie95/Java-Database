package ca.myjava.query;

import java.sql.*;
import java.util.Scanner;


public class QueryTableStaticSQL {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		//Load the driver
		Class.forName(AppConstants.DRIVER_CLASS_MYSQL);
		System.out.println("Driver is loaded");
		
		//Establish connection
		conn = DriverManager.getConnection(AppConstants.MySQLURL, 
				AppConstants.MYSQLU ,AppConstants.password);
		System.out.println("Database is connected");
		
		//creating a prepared statement
		
		System.out.println("Creating a prepared statement");
		String sql1 = "SELECT COUNTRYID, COUNTRYNAME, LIFEEXPECTANCY, COUNTRYREGION, POPULATION "
				+ "FROM COUNTRIES WHERE LIFEEXPECTANCY BETWEEN ? AND ? ORDER BY 3 DESC";
		
		pstmt = conn.prepareStatement(sql1);
		
		//get user info
		Scanner scn = new Scanner(System.in);
		System.out.println("Please enter min LifeExpectancy: ");
		int minExp = scn.nextInt();
		System.out.println("Please enter max LifeExpectancy: ");
		int maxExp = scn.nextInt();
		scn.close();
		
		pstmt.setInt(1, minExp);
		pstmt.setInt(2, maxExp);
		
		//execute Query
		rset = pstmt.executeQuery();

		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-9s | %-14s | %-15s | %-14s | %-11s |\n", "COUNTRYID",
				"COUNTRYNAME", "LIFEEXPECTANCY", "COUNTRYREGION", "POPULATION");
		System.out.println("");
		while (rset.next()) {
				System.out.printf("| %-9s | %-14s | %-15s | %-14s | %-11s |\n", rset.getString("COUNTRYID"), 
						rset.getString("COUNTRYNAME"), rset.getString("LIFEEXPECTANCY"), 
						rset.getString("COUNTRYREGION"), rset.getString("POPULATION"));
		}	
		
		conn.close();
	}

}

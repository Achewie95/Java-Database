package ca.myjava.unknown;

import java.sql.*;
import java.util.Scanner;

public class AnySQL {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// load the driver
			Class.forName(AppConstants.DRIVER_CLASS_ORACLE);
			System.out.println("Driver is loaded");
			
			//create the connection
			conn = DriverManager.getConnection(AppConstants.URL, AppConstants.U, AppConstants.P);
			System.out.println("Connected database successfully ...");
				
			// Prompt the user to enter a SQL command
			Scanner inputReader = new Scanner(System.in);
			
            System.out.print("Enter a SQL command (select,insert,update,delete) : ");
            String sql = inputReader.nextLine();
			inputReader.close();
            
			// Create a statement object
			pstmt = conn.prepareStatement(sql);

			// Execute the SQL command
            boolean hasResultSet = pstmt.execute(sql);
         
            // If the SQL query returns a ResultSet, print the results to the console
            if (hasResultSet) {
            	rset = pstmt.getResultSet();
                ResultSetMetaData metaData = rset.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                // Print the column names to the console
                System.out.print("[Header]:");
                for (int i = 1; i <= columnCount; i++) {	
                    System.out.print(metaData.getColumnName(i) + "|");
                }
                System.out.println();
                
                // Print the results to the console
                while (rset.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rset.getString(i) + "|");
                    }
                    System.out.println();
                }
            } else {
                // If the SQL query does not return a ResultSet, print the number of rows affected
                int updateCount = pstmt.getUpdateCount();
                System.out.println(updateCount + " rows affected.");
            }
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// close the connection in the finally block
		finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {	
		}	
	}
	}
}

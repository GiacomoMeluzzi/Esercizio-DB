package lepackage.enterprise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
	public static ConnectionSingleton instance = null;
	private String connectionString = "jdbc:oracle:thin:@192.168.100.37:1521/pdb1";
	private Connection con = null;

	private ConnectionSingleton() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(connectionString, "u18", "u18");
			System.out.println("Connesso al database.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (con != null) {
				System.out.println("Disconnesso dal database.");
			con.close(); 
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ConnectionSingleton getInstance () {
		if (instance == null) {
			instance = new ConnectionSingleton();
		}
		return instance;
	}

	public Connection getCon() {
		return con;
	}
	
}

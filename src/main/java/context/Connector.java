package context;

import java.sql.*;

public class Connector {
	private static  String JDBC_URL = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;encrypt=true;trustServerCertificate=true;databaseName=store";
	private static  String USER = "sa";
	private static  String PASSWORD = "123";

	public static Connection getConnection() {
		Connection con= null;
		try {
			con= DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
}

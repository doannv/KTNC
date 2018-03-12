package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static Connection con = null;
	
	    public static Connection getConnection() {
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String url ="jdbc:sqlserver://localhost:1433;databaseName=myapp";
	            String user = "sa";
	            String pass = "123456";
	            con = DriverManager.getConnection(url, user, pass);
	        } catch (ClassNotFoundException | SQLException e) {
	        }
	        return con;
	    }
	    public static Connection getConnection(String port) {
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String url ="jdbc:sqlserver://localhost:"+port+";databaseName=myapp";	            
	            String user = "sa";
	            String pass = "123456";
	            return DriverManager.getConnection(url, user, pass);
	        } catch (ClassNotFoundException | SQLException e) {
	        }
	        return con;
	    }
	public static void main(String[] args) {
		
	}

}

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	public static Connection con = null;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Filehide?useSSL=false", "root", "tiger");
            
            
            
            
           // Class.forName("org.postgresql.Driver");
          //  String url="jdbc:postgresql://localhost:5432/FileHide";
		//	String user="postgres";
		//	String password="tiger";
			
			//con = DriverManager.getConnection(url,user,password);
            
        
            
            
            
            
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("Connection Success");
        return con;
    }
    public static void closeConnection() {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

}
    
}

package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
     private static final String URL = "jdbc:postgresql://localhost:5432/rodrigo_foto";
    private static final String USER = "postgres";
    private static final String PASS = "mcs.hs";
    
    public static Connection conectar(){
        try {
            Connection con;
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            con = DriverManager.getConnection(URL, USER, PASS);
            
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}

import java.sql.*;

public class Demo {
    public static void main(String[] args) throws SQLException {
    	String url = "jdbc:postgresql://localhost:5432/Demo"; 
    	String username = "postgres";
    	String password = "test";
    		
       Connection con = DriverManager.getConnection(url, username, password);
       String sql = "select name from friends where village = 'Pasupugallu'";
       Statement st = con.createStatement();
       ResultSet r = st.executeQuery(sql);
       while(r.next()) {
    	   System.out.println(r.getString("name"));
       }
      
    }
}

/*
 * import packages
 * load the driver
 * create connection
 * create statement
 * execute statement
 * close the connnection
 */

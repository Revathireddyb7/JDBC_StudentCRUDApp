
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Ex {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		//url,hostname,password; jdbc:postgres://localhost:5432/databasename,hostname,password;
		//jdbc:postgresql://localhost:5432/Demo
		String url = "jdbc:postgresql://localhost:5432/Demo";
		String hostName = "postgres";
		String password = "test";
		Connection con = DriverManager.getConnection(url,hostName,password);
		String sql = "select * from friends";
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery(sql);
		while(r.next()) {
			System.out.println(r.getString("name"));
		}
		
		

	}

}

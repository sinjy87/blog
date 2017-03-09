package spring.utility.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOpen {

	public static Connection open() {
		Connection con = null;
		
		try {
			Class.forName(Constant.driver);
			
			con= DriverManager.getConnection(Constant.url, Constant.user, Constant.password);			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
				
		return con;
	}

}

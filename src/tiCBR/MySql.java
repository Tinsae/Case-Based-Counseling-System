package tiCBR;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySql {
	public static Connection db = null;

	public static void init() {
		System.out.println("\nInitializing Mysql...");

		try {
			String uid = "root";
			String pwd = "";
			String db_name = "fieldrecom";
			String url = "jdbc:mysql://localhost/" + db_name;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			db = DriverManager.getConnection(url, uid, pwd);
			//System.out.println("\nDB connection successful for database \""
			//		+ db_name + "\"");

		} catch (Exception e) {
			System.err.println("DB connection failed: " + e.getMessage());

		}
	}

	public static void close() {

		if (db != null) {
			try {
				db.close();
				System.out.println("DB connection closed.");
			} catch (Exception e) {
				System.err.println("DB connection close error: "
						+ e.getMessage());
			}

		}

	}

}
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String URL = "jdbc:mysql://localhost:3306/task_db2026";	//データベースのURL
	private static final String USER = "admin2026";								//データベースのユーザー名
	private static final String PASSWORD = "admin2026";							//データベースのパスワード
	
	/**
	 * URL、ユーザー名、パスワードを使ってデータベースにログインします。
	 * @return 接続できたかどうかの結果
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}

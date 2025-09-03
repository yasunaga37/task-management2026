package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entity.User;

public class UserDAO {
	
	/**
	 * ユーザーIDとパスワードが一致ユーザーを取得する
	 * @param userId
	 * @param password
	 * @return User ログイン中のユーザー、ログイン中のユーザーがいない場合はnull
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean login(String userId, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM m_user WHERE user_id = ? AND password = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//プレースホルダの値の設定
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			//SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				User user = new User();
				user.setId(userId);
				user.setPassword(password);
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * ユーザーIDからユーザー情報を取得する
	 * @param userId
	 * @return User
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public User searchById(String userId) throws ClassNotFoundException, SQLException {
		User user = null;
		String sql = "SELECT * FROM m_user WHERE user_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//プレースホルダの値の設定
			pstmt.setString(1, userId);
			//SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				String id = res.getString("user_id");
				String pasword = res.getString("password");
				String name = res.getString("user_name");
				Timestamp updateDatatime = res.getTimestamp("update_datetime");
				user = new User(id, pasword, name, updateDatatime);
			}
		}
		return user;
	}
	
	/**
	 * すべてのユーザー情報を取得する
	 * @return List<User>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<User> selectAll() throws ClassNotFoundException, SQLException {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM m_user";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				String id = res.getString("user_id");
				String password = res.getString("password");
				String name = res.getString("user_name");
				Timestamp updateDatatime = res.getTimestamp("update_datetime");
				User user = new User(id, password, name, updateDatatime);
				list.add(user);
			}
		}
//		System.out.println(list.size());
		return list;
	}

}

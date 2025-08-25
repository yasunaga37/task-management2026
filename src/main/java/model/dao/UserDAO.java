package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.User;

public class UserDAO {
	
	public User login(String userId, String password) throws ClassNotFoundException, SQLException {
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
				return user;
			}
		}
		return null;		
	}

}

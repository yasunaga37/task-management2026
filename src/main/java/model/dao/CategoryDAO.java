package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entity.Category;

public class CategoryDAO {
	
	/**
	 * すべてのカテゴリ情報を取得する
	 * @return List<Category>
	 */
	public List<Category> selectAll(){
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT * FROM m_category";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				int id = res.getInt("category_id");
				String name = res.getString("category_name");
				Timestamp updateDatatime = res.getTimestamp("update_datetime");
				Category category = new Category(id, name, updateDatatime);
				list.add(category);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(list.size());
		return list;
	}
	
	public List<Category> searchByID(int categoryId){
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT * FROM m_category WHERE category_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int id = res.getInt("category_id");
				String name = res.getString("category_name");
				Timestamp updateDatatime = res.getTimestamp("update_datetime");
				Category category = new Category(id, name, updateDatatime);
				list.add(category);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(list.size());
		return list;
	}

}

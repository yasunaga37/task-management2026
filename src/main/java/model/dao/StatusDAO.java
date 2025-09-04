package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entity.Status;

public class StatusDAO {
	
	/**
	 * すべてのステータス情報を取得する
	 * @return List<Status>
	 */
	public List<Status> selectAll() {
		List<Status> list = new ArrayList<Status>();
		String sql = "SELECT * FROM m_status";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				String code = res.getString("status_code");
				String name = res.getString("status_name");
				Timestamp updateDatatime = res.getTimestamp("update_datetime");
				Status status = new Status(code, name, updateDatatime);
				list.add(status);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(list.size());
		return list;
	}

}

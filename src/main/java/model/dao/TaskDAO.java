package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Task;

public class TaskDAO {
	
	public List<Task> selectAll() throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_name, "
				+ "  c.category_name,"
				+ "  u.user_name,"
				+ "  s.status_name,"
				+ "  t.limit_date,"
				+ "  t.memo, "
				+ "  t.delete_flag "
				+ "FROM t_task t "
				+ "  INNER JOIN m_category c "
				+ "    ON t.category_id = c.category_id "
				+ "  INNER JOIN m_user u "
				+ "    ON t.user_id = u.user_id "
				+ "  INNER JOIN m_status s "
				+ "    ON t.status_code = s.status_code ";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");
				
				Task task = new Task();
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatuName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		} 
		System.out.println(list.size());
		return list;
	}

}

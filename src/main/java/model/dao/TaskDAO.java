package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entity.Task;

public class TaskDAO {

	/**
	 * タスクリストページ表示用の全タスクリストを取得する
	 * @return List<Task> 全タスク情報のリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Task> selectAll() throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_id, "
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
				+ "    ON t.status_code = s.status_code "
				+ "  ORDER BY t.task_id";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");

				Task task = new Task();
				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		}
		//		System.out.println(list.size());
		return list;
	}

	/**
	 * タスクリストページ表示用のユーザー別タスクリストを取得する
	 * @return List<Task> 指定されたユーザーIDの全タスク情報リスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Task> searchTaskByUserId(String userId) throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_id, "
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
				+ "    ON t.status_code = s.status_code "
				+ " WHERE u.user_id = ?"
				+ "  ORDER BY t.task_id";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");

				Task task = new Task();
				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		}
		//		System.out.println(list.size());
		return list;
	}

	/**
	 * タスクリストページ表示用に指定された状況に該当するタスクリストを取得する
	 * @param statusCode
	 * @return List<Task> 指定された状況に該当するタスクリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Task> searchTaskByStatusCode(String statusCode) throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_id, "
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
				+ "    ON t.status_code = s.status_code "
				+ " WHERE s.status_code= ?"
				+ "  ORDER BY t.task_id";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, statusCode);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");

				Task task = new Task();
				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		}
		return list;
	}
	
	/**
	 * タスクリストページ表示用に指定されたカテゴリに該当するタスクリストを取得する
	 * @param categoryId
	 * @return List<Task> 指定されたカテゴリに該当するタスクリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Task> searchTaskByCategoryId(int categoryId) throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_id, "
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
				+ "    ON t.status_code = s.status_code "
				+ " WHERE c.category_id= ?"
				+ "  ORDER BY t.task_id";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");

				Task task = new Task();
				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		}
//		System.out.println(list.size());
		return list;
	}
	
	/**
	 * 全タスクリストを期限で昇順ソートする。
	 * @return List<Task> 期限を昇順ソートした全タスクリスト一覧
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Task> orderByLimitDateASC() throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_id, "
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
				+ "    ON t.status_code = s.status_code "
				+ "  ORDER BY t.limit_date";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");

				Task task = new Task();
				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		}
		return list;
	}
	
	/**
	 * 期限をキーにして並び替えたタスク一覧を取得する
	 * @return List<Task> 期限をキーにして並び替えたタスク一覧
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Task> orderByLimitDateDESC() throws ClassNotFoundException, SQLException {
		List<Task> list = new ArrayList<Task>();
		String sql = "SELECT"
				+ "  t.task_id, "
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
				+ "    ON t.status_code = s.status_code "
				+ "  ORDER BY t.limit_date DESC";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");

				Task task = new Task();
				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setDeleteFlag(delete_flag);
				list.add(task);
			}
		}
		return list;
	}
	
	/**
	 * タスクIDをキーにして該当タスク一情報を取得する
	 * @param taskId
	 * @return Task
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Task searchTaskByTaskId (int taskId) throws ClassNotFoundException, SQLException {
		Task task = new Task();
		String sql = "SELECT"
				+ "  t.task_id, "
				+ "  t.task_name, "
				+ "  c.category_name,"
				+ "  u.user_name,"
				+ "  s.status_name,"
				+ "  t.limit_date,"
				+ "  t.memo, "
				+ "  t.delete_flag, "
				+ "  t.create_datetime, "
				+ "  t.update_datetime "
				+ "FROM t_task t "
				+ "  INNER JOIN m_category c "
				+ "    ON t.category_id = c.category_id "
				+ "  INNER JOIN m_user u "
				+ "    ON t.user_id = u.user_id "
				+ "  INNER JOIN m_status s "
				+ "    ON t.status_code = s.status_code "
				+ "  WHERE t.task_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, taskId);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				int task_id = res.getInt("task_id");
				String task_name = res.getString("task_name");
				String category_name = res.getString("category_name");
				String user_name = res.getString("user_name");
				String status_name = res.getString("status_name");
				Date limit_date = res.getDate("limit_date");
				String memo = res.getString("memo");
				String delete_flag = res.getString("delete_flag");
				Timestamp create_datetime = res.getTimestamp("create_datetime");
				Timestamp update_datetime = res.getTimestamp("update_datetime");

				task.setId(task_id);
				task.setName(task_name);
				task.setCategoryName(category_name);
				task.setUserName(user_name);
				task.setStatusName(status_name);
				task.setLimitDate(limit_date);
				task.setMemo(memo);
				task.setDeleteFlag(delete_flag);
				task.setCreateDatetime(create_datetime);
				task.setUpdateDatetime(update_datetime);
			}
		}
		return task;		
	}
	
	/**
	 * 更新用データを受け取ってタスク情報の更新処理を行う。
	 * @param task
	 * @return int count
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateTask(Task task) throws ClassNotFoundException, SQLException {
		int count = 0;
		String sql = "UPDATE t_task "
				+ "SET task_name=?,"
				+ "	category_id=?,"
				+ "	limit_date=?,"
				+ "	user_id=?,"
				+ "	status_code=?,"
				+ "	memo=?,"
				+ "	delete_flag=? "
				+ "WHERE task_id=?;";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, task.getName());
			pstmt.setInt(2, task.getCatagoryId());
			pstmt.setDate(3, task.getLimitDate());
			pstmt.setString(4, task.getUserId());
			pstmt.setString(5, task.getStatusCode());
			pstmt.setString(6, task.getMemo());
			pstmt.setString(7, task.getDeleteFlag());
			pstmt.setInt(8, task.getId());
			count = pstmt.executeUpdate();
		}
//		System.out.println("count=" + count);
		return count;
	}
	
	/**
	 * 受取ったタスクIDを元に該当タスク情報を削除する
	 * @param task_id
	 * @return int count
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteTask(int task_id) throws ClassNotFoundException, SQLException {
		int count = 0;
		String sql = "UPDATE t_task "
				+ "SET delete_flag=1 "
				+ "WHERE task_id=?;";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, task_id);
			count = pstmt.executeUpdate();
		} 
		return count;		
	}

}

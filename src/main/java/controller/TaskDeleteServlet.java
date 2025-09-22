package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskDAO;
import model.entity.Task;
import util.LoginCheck;

/**
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet("/task-delete")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * タスク詳細画面から送信されたタスクIDを元に該当タスクを論理削除し。
	 * 削除処理後はラスク一覧画面へ遷移し、削除済みタスクIDとタスク名をアラート表示する
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = delete(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	/**
	 * タスク詳細画面から送信されたタスクIDを元に該当タスクを論理削除し。
	 * 削除処理後はラスク一覧画面へ遷移し、削除済みタスクIDとタスク名をアラート表示する
	 * @param request
	 * @param response
	 * @return String 遷移先URL
	 * @throws ServletException
	 * @throws IOException
	 */
	private String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		TaskDAO tDao = new TaskDAO();
		String taskName = null;
		// タスク名を取得する
		try {
			Task task = tDao.searchTaskByTaskId(task_id);
			taskName = task.getName();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// 削除処理を行う
		int count = 0;
		try {
			count = tDao.deleteTask(task_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// 削除処理が成功した場合は、タスク一覧画面に遷移し、削除済みタスクIDとタスク名を表示する
		String url = null;
		if (count != 0) {
			url = LoginCheck.gotoTaskListPage(request, response);
			request.setAttribute("deleteSuccess", "タスクID「" + task_id + " 」　　タスク名「" + taskName + "」を削除しました！");
		}
		return url;
	}

}


package controller;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class TaskEditServlet
 */
@WebServlet("/task-edit")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * リクエストパラメータactionを受け取り、その値に応じて処理を振り分ける
	 * action=task_edit：タスク編集画面へ遷移する
	 * action=execute_edit：更新処理を実行してタスク詳細画面へ戻る
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
//		System.out.println("action=" + action);
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {

			switch(action) {
			// タスク編集画面へ遷移する
			case "task_edit": 
				path = gotoTaskEditPage(request, response);
				break;
			// 更新処理を実行してタスク詳細画面へ戻る
			case "execute_edit": 
//				System.out.println("execute_edit" + primary);
				path = executeEdit(request, response);
				break;
			default:
				// do nothing
			}
		} else {
			path = "WEB-INF/view/login.jsp";
			System.out.println("ログイン中のユーザーはいません。");
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * タスク詳細画面から送信されたタスクIDを受け、該当タスク情報をタスク編集画面へ送信する
	 * @param request
	 * @param response
	 * @return String 遷移先URL(タスク編集画面)
	 */
	private String gotoTaskEditPage(HttpServletRequest request, HttpServletResponse response) {
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		TaskDAO dao = new TaskDAO();
		try {
			Task task = dao.searchTaskByTaskId(task_id);
			request.setAttribute("task", task);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "WEB-INF/view/task_edit.jsp";
	}
	
	/**
	 * タスク編集画面から送信されたリクエストパラメータから、データ更新用のタスクを生成。
	 * タスクデータ更新後、更新後のデータを取得してタスク詳細画面へのURLを戻す。
	 * @param request
	 * @param response
	 * @return String 遷移先URL(タスク詳細画面)
	 */
	private String executeEdit(HttpServletRequest request, HttpServletResponse response)  {
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		int category_id = Integer.parseInt(request.getParameter("category"));
		String task_name = request.getParameter("task_name");
		String user_id = request.getParameter("user");
		String status_code = request.getParameter("status");
		Date limit_date = Date.valueOf(request.getParameter("limit_date"));
		String memo = request.getParameter("memo");
		String deleteFlag = "0";
		
//		System.out.println("task_id:" + task_id);
//		System.out.println("category_id:" + category_id);
//		System.out.println("task_name:" + task_name);
//		System.out.println("user_id:" + user_id);
//		System.out.println("status_code:" + status_code);
//		System.out.println("limit_date:" + limit_date);
//		System.out.println("memo=" + memo);
		
		// 更新用タスクを生成し、DBに対して更新処理を行う。
		Task task = new Task(task_id, task_name, category_id, user_id, status_code, limit_date, memo, deleteFlag);		
		TaskDAO dao = new TaskDAO();
		int count = 0;
		try {
			count = dao.updateTask(task);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// 更新処理に成功した場合は、更新後のタスク情報を取得して遷移先へ送信する
		if (count != 0) {
			try {
				Task updated = dao.searchTaskByTaskId(task_id);
				request.setAttribute("task", updated);
				request.setAttribute("successMessage", "内容を更新しました！");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return "WEB-INF/view/task_detail.jsp";
	}

}

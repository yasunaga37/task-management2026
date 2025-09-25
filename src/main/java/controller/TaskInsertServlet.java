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
import javax.servlet.http.HttpSession;

import model.dao.TaskDAO;
import model.entity.Task;
import util.LoginCheck;

/**
 * Servlet implementation class TaskInsertServlet
 */
@WebServlet("/task-insert")
public class TaskInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ログイン中ユーザーの有無をチェックし、新規タスク登録画面へ遷移する。
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			path = gotoTaskInsertPage(request, response);
		} else {
			path = "WEB-INF/view/login.jsp";
			System.out.println("ログイン中のユーザーはいません。");
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * 新規タスクを登録し、登録後にタスク詳細画面へ遷移する
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		path = executeTaskInsert(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	/**
	 * 重複しない新規タスクIDと現在の日付を取得して、新規タスク登録画面へ遷移する。
	 * @param request
	 * @param response
	 * @return String 遷移先URL(新規タスク登録画面)
	 */
	private String gotoTaskInsertPage(HttpServletRequest request, HttpServletResponse response) {
		TaskDAO dao = new TaskDAO();
		try {
			int newTaskID = dao.getMaxId() + 1;
			Date today = new Date(System.currentTimeMillis());
			HttpSession session = request.getSession();
			request.setAttribute("task_id", newTaskID);
			request.setAttribute("today", today);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "WEB-INF/view/task_insert.jsp";
	}
	
	/**
	 * 新規タスクを登録し、登録後にタスク詳細画面へ遷移する
	 * @param request
	 * @param response
	 * @return String 遷移先URL(タスク詳細画面)
	 * @throws ServletException
	 * @throws IOException
	 */
	private String executeTaskInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		String task_name = request.getParameter("task_name");
		int category_id = Integer.parseInt(request.getParameter("category"));
		String user_id = request.getParameter("user");
		String status_code = request.getParameter("status");
		Date limit_date = Date.valueOf(request.getParameter("limit_date"));
		String memo = request.getParameter("memo");
		Date today = Date.valueOf(request.getParameter("today"));
		
//		System.out.print("task_id:" + task_id + " category_id:" + category_id + " task_name:" + task_name + " user_id:" + user_id);
//		System.out.println(" status_code:" + status_code + " limit_date:" + limit_date + " memo:" + memo + " today:" + today);
		
		Task newTask = new Task(task_id, task_name, category_id,  user_id, status_code, limit_date,  memo, "0");
		TaskDAO dao = new TaskDAO();
		int count = 0;
		try {
			count = dao.insertTask(newTask);
			if (count != 0) {
				Task task = dao.searchTaskByTaskId(task_id);
				request.setAttribute("task", task);
				request.setAttribute("successMessage", "以下の新規タスクを追加しました。");
				path = "WEB-INF/view/task_detail.jsp";
			} else {
				// 新規レコード追加に失敗
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return path;
	}

}

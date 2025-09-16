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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
//		System.out.println("action=" + action);
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			int task_id = Integer.parseInt(request.getParameter("task_id"));
			switch(action) {
			case "task_edit":
				path = gotoTaskEditPage(task_id, request, response);
				break;
			case "execute_edit":
//				System.out.println("execute_edit" + primary);
				path = executeEdit(task_id, request, response);
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

	private String gotoTaskEditPage(int task_id, HttpServletRequest request, HttpServletResponse response) {
		TaskDAO dao = new TaskDAO();
		try {
			Task task = dao.searchTaskByTaskId(task_id);
			request.setAttribute("task", task);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "WEB-INF/view/task_edit.jsp";
	}
	
	private String executeEdit(int task_id, HttpServletRequest request, HttpServletResponse response) {
		String category_id = request.getParameter("category");
		String task_name = request.getParameter("task_name");
		String user_id = request.getParameter("user");
		String status_code = request.getParameter("status");
		
		System.out.println("task_id:" + task_id);
		System.out.println("category_id:" + category_id);
		System.out.println("task_name:" + task_name);
		System.out.println("user_id:" + user_id);
		System.out.println("status_code:" + status_code);
		return "WEB-INF/view/task_detail.jsp";
	}

}

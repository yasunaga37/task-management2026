package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class TaskListServlet
 */
@WebServlet("/task-list")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String path = null;
		if ("all".equals(userId)) {
			path = gotoTaskListPage(request, response);
		} else {
			path = gotoTaskListPageByUserId(userId, request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getAttribute("action");
		String path = null;
		if ("login".equals(action)) {
			path = gotoTaskListPage(request, response);
			request.setAttribute("loginSuccess", "ログインしました");
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	/**
	 * ログインユーザーの有無を確認する。
	 * ログインユーザー有の場合はタスクリストページへ遷移する
	 * ログインユーザー無しの場合はログイン認証ページへ遷移する
	 * @param request
	 * @param response
	 * @return
	 */
	public String gotoTaskListPage(HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			TaskDAO tDao = new TaskDAO();
			try {
				List<Task> list = tDao.selectAll();
				request.setAttribute("task_list", list);
				path = "WEB-INF/view/task_list.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("タスクリストの取得に失敗しました。");
				e.printStackTrace();
			}
		} else {
			path = "WEB-INF/view/login.jsp";
			System.out.println("ログイン中のユーザーはいません。");
		}
		return path;
	}
	
	public String gotoTaskListPageByUserId(String userId, HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			TaskDAO tDao = new TaskDAO();
			try {
				List<Task> list = tDao.searchTaskByUserId(userId);
				request.setAttribute("task_list", list);
				path = "WEB-INF/view/task_list.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("タスクリストの取得に失敗しました。");
				e.printStackTrace();
			}
		} else {
			path = "WEB-INF/view/login.jsp";
			System.out.println("ログイン中のユーザーはいません。");
		}
		return path;
	}

}

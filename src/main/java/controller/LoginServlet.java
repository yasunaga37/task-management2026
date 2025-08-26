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
import javax.servlet.http.HttpSession;

import model.dao.TaskDAO;
import model.dao.UserDAO;
import model.entity.Task;
import model.entity.User;
import util.LoginCheck;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/index.html")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 初回アクセス時はログインjspへ遷移する
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "WEB-INF/view/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * ユーザーIDとパスワードからログイン認証を行い、
	 * 認証成功の場合はタスクリストページへ遷移する
	 * 認証失敗の場合はログイン認証ページへ遷移する
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		request.setCharacterEncoding("UTF-8");
		String path = null;
		if (login(request, response)) {
			path = gotoTaskListPage(request, response);
		} else {
			path = "WEB-INF/view/login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * ユーザーIDとパスワードからログイン認証を行い、
	 * 認証後にログインユーザーをセッションスコープに保存する。
	 * @param request
	 * @param response
	 * @return User ログイン中のユーザー
	 */
	private boolean login(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		System.out.println(user_id + " " + password);
		boolean loggedIn = false;

		UserDAO dao = new UserDAO();
		User loginUser = null;
		try {
			loginUser = dao.login(user_id, password);
			if (loginUser != null) {
				System.out.println(loginUser.getId());
				HttpSession session = request.getSession();
				session.setAttribute("login_user", loginUser);
				loggedIn = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return loggedIn;
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

}

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

import model.dao.CategoryDAO;
import model.dao.StatusDAO;
import model.dao.UserDAO;
import model.entity.Category;
import model.entity.Status;
import model.entity.User;

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
	 * actionの値 => null：初回アクセス時
	 * actionの値 => login：login.jspの「ログイン」ボタンが押された時
	 * actionの値 => login：ダイアログの「ログイン」ボタンが押された時
	 * actionの値 => logout：ダイアログの「ログアウト」ボタンが押された時
	 * 
	 * action == null: 初回アクセス時はログインjspへ遷移する
	 * "login".equals(action): ログアウトページのダイアログから「ログイン」ボタン押下時はログインjspへ遷移する
	 * "logout".equals(action): タスク一覧ページのダイアログから「ログアウト」ボタン押下時はログアウトjspへ遷移する
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = null;
		String action = (String) request.getParameter("action");
//		System.out.println("action:" + action);
		if (action == null || "login".equals(action)) {
			path = "WEB-INF/view/login.jsp";
		}  else if ("logout".equals(action)) {
			path = logout(request, response);
		}
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
		String action = request.getParameter("action");	    
		if ("login".equals(action) && login(request, response)) {
			path = "task-list";
		} else {
			path = "WEB-INF/view/login.jsp";
			request.setAttribute("loginFailure", "パスワード認証に失敗しました。");
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
		boolean loggedIn = false;

		UserDAO userDao = new UserDAO();
		StatusDAO statusDao = new StatusDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			if (userDao.login(user_id, password)) {
				User loginUser = userDao.searchById(user_id);
				request.setAttribute("loginSuccess", "ログインしました。");
				List<User> uList = userDao.selectAll();
				List<Status> sList = statusDao.selectAll();
				List<Category> cList = categoryDAO.selectAll();
				HttpSession session = request.getSession();
				session.setAttribute("login_user", loginUser);
				session.setAttribute("user_list", uList);
				session.setAttribute("status_list", sList);
				session.setAttribute("category_list", cList);
				loggedIn = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return loggedIn;
	}

	/**
	 * セッションスコープを廃棄してログアウトページへ遷移する。
	 * @param request
	 * @param response
	 * @return String logout.jsp
	 */
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "WEB-INF/view/logout.jsp";
	}

}

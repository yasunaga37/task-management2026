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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "WEB-INF/view/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		User loginUser = login(request, response);
		String path = gotoTaskListPage(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	/**
	 * ユーザーIDとパスワードからログイン認証を行う
	 * @param request
	 * @param response
	 * @return User ログイン中のユーザー
	 */
	private User login(HttpServletRequest request, HttpServletResponse response)  {
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		System.out.println(user_id + " " + password);
		
		UserDAO dao = new UserDAO();
		User loginUser = null;
		try {
			loginUser = dao.login(user_id, password);
			System.out.println(loginUser.getId());
			HttpSession session = request.getSession();
			session.setAttribute("login_user", loginUser);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return loginUser;
	}
	
	public String gotoTaskListPage(HttpServletRequest request, HttpServletResponse response)  {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			TaskDAO tDao = new TaskDAO();
			try {
				List<Task> list = tDao.selectAll();
				request.setAttribute("task_list", list);
				path = "WEB-INF/view/task_list.jsp";		
			} catch (ClassNotFoundException | SQLException e) {
				path = "WEB-INF/view/login.jsp";		
				e.printStackTrace();
			}
		} else {
			System.out.println("ログイン中のユーザーはいません。");
		}
		return path;
	}

}

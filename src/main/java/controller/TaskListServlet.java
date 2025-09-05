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
	 * ナビゲーションバーの「ユーザー別タスク」「状況」のプルダウンメニューの選択による処理の分岐を行う。
	 * ・「ユーザー別タスク」「状況」のプルダウンで「すべて」が選択された場合
	 * すべてのタスクを表示する。
	 * 
	 * ・「ユーザー別タスク」のプルダウンで特定のユーザーが選択された場合
	 * 該当ユーザーの担当タスクが表示される。
	 * 
	 *  ・「状況」のプルダウンで「すべて」以外が選択された場合
	 *  該当状況のタスクが表示される。
	 *  
	 *  ・「カテゴリ」のプルダウンで「すべて」以外が選択された場合
	 *  該当カテゴリのタスクが表示される。
	 *  
	 *  ・「期限」の▲▼が選択された場合
	 *  期限をキーにして並び替えたタスク一覧が表示される。
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String path = null;
		String action = request.getParameter("action");
	
		if ("all".equals(action)) {
			path = gotoTaskListPage(request, response);
		} else if ("user".equals(action)) {
			String userId = request.getParameter("user_id");
			path = gotoTaskListPageByUserId(userId, request, response);
		} else if ("status".equals(action)) {
			String statusCode = request.getParameter("status_code");
			path = gotoTaskListPageByStatusCode(statusCode, request, response);
		} else if ("category".equals(action)) {
			int categoryId = Integer.parseInt(request.getParameter("category_id"));
			path = gotoTaskListPageByCategoryCode(categoryId, request, response);
		} else if ("asc".equals(action)) {
			path = gotoTaskListPageOrderByLimitDate("asc", request, response);
		} else if ("desc".equals(action)) {
			path = gotoTaskListPageOrderByLimitDate("desc", request, response);
		} else {
			// do nothing
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * LoginServlet#doPostメソッドからログイン認証後に遷移される
	 * task_list.jspへと移動して、すべてのタスクを表示させる。
	 * 
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
	
	/**
	 * ナビゲーションバーの「ユーザー別タスク」のプルダウンで特定のユーザーが選択された場合
	 * 送信されたユーザーIDから該当ユーザーが担当するタスク一覧を取得し、task_list.jspへ移動する。
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * ナビゲーションバーの「状況」のプルダウンで「すべて」以外が選択された場合
	 * 送信されたステータスコードから該当状況のタスク一覧を取得し、task_list.jspへ移動する。
	 * @param statusCode
	 * @param request
	 * @param response
	 * @return String 遷移先URL
	 */
	public String gotoTaskListPageByStatusCode(String statusCode, HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			TaskDAO tDao = new TaskDAO();
			try {
				List<Task> list = tDao.searchTaskByStatusCode(statusCode);
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
	
	/**
	 * ナビゲーションバーの「カテゴリ」のプルダウンで「すべて」以外が選択された場合
	 * 送信されたステータスコードから該当状況のタスク一覧を取得し、task_list.jspへ移動する。
	 * @param categoryId
	 * @param request
	 * @param response
	 * @return String 遷移先URL
	 */
	public String gotoTaskListPageByCategoryCode(int categoryId, HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			TaskDAO tDao = new TaskDAO();
			try {
				List<Task> list = tDao.searchTaskByCategoryId(categoryId);
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

	/**
	 * 期限をキーにして並び替えを行う
	 * @param order String asc:期限昇順  desc:期限降順
	 * @param request
	 * @param response
	 * @return String 遷移先URL
	 */
	public String gotoTaskListPageOrderByLimitDate(String order, HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		if (LoginCheck.loginCheck(request, response)) {
			TaskDAO tDao = new TaskDAO();
			List<Task> list = null;
			try {				
				if ("asc".equals(order)) {
					list = tDao.orderByLimitDateASC();
				} else if ("desc".equals(order)) {
					list = tDao.orderByLimitDateDESC();
				} else {
					// do nothing
				}
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

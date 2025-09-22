package util;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskDAO;
import model.entity.Task;
import model.entity.User;

public class LoginCheck {
	
	/**
	 * セッションスコープ内の「login_user」属性の有無をチェックする
	 * @param request
	 * @param response
	 * @return boolean
	 */
	public static boolean loginCheck(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login_user");
		boolean login = false;
		if (user != null) {
			login = true;
		}
		return login;		
	}
	
	/**
	 * 全タスク一覧画面へ遷移する
	 * @param request
	 * @param response
	 * @return 遷移先URL
	 */
	public static String gotoTaskListPage(HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		TaskDAO tDao = new TaskDAO();
		try {
			List<Task> list = tDao.selectAll();
			request.setAttribute("task_list", list);
			path = "WEB-INF/view/task_list.jsp";
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("タスクリストの取得に失敗しました。");
			e.printStackTrace();
			// path = "エラーページ"(未定)
		}
		return path;
	}

}

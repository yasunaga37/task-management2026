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

/**
 * Servlet implementation class TaskDetailServlet
 */
@WebServlet("/task-detail")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * タスクIDを元に該当タスクを取得し、タスク情報詳細ページへ遷移する
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		path = gotoTaskDetailPageByTaskId(task_id, request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * タスクIDを元に該当タスクを取得し、遷移先URL(タスク情報詳細ページ)を戻す。
	 * @param task_id
	 * @param request
	 * @param response
	 * @return String 遷移先URL(タスク情報詳細ページ)
	 */
	private String gotoTaskDetailPageByTaskId (int task_id, HttpServletRequest request, HttpServletResponse response) {
		TaskDAO dao = new TaskDAO();
		try {
			Task task = dao.searchTaskByTaskId(task_id);
//			System.out.println("task_id:" + task.getId() + ", ");
//			System.out.println("task_name:" + task.getName() + ", ");
//			System.out.println("category_name:" + task.getCategoryName() + ", ");
//			System.out.println("user_name:" + task.getUserName() + ", ");
//			System.out.println("status_name:" + task.getStatusName()+ ", ");
//			System.out.println("limit_date:" + task.getLimitDate() + ", ");			
//			System.out.println( "memo:" + task.getMemo() + ", " );
//			System.out.println("delete_flag:" + task.getDeleteFlag()+ ", ");
//			System.out.println("create_datetime:" + task.getCreateDatetime() + ", ");
//			System.out.println("update_datetime:" + task.getUpdateDatetime() + ", ");
			request.setAttribute("task", task);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "WEB-INF/view/task_detail.jsp";
	}

}

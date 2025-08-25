package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.User;

public class LoginCheck {
	
	public static boolean loginCheck(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login_user");
		boolean login = false;
		if (user != null) {
			login = true;
		}
		return login;		
	}

}

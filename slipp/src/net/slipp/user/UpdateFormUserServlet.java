package net.slipp.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		HttpSession sesison = req.getSession();
		Object object = sesison.getAttribute(LoginServlet.SESSION_USER_ID);
	
		if(object == null){
			resp.sendRedirect("/slipp");
			return;
		}
		
		String userId = (String)object;
		System.out.println("User Id : " + userId);
		UserDAO userDao = new UserDAO();
		try{
			User user = userDao.findByUserId(userId);
			req.setAttribute("user", user);
			RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
			rd.forward(req, resp);
		}catch(SQLException e){
			
		}
	}
	
}

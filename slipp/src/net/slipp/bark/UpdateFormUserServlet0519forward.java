package net.slipp.bark;



@WebServlet("/users/updateForm")
public class UpdateFormUserServlet0519forward extends HttpServlet {

	
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
			RequestDispatcher rd = req.getRequestDispatcher("/update_form.jsp");
			rd.forward(req, resp);
		}catch(SQLException e){
			
		}
	}
	
}

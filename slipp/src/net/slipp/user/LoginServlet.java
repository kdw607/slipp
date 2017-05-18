package net.slipp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		
		try{
			User.login(userId, password);
			HttpSession session = req.getSession();
			session.setAttribute("userId", userId);//session �ٸ� jsp ���� ������ ���� ����
			resp.sendRedirect("/slipp");
			
		}catch(UserNotFoundException e){//���� ����� �α����Ҷ�
				
			forwardJSP(req, resp, "�������� �ʴ� ����� �Դϴ�. �ٽ� �α����ϼ���.");
			
		}catch(PasswordMismatchException e){//��й�ȣ Ʋ���� ��

			forwardJSP(req, resp, "��й�ȣ�� Ʋ���ϴ�. �ٽ� �α����ϼ���");
		}
	
	}
	
	private void forwardJSP(HttpServletRequest req, HttpServletResponse resp, String errorMessage) throws ServletException, IOException {
		
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		rd.forward(req, resp);
	}
	
}
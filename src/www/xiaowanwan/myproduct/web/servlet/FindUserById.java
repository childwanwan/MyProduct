package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.exception.UserException;
import www.xiaowanwan.myproduct.service.UserService;

public class FindUserById extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		
		UserService us = new UserService();
		
		try {
			User user = us.findUserById(id);
			request.getSession().setAttribute("u", user);
			request.getRequestDispatcher("modifyuserinfo.jsp").forward(request, response);
		} catch (UserException e) {
			response.getWriter().write(e.getMessage());
			response.sendRedirect(request.getContextPath()+"login.jsp");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

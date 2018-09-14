package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.User;

public class MyAccount extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session中取出user对象
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){//=null就是还没登入
			//重定向方法直接走，不用管下面的代码
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else{
			//走到普通用户页面
			String path = "/myAccount.jsp";
			if("admin".equals(user.getRole())){
				//管理员
				path="/admin/login/home.jsp";
			}
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

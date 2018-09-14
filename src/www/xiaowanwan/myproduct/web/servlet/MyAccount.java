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
		//��session��ȡ��user����
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){//=null���ǻ�û����
			//�ض��򷽷�ֱ���ߣ����ù�����Ĵ���
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else{
			//�ߵ���ͨ�û�ҳ��
			String path = "/myAccount.jsp";
			if("admin".equals(user.getRole())){
				//����Ա
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

package www.xiaowanwan.myproduct.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.User;

public class RoleFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		//ǿת
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		//����ҵ��
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null) {
			
			if(  !"admin".equals(user.getRole())){
				
				response.getWriter().write("Ȩ�޲��㣬���޷����ʣ�");
				response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
				return;
			}
			//����
			chain.doFilter(request, response);
		}
		if( !"admin".equals(user.getRole())){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}	
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

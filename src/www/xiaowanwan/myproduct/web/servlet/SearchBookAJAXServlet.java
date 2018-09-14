package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.service.ProductService;



public class SearchBookAJAXServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//只能解决post方式的乱码
		response.setContentType("text/html;charset=UTF-8");
		
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso-8859-1"),"UTF-8");
		ProductService ps = new ProductService();
		List<Object> list = ps.searchBookByName(name);
		
		//把集合中的数据转换为字符串返回到网页
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			if(i>0){
				str+=",";
			}
			str+=list.get(i);
		}
		
		System.out.println(str);
		//把数据响应到客户端
		response.getWriter().write(str);//str 1001,1002,1003
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

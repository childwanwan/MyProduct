package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.service.ProductService;

public class FindProductBySearch extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		ProductService ps = new ProductService();
		List<Product> list = ps.searchBooksByName(name);
		
		if(list.size()>=1){
			request.setAttribute("list", list);
			
			
			request.getRequestDispatcher("/search_product.jsp").forward(request, response);
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("<h1>暂时还没有您需要的书...</h1>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
			
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

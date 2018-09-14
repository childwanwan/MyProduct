package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.PageBean;
import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.service.ProductService;



public class PageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//导航按钮的查询条件
		String category = request.getParameter("category");
		if(category==null){
			category="";
		}
		if("全部商品目录".equals(category)){
			category="";
		}
		if("全部商品".equals(category)){
			category="";
		}
		//初始化每页显示的记录数
		int pageSize = 4;
		
		int currentPage = 1;//当前页
		String currPage = request.getParameter("currentPage");//从上一页或下一页得到的数据
		if(currPage!=null&&!"".equals(currPage)){//第一次访问资源时，currPage可能是null
			currentPage = Integer.parseInt(currPage);
		}
		
		
		ProductService bs = new ProductService();
		//分页查询，并返回PageBean对象
		PageBean pb = bs.findBooksPage(currentPage,pageSize,category);
		
		request.setAttribute("pb", pb);
		if("".equals(category)){
			category="全部商品";
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

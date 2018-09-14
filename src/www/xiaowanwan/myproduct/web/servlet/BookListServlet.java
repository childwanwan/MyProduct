package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.service.ProductService;



public class BookListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html;charset=UTF-8");
		//���� ҵ���߼�
		ProductService ps = new ProductService();
		List<Product> list = ps.findAllBooks();
		
		
		//��תҳ��
	
			request.setAttribute("books", list);//��list���뵽request������
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

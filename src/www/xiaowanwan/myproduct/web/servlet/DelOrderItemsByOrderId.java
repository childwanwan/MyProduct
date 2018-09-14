package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.service.OrderService;

public class DelOrderItemsByOrderId extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderid = request.getParameter("orderid");
		OrderService os = new OrderService();
		os.delOrdersByOrderId(orderid);
		
		
		request.getRequestDispatcher("findOrderByUserId").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

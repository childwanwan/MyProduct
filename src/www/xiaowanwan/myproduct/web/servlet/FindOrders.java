package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.service.OrderService;
import www.xiaowanwan.myproduct.service.UserService;

public class FindOrders extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		UserService us = new UserService();
		OrderService os = new OrderService();
		List<Order> orders = os.findOrders();
		
		
		request.setAttribute("orders", orders);
		request.setAttribute("count", orders.size());
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

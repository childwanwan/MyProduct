package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.service.OrderService;



public class FindOrderByUserId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		OrderService os = new OrderService();
		List<Order> orders = os.findOrdersByUserId(user.getId());
		
		request.setAttribute("orders", orders);
		request.setAttribute("count", orders.size());
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

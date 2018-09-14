package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.OrderItem;
import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.service.OrderService;

public class CreateOrderServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//封装Order对象
		Order order = new Order();
		
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setUser((User) request.getSession().getAttribute("user"));//把user放入order对象
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("订单信息：id="+order.getId()+"收货人姓名="+order.getReceiverName()+"地址="+order.getReceiverAddress()+"money="+order.getMoney());
		//获取session对象中的购物车数据
		Map<Product,String> cart = (Map<Product,String>)request.getSession().getAttribute("cart");
		//遍历购物车中的数据，添加到orderItem对象中，同时把多个orderItem添加到list集合中
		List<OrderItem> list = new ArrayList();
		for(Product p : cart.keySet()){
			OrderItem oi = new OrderItem();
			oi.setOrder(order);//把Order对象添加到OrderItem中
			oi.setP(p);//把购物车中的商品对象添加到OrderItem中
			oi.setBuynum(Integer.parseInt(cart.get(p)));//购物车中的商品的数量
			list.add(oi);//把每个订单项添加到集合中
		}
		//把集合放入Order对象中
		order.setOrderItems(list);
		
		
		//调用业务逻辑
		OrderService os = new OrderService();
		
		os.addOrder(order);
		//把用户的
		request.setAttribute("orderid", order.getId());
		request.setAttribute("money", order.getMoney());
		
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

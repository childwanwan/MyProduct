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
		//��װOrder����
		Order order = new Order();
		
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setUser((User) request.getSession().getAttribute("user"));//��user����order����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������Ϣ��id="+order.getId()+"�ջ�������="+order.getReceiverName()+"��ַ="+order.getReceiverAddress()+"money="+order.getMoney());
		//��ȡsession�����еĹ��ﳵ����
		Map<Product,String> cart = (Map<Product,String>)request.getSession().getAttribute("cart");
		//�������ﳵ�е����ݣ���ӵ�orderItem�����У�ͬʱ�Ѷ��orderItem��ӵ�list������
		List<OrderItem> list = new ArrayList();
		for(Product p : cart.keySet()){
			OrderItem oi = new OrderItem();
			oi.setOrder(order);//��Order������ӵ�OrderItem��
			oi.setP(p);//�ѹ��ﳵ�е���Ʒ������ӵ�OrderItem��
			oi.setBuynum(Integer.parseInt(cart.get(p)));//���ﳵ�е���Ʒ������
			list.add(oi);//��ÿ����������ӵ�������
		}
		//�Ѽ��Ϸ���Order������
		order.setOrderItems(list);
		
		
		//����ҵ���߼�
		OrderService os = new OrderService();
		
		os.addOrder(order);
		//���û���
		request.setAttribute("orderid", order.getId());
		request.setAttribute("money", order.getMoney());
		
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

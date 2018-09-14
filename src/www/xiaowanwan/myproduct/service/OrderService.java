package www.xiaowanwan.myproduct.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import www.xiaowanwan.myproduct.dao.OrderDao;
import www.xiaowanwan.myproduct.dao.OrderItemDao;
import www.xiaowanwan.myproduct.dao.ProductDao;
import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.OrderItem;
import www.xiaowanwan.myproduct.exception.OrderException;
import www.xiaowanwan.myproduct.util.ManagerThreadLocal;

public class OrderService {
	
	OrderDao orderDao = new OrderDao();
	OrderItemDao orderIteamDao = new OrderItemDao();
	ProductDao productDao = new ProductDao();
	
	public void addOrder(Order order) {
		
		try {
			ManagerThreadLocal.startTransacation();
			orderDao.addOrder(order);
			orderIteamDao.addOrderItem(order);
			productDao.updateProductNum(order);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
			e.printStackTrace();
		}
		
	}

	public List<Order> findOrdersByUserId(int id) {
		try {
			return orderDao.findOrders(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Order findOrdersByOrderId(String orderid) {
		try {
			return orderDao.findOrdersByOrderId(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delOrdersByOrderId(String orderid) {
		try {
			ManagerThreadLocal.startTransacation();
			
			orderIteamDao.delOrderItemByOrderId(orderid);
			orderDao.delOrdersByOrderId(orderid);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
			e.printStackTrace();
		}
		
	}

	public void modifyOrderState(String orderid) throws OrderException {
		try {
			orderDao.modifyOrderState(orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("修改失败！");
		}
		
	}

	public List<Order> findOrders() {
		try {
			return orderDao.findOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//根据日期查询图书销售情况
	public Map<String, Integer> searchBooksSell(Date date) {
		try {
			return orderDao.searchBooksSell(date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

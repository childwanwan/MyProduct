package www.xiaowanwan.myproduct.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.OrderItem;
import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.util.DateHelper;
import www.xiaowanwan.myproduct.util.DruidUtil;
import www.xiaowanwan.myproduct.util.ManagerThreadLocal;



public class OrderDao {
	// ��Ӷ���
	public void addOrder(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),
				"INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)", order.getId(),
				order.getMoney(), order.getReceiverAddress(), order
						.getReceiverName(), order.getReceiverPhone(), order
						.getPaystate(), order.getOrdertime(), order.getUser()
						.getId());
	}

	// �����û�id��ѯ���ж���
	public List<Order> findOrders(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from orders where user_id=?",
				new BeanListHandler<Order>(Order.class), id);
	}

	//��ѯָ���û��Ķ�����Ϣ
	public Order findOrdersByOrderId(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		//�õ�һ������
		Order order = qr.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),orderid);
		//�õ���ǰ�����е����ж�����
		//List<OrderItem> orderItems = qr.query("select * from orderItem where order_id=? ", new  BeanListHandler<OrderItem>(OrderItem.class),orderid);
		//�õ����ж������е���Ʒ��Ϣ
		//List<Product> products = qr.query("select * from products where id in(select product_id from orderitem where order_id=?)", new BeanListHandler<Product>(Product.class),orderid);
		
		List<OrderItem>  orderItems = qr.query("SELECT * FROM orderitem o,products p WHERE p.id=o.product_id AND order_id=?", new ResultSetHandler<List<OrderItem>>(){

			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				while(rs.next()){
					
					//��װOrderItem����
					OrderItem oi = new OrderItem();
					oi.setBuynum(rs.getInt("buynum"));
					//��װProduct����
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					//��ÿ��p�����װ��OrderItem������
					oi.setP(p);
					//��ÿ��OrderItem�����װ��������
					orderItems.add(oi);
				}
				return orderItems;
			}
			
		},orderid);
		
		//�����еĶ�����orderItems��ӵ���������Order��
		order.setOrderItems(orderItems);
		
		return order;
	}

	public void delOrdersByOrderId(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		 
		 qr.update(ManagerThreadLocal.getConnection(), "DELETE FROM orders WHERE id="+"'"+orderid+"'");
	}
//�޸Ķ���֧��״̬
	public void modifyOrderState(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		qr.update("update orders set paystate=1 where id=?",orderid);
		
	}

	public List<Order> findOrders() throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		//SELECT * FROM orders o,user u WHERE u.id=o.user_id 
		//List<Order> orders= qr.query("select * from orders", new BeanListHandler<Order>(Order.class));
		List<Order>  orders = qr.query("SELECT * FROM orders o,user u WHERE u.id=o.user_id", new ResultSetHandler<List<Order>>(){

			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
				while(rs.next()){
					
					//��װUser����
					User u = new User();
					u.setId(rs.getInt("user_id"));
					u.setUsername(rs.getString("username"));
					
					
					
					//��װorder����
					Order o = new Order();
					o.setId(rs.getString("id"));
					o.setMoney(rs.getDouble("money"));
					o.setReceiverAddress(rs.getString("receiverAddress"));
					o.setReceiverName(rs.getString("receiverName"));
					o.setReceiverPhone(rs.getString("receiverPhone"));
					o.setPaystate(rs.getInt("paystate"));
					o.setOrdertime(rs.getDate("ordertime"));
					o.setUser(u);
					orders.add(o);
					
				}
				return orders;
			}
			
		});
		
		
		
		return orders;
	}
	//�������ڲ�ѯͼ���������
	public Map<String, Integer> searchBooksSell(Date date) throws SQLException {
		//����date
		Date date1 = date;
		//86400000��һ��ĺ�����;
		Date dateTemp = new Date(date.getTime());
		dateTemp.setMonth(date1.getMonth()+1);
		Date date2 = new Date(dateTemp.getTime()-86400000);
		
		
		
		//Date date2 =new Date(DateHelper.getSQLDate(date.getYear(), date.getMonth()+1, 1).getTime()-86400000);
		
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		Map<String, Integer> m = qr.query("SELECT name,buynum FROM orderitem o,products p,orders os WHERE p.id=o.product_id and os.id=o.order_id and os.ordertime>="+"'"+date1+"'"+" and os.ordertime<="+"'"+date2+"'"+"order by buynum desc", new ResultSetHandler<Map<String, Integer>>(){

			public Map<String, Integer> handle(ResultSet rs)
					throws SQLException {
				Map<String, Integer> m = new TreeMap<String, Integer>();
				while(rs.next()){
					
					m.put(rs.getString("name"), rs.getInt("buynum"));
					
				}
				return m;
			}});
		return m;
	}
}

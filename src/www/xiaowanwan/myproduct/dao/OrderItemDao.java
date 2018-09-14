package www.xiaowanwan.myproduct.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.OrderItem;
import www.xiaowanwan.myproduct.util.ManagerThreadLocal;



public class OrderItemDao {
	//���Ӷ�����
	public void addOrderItem(Order order) throws SQLException{
		List<OrderItem> orderItems = order.getOrderItems();//�õ����ж�����ļ���
		QueryRunner qr = new QueryRunner();
		Object[][] params = new Object[orderItems.size()][];
		
		for (int i = 0; i < params.length; i++) {
			//�����еĵ�һ��������������id�� �ڶ�����������Ʒid ���������� ����Ʒ�Ĺ�������
			params[i] = new Object[]{order.getId(),orderItems.get(i).getP().getId(),orderItems.get(i).getBuynum()};
		}
		qr.batch(ManagerThreadLocal.getConnection(),"INSERT INTO orderitem VALUES(?,?,?)", params );
	}

	public void delOrderItemByOrderId(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "DELETE FROM orderitem WHERE order_id="+"'"+orderid+"'");
		
	}
}
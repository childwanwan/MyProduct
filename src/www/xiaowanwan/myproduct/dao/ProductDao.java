package www.xiaowanwan.myproduct.dao;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import www.xiaowanwan.myproduct.domain.Order;
import www.xiaowanwan.myproduct.domain.OrderItem;
import www.xiaowanwan.myproduct.domain.Product;
import www.xiaowanwan.myproduct.util.DruidUtil;
import www.xiaowanwan.myproduct.util.ManagerThreadLocal;



public class ProductDao {
	//�޸���Ʒ����
	public void updateProductNum(Order order) throws SQLException{
		List<OrderItem> orderItems = order.getOrderItems();
		QueryRunner qr = new QueryRunner();
		
		Object[][] params = new Object[orderItems.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{orderItems.get(i).getBuynum(),orderItems.get(i).getP().getId()};
		}
		qr.batch(ManagerThreadLocal.getConnection(),"UPDATE products SET pnum=pnum-? WHERE id=?", params);
	}
	/**
	 * ��������ͼ��
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAllBooks() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from products", new BeanListHandler<Product>(Product.class));
	}
	/**
	 * ���ͼ����Ϣ
	 * @param product
	 * @throws SQLException
	 */
	public void addBook(Product product) throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		qr.update("INSERT INTO products VALUES(?,?,?,?,?,?,?)",product.getId(),product.getName(),product.getPrice(),product.getCategory(),product.getPnum(),product.getImg_url(),product.getDescription());
	}
	
	/**
	 * 
	 * @param id
	 * @return 
	 * @throws SQLException 
	 */
	public Product findBookById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from products where id=?", new BeanHandler<Product>(Product.class),id);
	}
	
	/**
	 * �޸�ͼ����Ϣ
	 * @param product
	 * @throws SQLException
	 */
	public void updateBook(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		qr.update("update products set name=?,price=?,pnum=?,category=?,description=? where id=?",
				product.getName(),product.getPrice(),product.getPnum(),product.getCategory(),product.getDescription(),product.getId());
		
		
	}
	/**
	 * ����idɾ��ͼ��
	 * @param id
	 * @throws SQLException 
	 */
	public void delBook(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		qr.update("delete from products where id=?",id);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 * @throws SQLException 
	 */
	public void deleAllBooks(String[] ids) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		Object[][] params = new Object[ids.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{ids[i]};//ѭ����ÿ��һά�����е�Ԫ�ظ�ֵ��ֵ��id
		}
		qr.batch("delete from products where id=?", params );
	}
	
	/**
	 * ��������ѯͼ��
	 * @param id
	 * @param category
	 * @param name
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> searchBooks(String id, String category, String name,
			String minprice, String maxprice) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		String sql = "select * from products where 1=1";
		List list = new ArrayList();
		if(!"".equals(id.trim())){
			sql+=" and id like ?"; //  ��������д%   %'1002'%
			list.add("%"+id.trim()+"%");// '%1002%'
		}
		
		if(!"".equals(category.trim())){
			sql+=" and category=?";
			list.add(category.trim());
		}
		
		if(!"".equals(name.trim())){
			sql+=" and name like ?";
			list.add("%"+name.trim()+"%");
		}
		
		if(!"".equals(minprice.trim())){
			sql+=" and price>?";
			list.add(minprice.trim());
		}
		if(!"".equals(maxprice.trim())){
			sql+=" and price< ?";
			list.add(maxprice.trim());
		}
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
	}
	
	/**
	 * �õ��ܼ�¼��
	 * @return
	 * @throws SQLException
	 */
	public int count(String category) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		String sql ="select count(*) from products";
		//���category���ǿգ��Ͱ���������
		if(!"".equals(category)){
			sql+=" where category='"+category+"'";
		}
		long l =  (Long)qr.query(sql, new ScalarHandler(1));
		return (int)l;
	}
	
	/**
	 * ���ҷ�ҳ����
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> findBooks(int currentPage, int pageSize,String category) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		
		String sql = "select * from products where 1=1";
		List list = new ArrayList();
		if(!"".equals(category)){
			sql+=" and category=?";
			list.add(category);
		}
		sql+=" limit ?,?";
		
		// select * from products where 1=1 and category=? limit ?,?;
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
	}
	
	/**
	 * ������������ͼ�� ģ����ѯ
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<Object> searchBookByName(String name) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select name from products where name like ?", new ColumnListHandler(),"%"+name+"%");
	}
	/**
	 * ����ͼ�����id 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public String selectBooksLastId() throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		List<Object[]> query = qr.query("select id from products;", new ArrayListHandler());
		if(query!=null){
			int t=0;
			  for (Object[] objects : query) {
				for (Object object : objects) {
					try {
						if(t<Integer.parseInt(object.toString())){
							t=Integer.parseInt(object.toString());
						}
					} catch (Exception e) {
						continue;
					}
						
					}
				
			}
			  return String.valueOf(t);
		}
		
		else return null;
	}
	
	/**
	 * ������������ͼ�� ģ����ѯ
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<Product> searchBooksByName(String name) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from products where name like ?",new BeanListHandler<Product>(Product.class),"%"+name+"%");
	}
}

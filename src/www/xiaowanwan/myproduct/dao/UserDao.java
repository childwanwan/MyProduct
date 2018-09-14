package www.xiaowanwan.myproduct.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import www.xiaowanwan.myproduct.domain.User;
import www.xiaowanwan.myproduct.util.DruidUtil;



public class UserDao {
	public void addUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		String sql = "INSERT INTO USER(username,PASSWORD,gender,email,telephone,introduce,activecode,state,registtime) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getUsername(), user.getPassword(),
				user.getGender(), user.getEmail(), user.getTelephone(),
				user.getIntroduce(), user.getActiveCode(), user.getState(),
				user.getRegistTime());

	}
	//根据激活码查找用户
	public User findUserByActiveCode(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from user where activecode=?", new BeanHandler<User>(User.class),activeCode);
	}
	//修改用户激活状态
	public void activeCode(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		qr.update("update user set state=1 where activecode=?",activeCode);
		
	}
	//用户登录
	public User findUserByUserNameAndPassword(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),username,password);
	}
	//据id差用户
	public User findUserById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		return qr.query("select * from user where id=?", new BeanHandler<User>(User.class),id);
	}
	//修改用户信息
	public void modifyUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		qr.update("update user set password=?,gender=?,telephone=? where id=?",user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
		
	}
}

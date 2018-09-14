package www.xiaowanwan.myproduct.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.alibaba.druid.pool.DruidPooledConnection;



public class DruidUtil {
	//获取一个数据源
	static 	DruidDataSource dataSource;
	//加载配置文件
	static{
		Properties pt = new Properties();
		try {
      
			pt.load(DruidUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties"));
			dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(pt);
			
		    }
			catch (Exception e) {
		
				e.printStackTrace();
			}
	}
     //获取一个连接对象
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		
		} catch (SQLException e) {
		
			throw new RuntimeException("服务器错误");
		}
	   //return null;
		
		
	}
    //关闭数据库
	public static void close(){
		dataSource.close();
	}
	
	
	public static DruidDataSource getDataSource() {
		return dataSource;
	}
	public static void setDataSource(DruidDataSource dataSource) {
		DruidUtil.dataSource = dataSource;
	}
	
	

}

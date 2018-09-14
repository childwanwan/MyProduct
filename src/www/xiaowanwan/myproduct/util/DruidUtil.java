package www.xiaowanwan.myproduct.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.alibaba.druid.pool.DruidPooledConnection;



public class DruidUtil {
	//��ȡһ������Դ
	static 	DruidDataSource dataSource;
	//���������ļ�
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
     //��ȡһ�����Ӷ���
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		
		} catch (SQLException e) {
		
			throw new RuntimeException("����������");
		}
	   //return null;
		
		
	}
    //�ر����ݿ�
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

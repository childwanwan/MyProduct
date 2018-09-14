package www.xiaowanwan.myproduct.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


import www.xiaowanwan.myproduct.util.DateHelper;
import www.xiaowanwan.myproduct.util.DruidUtil;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date1 = DateHelper.getSQLDate(2018, 8, 1);
		System.out.println(date1);
		int mouth = date1.getMonth()+1;
		System.out.println("mouth="+mouth);
		Date dateTemp = date1;
		dateTemp.setMonth(mouth);
		System.out.println("dateTemp/mouth="+dateTemp.getMonth());
		System.out.println("dateTemp="+dateTemp);
		//System.out.println(date1.getMonth()+2);
		//Date date2 = DateHelper.getSQLDate(2018, 8, 31);
		long l = dateTemp.getTime();
		l=l-24*60*60*1000;
		Date d = new Date(l);
		
		System.out.println(d);
		
		
		
		
		Date date2 = DateHelper.getSQLDate(2018, 8, 31);
		Connection conn=DruidUtil.getConnection();
		int no;
		String name;
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT name,buynum FROM orderitem o,products p,orders os WHERE p.id=o.product_id and os.id=o.order_id and os.ordertime>="+"'"+date1+"'"+" and os.ordertime<="+"'"+date2+"'"+"order by buynum desc");
			System.out.println("什么情况");
			if(rs==null){System.out.println("查不到？");}
			while (rs.next()) {
				// System.out.println("查到了吗？");
				no = rs.getInt(2);
				name = rs.getString(1);
				
				System.out.println( name+ "             " + no);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

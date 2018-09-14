package www.xiaowanwan.myproduct.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 这是一个用来操作日期的工具类
 */
public final class DateHelper {
	
	private static final Calendar CALENDAR = Calendar.getInstance();
	
	private static final String DATE_PATTERN = "yyyy-MM-dd" ;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat( DATE_PATTERN );
	
	private static final String TIME_PATTERN = "HH:mm:ss" ;
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat( TIME_PATTERN );
	
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd  HH:mm:ss" ;
	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat( DATE_TIME_PATTERN );
	
	static {
		// 将 时、分、秒 改成 0
		CALENDAR.set( Calendar.HOUR,  0 );
		CALENDAR.set( Calendar.HOUR_OF_DAY,  0 );
		CALENDAR.set( Calendar.MINUTE,  0 );
		CALENDAR.set( Calendar.SECOND,  0 );
		CALENDAR.set( Calendar.MILLISECOND,  0 );
	}
	
	/**
	 * 返回指定的 年月日对应的 Date 对象 (时间是当前的0:0:0.0)
	 * @param year 指定的年份
	 * @param month 指定的月份，范围是 1 到 12 
	 * @param date 指定的日期，范围可以是 1 到 31 
	 * @return 返回一个 java.util.Date 类型的对象
	 */
	public static final java.util.Date getDate( int year , int month , int date ){
		CALENDAR.set( year , month - 1 , date );
		java.util.Date ud = CALENDAR.getTime(); 
		return ud ;
	}
	
	/**
	 * 返回指定的 年月日对应的 Date 对象 (时间是当前的0:0:0.0)
	 * @param year 指定的年份
	 * @param month 指定的月份，范围是 1 到 12 
	 * @param date 指定的日期，范围可以是 1 到 31 
	 * @return 返回一个 java.sql.Date 类型的对象
	 */
	public static final java.sql.Date getSQLDate( int year , int month , int date ){
		CALENDAR.set( year , month - 1 , date );
		long ms = CALENDAR.getTimeInMillis();
		java.sql.Date sd = new java.sql.Date( ms );
		return sd ;
	}
	
	/**
	 * 将 java.util.Date 重新构造成 java.sql.Date
	 * @param ud 需要处理的 java.util.Date 对象
	 * @return
	 */
	public static final java.sql.Date cast( java.util.Date ud ) {
		if( ud != null ){
			long time = ud.getTime() ;
			return new java.sql.Date( time ) ;
		}
		return null ;
	}
	
	/**
	 * 将指定的 日期对象 格式化 为 yyyy-MM-dd 形式
	 * @param date 需要格式化的日期
	 * @return 日期对应的字符串
	 */
	public static final String dateFormat( java.util.Date date ) {
		return DATE_FORMAT.format( date );
	}
	
	/**
	 * 将指定的 日期对象 格式化 为 HH:mm:ss 形式
	 * @param date 需要格式化的日期
	 * @return 时间对应的字符串
	 */
	public static final String timeFormat( java.util.Date date ) {
		return TIME_FORMAT.format( date );
	}
	
	/**
	 * 将指定的 日期对象 格式化为 yyyy-MM-dd  HH:mm:ss 格式
	 * @param date 需要格式化的日期
	 * @return 日期和时间对应的字符串
	 */
	public static final String datetime( java.util.Date date ) {
		return DATE_TIME_FORMAT.format( date );
	}
	
	/**
	 * 将日期字符串按照 "yyyy-MM-dd" 模式解析为 日期对象
	 * @param source 需要解析的表示日期的字符串
	 * @return 解析成功返回 相应的日期对象，否则返回 null
	 */
	public static final java.util.Date parseDate( String source ) {
		java.util.Date date = null ;
		try {
			date = DATE_FORMAT.parse(source);
		} catch ( ParseException e ) {
			System.out.println( "解析 " + source + " 为 日期时发生错误，它可能不符合 " +  DATE_PATTERN + " 模式");
		}
		return date ;
	}
	
}

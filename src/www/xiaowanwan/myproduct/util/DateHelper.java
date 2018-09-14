package www.xiaowanwan.myproduct.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ����һ�������������ڵĹ�����
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
		// �� ʱ���֡��� �ĳ� 0
		CALENDAR.set( Calendar.HOUR,  0 );
		CALENDAR.set( Calendar.HOUR_OF_DAY,  0 );
		CALENDAR.set( Calendar.MINUTE,  0 );
		CALENDAR.set( Calendar.SECOND,  0 );
		CALENDAR.set( Calendar.MILLISECOND,  0 );
	}
	
	/**
	 * ����ָ���� �����ն�Ӧ�� Date ���� (ʱ���ǵ�ǰ��0:0:0.0)
	 * @param year ָ�������
	 * @param month ָ�����·ݣ���Χ�� 1 �� 12 
	 * @param date ָ�������ڣ���Χ������ 1 �� 31 
	 * @return ����һ�� java.util.Date ���͵Ķ���
	 */
	public static final java.util.Date getDate( int year , int month , int date ){
		CALENDAR.set( year , month - 1 , date );
		java.util.Date ud = CALENDAR.getTime(); 
		return ud ;
	}
	
	/**
	 * ����ָ���� �����ն�Ӧ�� Date ���� (ʱ���ǵ�ǰ��0:0:0.0)
	 * @param year ָ�������
	 * @param month ָ�����·ݣ���Χ�� 1 �� 12 
	 * @param date ָ�������ڣ���Χ������ 1 �� 31 
	 * @return ����һ�� java.sql.Date ���͵Ķ���
	 */
	public static final java.sql.Date getSQLDate( int year , int month , int date ){
		CALENDAR.set( year , month - 1 , date );
		long ms = CALENDAR.getTimeInMillis();
		java.sql.Date sd = new java.sql.Date( ms );
		return sd ;
	}
	
	/**
	 * �� java.util.Date ���¹���� java.sql.Date
	 * @param ud ��Ҫ����� java.util.Date ����
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
	 * ��ָ���� ���ڶ��� ��ʽ�� Ϊ yyyy-MM-dd ��ʽ
	 * @param date ��Ҫ��ʽ��������
	 * @return ���ڶ�Ӧ���ַ���
	 */
	public static final String dateFormat( java.util.Date date ) {
		return DATE_FORMAT.format( date );
	}
	
	/**
	 * ��ָ���� ���ڶ��� ��ʽ�� Ϊ HH:mm:ss ��ʽ
	 * @param date ��Ҫ��ʽ��������
	 * @return ʱ���Ӧ���ַ���
	 */
	public static final String timeFormat( java.util.Date date ) {
		return TIME_FORMAT.format( date );
	}
	
	/**
	 * ��ָ���� ���ڶ��� ��ʽ��Ϊ yyyy-MM-dd  HH:mm:ss ��ʽ
	 * @param date ��Ҫ��ʽ��������
	 * @return ���ں�ʱ���Ӧ���ַ���
	 */
	public static final String datetime( java.util.Date date ) {
		return DATE_TIME_FORMAT.format( date );
	}
	
	/**
	 * �������ַ������� "yyyy-MM-dd" ģʽ����Ϊ ���ڶ���
	 * @param source ��Ҫ�����ı�ʾ���ڵ��ַ���
	 * @return �����ɹ����� ��Ӧ�����ڶ��󣬷��򷵻� null
	 */
	public static final java.util.Date parseDate( String source ) {
		java.util.Date date = null ;
		try {
			date = DATE_FORMAT.parse(source);
		} catch ( ParseException e ) {
			System.out.println( "���� " + source + " Ϊ ����ʱ�������������ܲ����� " +  DATE_PATTERN + " ģʽ");
		}
		return date ;
	}
	
}

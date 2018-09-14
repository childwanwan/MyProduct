package www.xiaowanwan.myproduct.util;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		return UUID.randomUUID().toString();//得到随机的一个字符串
	}
	
/*	public static void main(String[] args) {
		System.out.println(getUUID());
	}*/
}

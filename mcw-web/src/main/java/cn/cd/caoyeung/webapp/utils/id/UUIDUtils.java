package cn.cd.caoyeung.webapp.utils.id;

import java.util.UUID;


public class UUIDUtils {
	public static String  get32BitUUID(){
		UUID uuid= UUID.randomUUID();
		String uuidString = uuid.toString().replaceAll("-", "");
		return uuidString;
	}
	public static void main(String[] args) {
		System.out.println(UUIDUtils.get32BitUUID());
		
	}

}

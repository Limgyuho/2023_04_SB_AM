package com.koreaIT.demo.util;

public class Util {

	
	public static boolean empty(Object obj) {
		
		if(obj == null) {
			return true;
		}
		
//		if(obj instanceof String == false) {
//			
//		}
		
		//컨트롤러 에서 넘겨준 값이 문자열이기 때문에 스트링을 사용한다
		String str =(String) obj;
		
		
		
		return str.trim().length() == 0;
	}

	
	
	//Object... 여러개가 순서대로 들어간다
	//컨드롤러 부분에서 인자를 받아들이기 위함이다.
	//
	public static String f(String format, Object... args) {
	
		return String.format(format, args);
	}
}

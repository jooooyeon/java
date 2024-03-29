package kr.or.ddit.reflection;

import java.lang.reflect.Modifier;

/**
 * Class의 메타정보 가져오기
 */

public class T02ClassMetadateTest {
	public static void main(String[] args) {
		//클래스 오브젝트 생성하기
		Class<?> clazz = SampleVO.class;
		
		System.out.println("심플클래스명 : " + clazz.getSimpleName());//패키지명 제외 클래스이름만
		System.out.println("클래스명 : " + clazz.getName());//패키지명 + 클래스이름
		System.out.println("상위클래스명 : " + clazz.getSuperclass().getName());//
		
		
		//패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println("패키지 정보 : " + pkg.getName());
		
		//해당 클래스에서 구현하고 있는 인터페이스 목록 가져오기
		Class<?>[] interfaces = clazz.getInterfaces();
		
		for(Class<?> inf : interfaces) {
			System.out.println(inf.getName() + "|");
			
		}
		System.out.println();
		
		
		//클래스의 접근제어자 정보 가져오기
		int modFlag = clazz.getModifiers();
		
		System.out.println("접근제어자 : " + Modifier.toString(modFlag));
		
	}

}

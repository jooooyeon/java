package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

class Util2{
	//제한된타입
	//제너릭메소드 int라는반환앞에 <>
	public static<T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
	
}

public class T04GenericMethodTest {
	public static void main(String[] args) {
		
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2);
		
		//Util2.compare("C", "JAVA");
		
		List<?> list = new ArrayList<String>();
		//<?>와일드카드 임의의 객채가온다고 표시
		//
		
	}
}

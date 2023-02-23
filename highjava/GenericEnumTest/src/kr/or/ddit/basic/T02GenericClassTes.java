package kr.or.ddit.basic;

import java.util.Map;

public class T02GenericClassTes {
/*
 	제너릭클래스 만드는 방법
 	형식)
 	class 클래스명<제너릭타입글자>{
 		제너릭타입글자 변수명; //변수선언에 제너릭을 사용 할 경우
 		...
 		
 		제너릭타입 글자 메서드명(){//반환값이 있는 메서드에서 사용할 경우
 			...
 			
 			return 값;
 		}
 		
 	
 		...
 	}
 	
 
 -- 제너릭 타입 글자--
 T => Type
 k => Key
 V => Value
 E => Element(자료구조에 들어가는 요소들을 나타낼 때 사용) 
 	
 */
	
	
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		//오브젝트 클래스이면 매번 타입을 캐스팅 해주어야함 (String) 이런식
		String rtnVal1 = (String) ng1.getVal();
		System.out.println("문자열 반환값 rtnVal1 : " + rtnVal1);
		
		Integer irtnVal2 = (Integer) ng2.getVal();
		System.out.println("정수형 반환값 irtnVal2 : " + irtnVal2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		//캐스팅이 필요없음 타입체크가 일어나고있기때문에 스트링제너릭에는 스트링만들어갈수있음 다른타입이면 오류가남
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		rtnVal1 = mg1.getVal();
		irtnVal2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnVal1);
		System.out.println("제너릭 정수형 반환값 : " + irtnVal2);
		
		//제너릭 : 타입이 정해지지않앗다 -> 지정해주면 그 타입만들어온다(?)
		//Map<K, V>
		
	}
	


}
class NonGenericClass{
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
	
}
	
	
class MyGeneric<T>{
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
	
}

package kr.or.ddit.basic;

@FunctionalInterface //이걸 붙임으로 인하여 메서드를 2개 이상 선언 불가
public interface LamdaTestInterface1 {
	//반환값이 없고 매개변수(파라미터)도 없는 추상메서드선언
	public void test();
}

@FunctionalInterface
interface LamdaTestInterface2 {
	//반환값이 없고 매개변수는 있는 추상 메서드 선언
	public void test(int a);
	
	
}

@FunctionalInterface
interface LamdaTestInterface3 {
	//반환값이 있고, 매개변수도 있는 추상메서드 선언
	public int test(int a, int b);
}


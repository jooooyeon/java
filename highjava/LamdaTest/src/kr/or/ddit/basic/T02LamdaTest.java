package kr.or.ddit.basic;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class T02LamdaTest {
	public static void main(String[] args) {
		
		//람다식을 사용하지 않았을 경우...
		LamdaTestInterface1 lam1 = new LamdaTestInterface1() {
			
			@Override
			public void test() {
				System.out.println("안녕하세요");
				System.out.println("익명 구현 객체 방식입니다");
				
			}
		};
		lam1.test(); //메서드 호출
		
		// 람다식을 사용했을 때...
		LamdaTestInterface1 lam2 = 
					()-> System.out.println("반갑습니다. \n람다식으로 처리하는 방식입니다");//lam2.test();가 아랫줄에 있어서 해당줄에 ;생략불가
		lam2.test(); //메서드 호출
		
		/////////////////////////////////////////////////////////////////////
		//람다식이 마지막에는 익명개채로 바뀜 (컴파일)
		/*
		 	람다식의 작성방법
		 	
		 	기본형식) (자료형이름 매개변수명, ...) -> {실행문들;...}
		 	
		 	1) 매개변수의 '자료형이름'은 생략할 수 있다.
		 	 예) (int a) -> {System.out.prinln(a);}
		  			(a) -> {System.out.prinln(a);}
		  			
		  	2) 매개변수가 1개일 경우에는 괄호'()'를 생략할 수 있다.
		  		(단, '자료형이름'을 지정할 경우에는 괄호를 생략할 수 없다.)
		  	 예)       a -> {System.out.prinln(a);}
		  	 예)   int a -> {System.out.prinln(a);} 불가!!
		  	
		  	3) '실행문'이 1개일 경우에는 '{}'를 생략할 수 있다.
		  		(이때, 문장의 끝을 나타내는 세미콜론(;)도 생략한다.
		  	 예)       a -> System.out.prinln(a)
		  	
		  	4) 매개변수가 하나도 없으면 괄호'()'를 생략 할 수 없다.
		  	 예)       () -> System.out.prinln("안냥")
		 	
		 	5) 변환값이 있을 경우에는 return 명령을 사용한다.
		  	 예) (a, b) -> {return a+b;}
		  	     (a, b) -> return a+b //3번내용 실행문이 1개여서{}와 ;생략가능
		  	     
		  	6) 실행문에 return문만 있을 경우 return명령과 '{}'를 생략 할 수 있다.
		  	 예) (a, b) -> a + b //3번내용 실행문이 1개여서{}와 ;생략가능
		  	 
		 	 
		 */
		
		
		//직접만든 인터페이스 사용
////	LamdaTestInterface2 lam3 = 
//		(Integer z)->{
//			int result = z +100;
//			System.out.println("result =" + result);
//		};
////		lam3.test(30);
		
		
		//자바에서 제공하는 펑셔널인터페이스 사용
		Consumer<Integer> lam3 = 
				(Integer z)->{
					int result = z +100;
					System.out.println("result =" + result);
				};
		lam3.accept(30);
			
		
		
		LamdaTestInterface2 lam4 = 
				z-> {
					int result = z +300;
					System.out.println("result =" + result);
				};
		lam4.test(60);
			
			
		LamdaTestInterface2 lam5 = 
				z->System.out.println("reuslt = " + (z+50));//중괄호 생략
		lam5.test(90);
			
		
		
		
		System.out.println("----------------------------------------");
			
		
		
		
//		LamdaTestInterface3 lam6 = 
//				(int x, int y) -> {
//					int r = x + y;
//					return r;
//				};
//		int k = lam6.test(20, 50);
//		System.out.println("k = " + k);
			
		//자바에서 제공하는 펑셔널인터페이스 사용
		BiFunction<Integer, Integer, Integer> lam6 = 
				(Integer x, Integer y) -> {
					int r = x + y;
					return r;
				};
		int k = lam6.apply(20, 50);
		System.out.println("k = " + k);
			
		
		/////////////////////////////////////////////
		LamdaTestInterface3 lam7 =
				(x , y) -> {
					return x+y;
				};
		k= lam7.test(80, 50);
		System.out.println("k = " + k);
			
		////////////////////////////////////////	
		LamdaTestInterface3 lam8 =  //중괄호와 리턴생략
				(x,y) -> x + y ;
		k = lam8.test(100, 200);
		System.out.println("k = " + k);
		
		
		///////////////////////////////////////
		LamdaTestInterface3 lam9 = 
				(x,y)-> x > y  ? x : y; //3항연산자
		k = lam9.test(100, 200);
		System.out.println("k = " + k);
				
			
		
		
		
		
	}//메인매서드
}//클래스

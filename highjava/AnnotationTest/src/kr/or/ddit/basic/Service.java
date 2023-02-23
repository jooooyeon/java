package kr.or.ddit.basic;

public class Service {

	@PrintAnnotation
	public void method1() {
		System.out.println("매서드 1에서 출력되었습니다");
	}

	@PrintAnnotation("%") // 밸류를 1개만셋팅할때 앞에 삭제가능(값만입력)
	public void method2() {
		System.out.println("매서드 2에서 출력되었습니다");
	}
	@PrintAnnotation(value = "#", count = 50)
	public void method3() {
		System.out.println("매서드 3에서 출력되었습니다");
	}

}

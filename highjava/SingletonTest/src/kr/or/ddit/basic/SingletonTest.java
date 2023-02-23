package kr.or.ddit.basic;

public class SingletonTest {
	public static void main(String[] args) {
		
//		MySingleton test1 = new MySingleton(); // new 명령 사용불가(싱글톤이라)
		
		//getInstance()메서드를 이용하여 객체 생성
		MySingleton test2 = MySingleton.getInstance(); //객체가 없어서 생성자입니다가 출력
		test2.displayText();							//디스플레이메소드출력
		
		MySingleton test3 = MySingleton.getInstance();	//기존에 있던것을 출력 ->알수있는 방법 : 주소값이 같음
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);
		
	}
}//객체 생성을 하나만 해서 같이 쓰는것을 싱글톤? 그렇기 때문에 여러개를 만들 필요가없다?

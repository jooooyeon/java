package kr.or.ddit.basic;

public class T02ThreadTest {
	public static void main(String[] args) {
		//멀티 스레드 프로그램 방식
		
		//방법1 : Thread 클래스를 상속한 클래스의 인스턴스를 생성한 후
		//		 이 인스턴스의 start()메서드를 호출한다.
		
		MyThread1 th1 = new MyThread1();//객체생성
		th1.start(); //만약 run으로 사용시 멀티쓰레드 사용이 불가임(메인스레드에서 싱글쓰레드처럼운영)
		
		//방법2 : Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후 
		//		이 인스턴스를 Thread객체를 생성할 때의 생성자의 파라미터로 넘겨준다
		//		이 때 생성된 Thread객체의 start()메서드를 호출한다
		//(다중상속이 안되기 때문에 인터페이스를 통해여 생성하는 경우가 있기에 방법2가 존재함)
		Runnable r = new MyRunnable();
		Thread th2 = new Thread(r);
		th2.start();
		
		
		//방법3 : 익명클래스를 이용하는 방법
		//   	Runnable 인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할 때 파라미터로 넘겨준다.
		//(한번만쓸때 사용 재사용가능성이 없을때)
		Thread th3 = new Thread(new Runnable() {
			
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					
					try {
						// Thread.sleep(시간) => 주어진 시간동안 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다. 즉 1000은 1초를 의미한다
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
		th3.start();
		
	}
}

//방법1
class MyThread1 extends Thread{
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. 즉 1000은 1초를 의미한다
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
//러너블 객체임 (불러오기전)
class MyRunnable implements Runnable{

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. 즉 1000은 1초를 의미한다
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
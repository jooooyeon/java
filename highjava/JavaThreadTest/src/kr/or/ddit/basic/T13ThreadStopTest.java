package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {
		
	/*	Thread th1 = new ThreadStop1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 	Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다
		 	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에 실행하는 프로그램에
		 	영양을 줄 수 있다. 그래서 현재는 비추천으로 되어 있다.
		 */
//		th1.stop();
		//((ThreadStop1)th1).setStoped(true); 	
		///////////////////////////////////////////////////
	
		Thread th2 = new ThreadStop2();
		th2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		th2.interrupt();//인터럽트 걸기
		th2.interrupt();//인터럽트 걸기
		th2.interrupt();//인터럽트 걸기
		th2.interrupt();//인터럽트 걸기
	}
}
class ThreadStop1 extends Thread{
	
	private boolean isStoped;
	
	
	
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}



	@Override
	public void run() {
		while(!isStoped) {
			System.out.println("스레드 처리 작업 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}

//interrupt() 메서드를 이용한 스레드 중지 방법
class ThreadStop2 extends Thread {
	
	@Override
	public void run() {


		// 방법2 => interrupt() 호출 되었는지 검사하여 처리하기
		while (true) {
			System.out.println("스레드 처리 작업 중...");

			/*
			 * //검사방법 1 => 스레드의 인스턴스 메서드를 이용하는 방법 if(this.isInterrupted()) {
			 * System.out.println("인스턴스용 isInterrupted()호출됨"); break; }
			 */

			// 검사방법 2 => 스레드 클래스의 정적 메서드를 이용하는 방법
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("정적메서드 interrupt()호출됨");
				
				System.out.println("if절 안에서... : " + Thread.interrupted());
				//Thread.interrupted()는 일회성임. 한번호출되고나면 다시 원래값으로 돌아감
				break;
			}
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}

}
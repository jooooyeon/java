package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티 스레드를 이용한 사용자 입력처리
 */
public class T06ThreadTest {
	
	//입력 여부를 확인하기 위한 변수 선언
	//모든 스레드에서 공용으로 사용할 변수
	public static boolean inputCheck = false;
	
	
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		th1.start();
		
		Thread th2 = new countDown();
		th2.start();
	}

}
/**
 * 사용자 입력을 처리하는 스레드
 */
class DataInput extends Thread{
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		T06ThreadTest.inputCheck=true;
		System.out.println("입력한 값은" +str +"입니다");
	}
}

/**
 * 카운트다운 처리를 위한 스레드
 */
class countDown extends Thread{
	@Override
	public void run() {
		for(int i=10; i>=1; i--) {
			
			if(T06ThreadTest.inputCheck) {
				return; //메소드(종료)빠져나감  / break는 포문만 빠져나감
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다");
		System.exit(0); //프로그램을 종료시키는 명령
	}
}
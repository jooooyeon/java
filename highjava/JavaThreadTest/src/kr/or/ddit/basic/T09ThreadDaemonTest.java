package kr.or.ddit.basic;

/**
 	데몬스레드 예제
 */
public class T09ThreadDaemonTest {
	public static void main(String[] args) {
		
		Thread th = new AutoSaveThread();
		//일반스레드를 보조하는 /일반스레드를 위해 존재하는 =>데몬스레드
		//일반스레드가 없으면 데몬스레드는 스스로 사라짐
		//일반스레드가 있어야 데몬스레드는 의미가 있음
		//데몬스레드로 설정하기(start() 메서드를 호출하기 전에 설정해야 한다.)
		th.setDaemon(true);//false면 일반스레드임. 디폴드값이 false임으로지금까지 설정하지 않았었음
		
		th.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println("작업 "+ i);
				
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("메인스레드 종료...");
		
		
	}
}
/**
 * 자동저장하는 기능을 제공하는 스레드(3초에 한번씩 저장하기)
 */
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); // 저장기능호출  / 3초동안 잠들었다가 깨어나서 저장=3초에 1번씩저장 / 무한루프
		}
	}
	
}
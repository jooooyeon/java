package kr.or.ddit.basic;

/**
 * 스레드의 수행시간 체크해보기
 * @author PC-11
 *
 */
public class T03ThreadTest {
	public static void main(String[] args) {
		
		Thread th = new Thread(new MyRunner());
		
		//UTC(Universal Time Coordinated 협정 세계 표준시)를 사용하여 1970년 1월 1일
		//0시 0분 0초를 기주으로 경과한 시간을 밀리세컨드(1/1000초)단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		//System.out.println(System.currentTimeMillis());
		
		th.start(); // th 쓰레드 작업 시작
		
		try {
			th.join(); //현재 실행중인 스레드에서 작업중인 스레드(th스레드)가 종료될때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간(ms) : " + (endTime - startTime));
		
	}
}


/**
 * 1~10억 까지의 합계를 구하는 스레드 클래스
 * 2번 러너블 인터페이스 구현방식
 */
class MyRunner implements Runnable{

	@Override
	public void run() {
		long sum =0;
		for(int i=1;i<=1000000000;i++) {
			sum+=i; // 동일 sum=sum+i
		}
		System.out.println("합계 : " + sum); 
	}
	
}

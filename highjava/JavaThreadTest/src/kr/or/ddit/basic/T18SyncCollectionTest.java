package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * 동기화 처리한 리스트 예제
 */
public class T18SyncCollectionTest {
/*
 	Vector, Hashtable 등의 예전부터 존재하던 Collection클래스들은 내부에 동기화 처리가 되어 있다.
 	그런데, 최근에 새로 구성된 클래스들은 동기화 처리가 되어 있지 않다. 그래서동기화가 필요한 프로그램에서
 	이런 컬렉션 클래스들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
 */
	//동기화 처리를 하지 않은 경우...
	private static List<Integer> list1 = new ArrayList<>();
	
	
	//동기화 처리를 한 경우...
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	private static List<Integer> list3 = new Vector<>();
	
	public static void main(String[] args) {
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
			//	synchronized (list1) {
					
				for(int i=1; i<=10000; i++) {
				//	list1.add(i);
			//		list2.add(i);
					list3.add(i);
				}
				}
		
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r), new Thread(r)				
		};
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("list1의 개수 : " + list1.size());
//		System.out.println("list2의 개수 : " + list2.size());
		System.out.println("list3의 개수 : " + list3.size());
		
		
	}
	
}

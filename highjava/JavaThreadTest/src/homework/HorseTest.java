//package homework;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//public class HorseTest {
//	static int rank;
//	public static void main(String[] args) {
//		List<Horse> horses = new ArrayList<>();
//		horses.add(new Horse("1번말"));
//		horses.add(new Horse("2번말"));
//		horses.add(new Horse("3번말"));
//		
//		List<Thread> ths = new ArrayList<>();
//		for(Horse h : horses) {
//			ths.add(new HorseRace(h));
//		}
//		
//		for(Thread th : ths) {
//			th.start();
//		}
//		for(Thread th: ths) {
//			try {
//				th.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		Collections.sort(horses);
//		for(Horse h : horses) {
//			System.out.println(h);
//		}
//	}
//}
// 
//class HorseRace extends Thread {
//	String[] stadium;
//
//	Horse horse;
//	
//	
//
//	public HorseRace(Horse horse) {
//		stadium = new String[50];
//		this.horse=horse;
//		stadium[0] = ">";
//		for (int i = 1; i < stadium.length; i++) {
//			stadium[i] = "-";
//		}
//	}
//
//	@Override
//	public void run() {
//		String position="";//초기화
//		Random rand = new Random();
//		
//		for(int i=0;i<stadium.length;i++) {
//			System.out.print(horse.getName() + " ");
//			for(String str : stadium) {
//				System.out.print(str);
//			}
//			System.out.println();
//			
//			if(stadium[stadium.length-1].equals(">")) {
//				horse.setRank(++HorseTest.rank);
//				return;
//			}
//			
//			position = stadium[i];
//			stadium[i]=stadium[i+1];
//			stadium[i+1]=position;
//			
//			
//			
//			try {
//				Thread.sleep(rand.nextInt(100));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//		
//		
//		
//	}
//
//}
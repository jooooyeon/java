//package kr.or.ddit.basic;
//
//import javax.swing.JOptionPane;
//
//public class Ex {
//	
//	public static String result ="짐"; 
//	public static String a =""; 
//	
//	public static void main(String[] args) {
//		Thread th1 = new DataInput2();
//		th1.start();
//		
//		Thread th2 = new CountDown2();
//		th2.start();
//		
//		Thread th3 = new RSP();
//		th3.start();
//	}
//}
//
//
//class DataInput2 extends Thread{
//	@Override
//	public void run() {
//		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
//		System.out.println(str + "을 입력하셨습니다");
//	}
//}
//
//class CountDown2 extends Thread{
//	@Override
//	public void run() {
//		for(int i=5; i>1; i--) {
//			System.out.println(i);
//		//	if(Ex.result.equals()) {
//				System.out.println("비김");
//				return;
//			}if(Ex.result==Ex.a) {
//				System.out.println("비김");
//				return;
//			}
//			try {
//				Thread.sleep(1000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("5초가 지났습니다.");
//		
//		System.exit(0); //프로그램을 종료시키는 명령
//		Ex.result="짐";
//	}
//}
//class RSP extends Thread{
//	@Override
//	public void run() {
//		int result = (int) ((Math.random()*3)+1);
//		String a;
//		switch (result) {
//		case 1:
//			a = "가위";
//			break;
//			
//		case 2:
//			a = "바위";
//			break;
//
//		default :
//			a = "보";
//			break;
//		}
//	}
//}
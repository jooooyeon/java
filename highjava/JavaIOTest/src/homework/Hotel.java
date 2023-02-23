package homework;
//package homework;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Set;
//
//public class Hotel {
//	 private Scanner scan;
//	 private static Map<String, HotelVo> hotel;
//	 
//	 public Hotel() {
//		 scan = new Scanner(System.in);
//		 hotel = new HashMap<>();
//	 }
//	 
//	 public void hotelOpen() {  //메뉴를 출력하는 메서드
//		 System.out.println("************************************************");
//		 System.out.println("\t호텔 문을 열었습니다.\t");
//		 System.out.println("************************************************");
//		 while(true) {
//			 System.out.println("************************************************");
//			 System.out.println("\t어떤 업무를 하시겠습니까?\t");
//			 System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
//			 System.out.println("************************************************");
//			 System.out.println("메뉴선택 => ");
//			 int chooseNum = scan.nextInt();
//			 scan.nextLine();
//			 switch(chooseNum) {
//			 case 1:
//				 checkIn();  //체크인
//				 break;
//			 case 2:
//				 checkOut();  //체크아웃
//				 break;
//			 case 3:
//				 roomSit();  //객실상태
//				 break;
//			 case 4:
//				 System.out.println("************************************************");
//				 System.out.println("호텔 문을 닫았습니다.");
//				 System.out.println("************************************************");
//				 return;
//			 }
//		 }
//
//	 }
//	 
//	 private void checkIn() {  //객실 체크인 메서드
//		 System.out.println();
//		 System.out.println("어느 방에 체크인 하시겠습니까?");
//		 System.out.println("방 번호 입력 => ");
//		 String roomNum = scan.next();
//		 
//		 // 이미 체크인된 방인지 검사, get()메서드로 값을 가져올때 자료가 없으면 null을 반환
//		 if(hotel.get(roomNum) != null) {
//			 System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
//			 return;  // 메서드 종료
//		 }
//		 
//		 scan.nextLine();
//		 System.out.println("누구를 체크인하시겠습니까?");
//		 System.out.println("이름입력 => ");
//		 String name = scan.next();
//		 
//		 hotel.put(roomNum, new HotelVo(roomNum, name));
//		 System.out.println("체크인 되었습니다");
//	 }
//	 
//	 private void checkOut() {
//		 System.out.println();
//		 System.out.println("어느방을 체크아웃 하시겠습니까?");
//		 System.out.println("방번호 입력 =>");
//		 String roomNum = scan.next();
//		 if(hotel.remove(roomNum) == null) {
//			 System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
//		 } else {
//			 System.out.println("체크아웃 되었습니다");
//		 }
//	 }
//	 
//	 private void roomSit() {
//		 Set<String> keySet = hotel.keySet();
//		 
//		 if(keySet.size() == 0) {
//			 System.out.println("등록된 체크인 정보가 없습니다");
//		 } else {
//			 Iterator<String> it = keySet.iterator();
//			 while (it.hasNext()) {
//				 String roomNum = it.next();
//				 HotelVo h = hotel.get(roomNum);
//				 System.out.println("방번호 : " + h.getRoomNum() + ", 투숙객 : " + h.getName());
//			 }
//			 
//		 }
//		
//	 }
//	 
//
//private static void newFile() {
//	File hotel = new File("d:/D_Other/hotel/roomSit.bin");
//	if(hotel.exists()) {
//		return;
//	} else {
//		try {
//			ObjectOutputStream room = new ObjectOutputStream(
//									  new BufferedOutputStream(
//									  new FileOutputStream("d:/D_Other/hotel/roomSit.bin")));
//					
//		} catch(IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//}
//
//private static void receive() {
//	
//	try {
//		ObjectInputStream ois;
//		ois = new ObjectInputStream(
//				new BufferedInputStream(
//						new FileInputStream("d:/D_Other/hotel/roomSit.bin")));
//		
//		Object obj;
//		
//		while((obj = ois.readObject()) !=null) {
//			hotel = (Map<String, HotelVo>)obj;
//		}
//				
//	} catch(IOException ex) {
//		ex.printStackTrace();
//	} catch(ClassNotFoundException e) {
//		e.printStackTrace();
//	}
//}
//
//private static void close() {
//	
//	try {
//		ObjectOutputStream oos = new ObjectOutputStream(
//								  new BufferedOutputStream(
//								  new FileOutputStream("d:/D_Other/hotel/roomSit.bin")));
//		oos.writeObject(hotel);
//				
//	} catch(IOException ex) {
//		ex.printStackTrace();
//	}
//}
//
//	 
//	 
//public static void main(String[] args) {
//		new Hotel().hotelOpen();
//		newFile();
//		receive();
//	} 
//	 
//}
//
//class HotelVo {
//	private String roomNum;
//	private String name;
//	
//	public HotelVo(String roomNum, String name) {
//		super();
//		this.roomNum = roomNum;
//		this.name = name;
//	}
//
//	public String getRoomNum() {
//		return roomNum;
//	}
//
//	public void setRoomNum(String roomNum) {
//		this.roomNum = roomNum;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	@Override
//	public String toString() {
//		return "HotelVo [roomNum=" + roomNum + ", name=" + name + "]";
//	}
//	
//}
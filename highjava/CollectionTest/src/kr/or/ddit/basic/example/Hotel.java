package kr.or.ddit.basic.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;


import java.util.Scanner;
import java.util.Set;

public class Hotel {
	
	Scanner sc = new Scanner(System.in);
	Map<Integer, HotelInfo> room = new HashMap<>();
	//Map<Integer, String> room;

	public void displayMenu() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
	
		int flag=0;
		while(flag==0) {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 =>");
		int sm = sc.nextInt();
		
		switch (sm) {
		case 1: 
			checkIn();			
			break;
		case 2: 
			checkOut();
			
			break;
		case 3:
			roomCondition();
			break;
		case 4:
			exit();
			flag = 1;
			break;

		default:
			System.out.println("잘못입력하셨습니다");
		}
		}
	}
	
	private void exit() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}



	private void roomCondition() {
		Set<Map.Entry<Integer, HotelInfo>> entrySet = room.entrySet();
		
		Iterator<Map.Entry<Integer, HotelInfo>> entryIt = entrySet.iterator();
		while(entryIt.hasNext()) {
			Entry<Integer, HotelInfo> entry = entryIt.next();
			System.out.println("방번호: " +entry.getKey() + ", 투숙객: " + entry.getValue()  );
		}
			
		
	}

	
	
	
	

	private void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력=>");
		Integer roomNum = sc.nextInt();
		
		if(room.get(roomNum)!=null) {
			room.remove(roomNum);
			System.out.println("체크아웃 되었습니다.");		
		}else if(room.get(roomNum) ==null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다");
		}
		
		
		
	}

		
	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력=>");
		Integer roomNum = sc.nextInt();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 =>");
		String name = sc.next();
		sc.nextLine();
	//	System.out.println(room);
		

		if(room.get(roomNum) !=null) {
			System.out.println(roomNum+"방에는 이미 사람이 있습니다.");
			return;
		}else {
			HotelInfo hi = new HotelInfo(roomNum, name);
			room.put(roomNum, hi);
			System.out.println("체크인 되었습니다");
			
		}
			
		
	}


}

class HotelInfo{
	private int roomNum;
	private String name;
	public HotelInfo(int roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return  name ;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(roomNum,name);
	}


	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		HotelInfo other = (HotelInfo) obj;
		return roomNum == other.roomNum && Objects.equals(name, other.name);
	}



	
	

}
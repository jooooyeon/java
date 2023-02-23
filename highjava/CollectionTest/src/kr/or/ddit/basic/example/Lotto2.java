package kr.or.ddit.basic.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int flag=0;
		while(flag==0) {
			
		
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 :");
		int menu = sc.nextInt();

		
		switch(menu) {
		case 1:
			System.out.println("Lotto 구입 시작");
			System.out.println("(1000원에 로또번호 하나입니다.)");
			System.out.print("금액 입력 : ");
			int price = sc.nextInt();
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			for(int i=1; i<=price/1000; i++) {
				Set<Integer> lottoNum = new HashSet<>();
			
			while(lottoNum.size()<6) {
				int num = (int) (Math.random()*45+1);
				lottoNum.add(num); 
			}
			
			System.out.println("로또번호"+i+lottoNum);
			}
			System.out.println("받은 금액은 " + price +"원이고 거스름돈은 " +price%1000 +"입니다.");
			flag=1;
			break;
			
		case 2:
			System.out.println("감사합니다");
			flag=1;
			break;
		default:
			System.out.println("잘못입력하셨습니다");
			
			
		}
		
		
		
	}
	}
}

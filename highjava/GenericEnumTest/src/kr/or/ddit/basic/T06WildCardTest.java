package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
	
	/**
	 * 장바구니 항목 조회를 위한 메서드(모든항목)
	 */
	public static void displayCartItemInfo(Cart<?> cart) {
		System.out.println("= 음식류 장바구니 항목 리스트=");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("-----------------------------");
		
	}
	/**
	 * 장바구니 항목 조회를 위한 메서드(음료나 그 하위 항목)
	 */
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
		System.out.println("= 음료류 장바구니 항목 리스트=");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("-----------------------------");
		
	}
	
	/**
	 * 장바구니 항목 조회를 위한 메서드(고기류나 그 상위 항목)
	 */
	public static void displayCartItemInfo3(Cart<? super Meat> cart) {
		System.out.println("= 고기류 또는 상위 장바구니 항목 리스트=");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("-----------------------------");
		
	}

	public static void main(String[] args) {

		Cart<Food> foodCart = new Cart<>();
		foodCart.add(new Meat("돼지고기", 5000));
		foodCart.add(new Meat("소고기", 50000));
		foodCart.add(new Juice("토마토쥬스", 1200));
		foodCart.add(new Coffee("마끼야또", 5000));
		
		Cart<Meat> meatCart = new Cart<>();
		meatCart.add(new Meat("돼지고기", 5000));
		meatCart.add(new Meat("소고기", 50000));
		
		Cart<Drink> drinkCart = new Cart<>();
		drinkCart.add(new Juice("토마토쥬스", 1200));
		drinkCart.add(new Coffee("마끼야또", 5000));
		
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
//		displayCartItemInfo2(foodCart);
//		displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
//		displayCartItemInfo3(drinkCart);
	}

}

class Food{
	private String name; //음식이름
	private int price; //음식 가격
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return this.name+ "(" + this.price + "원)";
	}
	
	
}


class Meat extends Food{
	public Meat(String name, int price) {
		super(name,price);
	}
}
class Drink extends Food{
	public Drink(String name, int price) {
		super(name,price);
	}
} 


class Juice extends Drink{
	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink{
	public Coffee(String name, int price) {
		super(name, price);
	}
}

class Cart<T> {
	private List<T> list;
	
	public Cart() {
		list= new ArrayList<>();
	}

	public List<T> getList() {
		return list;
	}
	
	public void add(T item) {
		list.add(item);
	}
	
	
}

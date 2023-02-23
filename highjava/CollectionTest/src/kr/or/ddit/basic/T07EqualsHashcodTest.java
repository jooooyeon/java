package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class T07EqualsHashcodTest {
	
	/*
	  해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다
	  해시함수에 의해 얻어지는 해시값, 해시코드, 해시 체크섬 또는 간단하게 해시라고 부른다.
	  
	  HashSet, Hashtable, HashMap과 같은 객체들을 사용할 경우
	  객체가 서로 같은지를 비교하기 위해 equals()매서드와 hashCode()메서드를 이용한다
	  그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야 한다.
	  HashSet, Hashtable, HashMap에서는 객체가 같은지 여부를 데이터를 추가할 때 검사한다.
	  
	  - equals()메서드는 두 객체의 내용(값)이 같은지 비교하는 메서드이고, 
	  - hashCode()메서드는 객체에 대한 해시코드값을 반환하는 메서드다.=>해시테이블 작성시 사용됨.
	  //두개같이 오버라이드해야함
	  
	  -equals()와 hashCode()메서드에 관련된 규칙
	  1. 두 객체가 같으면 반드시 같은 hashcode를 가져야 한다.
	  2. 두 객체가 같으면 equals()메서드를 호출했을때 true값을 반환해야한다.
	     즉, 객체 a,b가 같다면 a.equals(b)와 b.equals(a) 둘 다 true값을 반환해야 한다.
	   
	  3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
	  	 하지만, 두 객체가 같으면 반드시 hashcode가 같아야 한다.
	  4. equals()메서드를 override하려면 반드시 hashCode()메서드도 override 해야 한다. 
	  5. hashCode()는 기본적으로 힙메모리에 존재하는 각 객체에 대한 메모리 주소값을 기반으로 한 
	     정수값을 반환한다.
	     그러므로, hashCode()를 재정의 하지 않고서는 절대로 두 객체가 같은 해시코드가 반환될 수 없다.
	  
	  - hashCode()에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashcode를 만들어
	    낼 수 있다. 그래서 객체가 같지 않더라도 hashcode가 같을 수 있다.
	    
	    
	    //오브젝트클래스가 가지고있는 메소드임
	 */
	
		public static void main(String[] args) {
			//해시코드 충돌 -> 이때문에 equals를 같이써야함
			System.out.println("Aa : " + "Aa".hashCode());
			System.out.println("BB : " + "BB".hashCode());
			
			
		//3개의 펄스 객체 생성
		Person p1 = new Person(1,"홍길동");
		Person p2 = new Person(1,"홍길동");
		Person p3 = new Person(1,"이순신");
			
		
		
		
		//폴스 : equals가 오브젝트에서 제공하는 기능을 사용 -> 적절하게 오버라이드를 해야 원하는 값을 ㄱ할 수 있음 
		System.out.println("p1.equals(p2) : "+ p1.equals(p2));
		System.out.println("p1==p2 : "+ (p1==p2));
		
		
		
		Set<Person> hs = new HashSet<>();
		System.out.println("add(p1) 성공여부 : " + hs.add(p1));
		System.out.println("add(p2) 성공여부 : " + hs.add(p2));
		
		System.out.println("p1, p2등록 후 데이터");
		for(Person p : hs) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("add(p3) 성공여부 : " + hs.add(p3));
		
		System.out.println("p3등록 후 데이터");
		for(Person p : hs) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		System.out.println("remove(p2) 성공여부 : " + hs.remove(p2));
		
		System.out.println("remove(p2) 후 데이터");
		for(Person p : hs) {
			System.out.println(p.getId() + ":" + p.getName());
		}
		
		
		}

}



class Person{
	private int id;
	private String name;
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
/*	
	@Override
	public boolean equals(Object obj) {
		Person p = (Person) obj;
		return this.getId()==p.getId() && this.getName().equals(p.getName());
	}
	
	@Override
	public int hashCode() {
		//name+id하면 문자열이나옴->스트링객체가 만들어짐
		return (name + id).hashCode() ;
	}
*/	
	
	
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return id == other.id && Objects.equals(name, other.name);
	} 
	
	
	
	
	
	
	
}

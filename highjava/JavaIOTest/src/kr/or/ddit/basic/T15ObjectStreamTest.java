package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 객체 입출력을 위한 보조스트림 예제
 */
public class T15ObjectStreamTest {
	public static void main(String[] args) {
		
		Member mem1 = new Member("홍길동", 10, "대전");
		Member mem2 = new Member("이순신", 20, "광주");
		Member mem3 = new Member("일지매", 30, "대구");
		Member mem4 = new Member("성춘향", 40, "서울");
		
		ObjectOutputStream oos = null;
		
		try {
			//객체를 파일로 저장하기
			
			//출력용 스트림객체 생성하기
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream("d:/D_Other/memObj.bin")));
			//객체쓰기 작업
			oos.writeObject(mem1);// 직렬화
			oos.writeObject(mem2);// 직렬화
			oos.writeObject(mem3);// 직렬화
			oos.writeObject(mem4);// 직렬화
		
			System.out.println("쓰기 작업 완료...");
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		////////////////////////////////////////////////////////////////
		//역직렬화
		
		ObjectInputStream ois = null;
		
		try {
			//저장한 객체를 읽어와 화면에 출력하기
			
			ois = new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj = null;
			while((obj= ois.readObject()) != null) { //4번 돈다. 역직렬화 
				//일어온 데이터를 원래의 객체형으로 변환 후 사용한다 
				
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("-----------------------------");
			}
			
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}{
				
			}
		}
		
		
		
	}
}
/**
	회원 정보를 담기 위한 VO클래스
 */
//메소드가 없음 , Serializable타입을 사용하기 위해서 Serializable을 구현, 해당인터페이스에는 구현해야할 메소드가 없음
class Member implements Serializable{ 
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음
	// 구현하지 않으면 직렬화 작업시 예외 발생함.
	// 직렬화: 객체를 데이터로 저장할때 / 객체자체를 쭉늘어ㅌㅡ려놓음 // 객체를 입출력을 하려면 직렬화를 통해서 저장이되고 , 반대는 역직렬화(저장된객체를 꺼낼때?)
	
	/*
	 	Transient(일시적인,순간적인) => 직렬화가 되지 않을 멤버변수에 지정한다.
	 		직렬화대상제외				( * static 필드도 직렬화가 되지 않는다)
	 								직렬화가 되지 않는 멤버변수는 기본값으로 지정된다.
	 								(참조변수 : null, 숫자형변수:0)
	 */
	private String name;
	transient private int age;
	private String addr;
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
	
}
/*transient private int age;
 * 쓰기 작업 완료...
이름 : 홍길동
나이 : 0
주소 : 대전
-----------------------------
이름 : 이순신
나이 : 0
주소 : 광주
-----------------------------
이름 : 일지매
나이 : 0
주소 : 대구
-----------------------------
이름 : 성춘향
나이 : 0
주소 : 서울
-----------------------------
 */

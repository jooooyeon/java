package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/*
 * 부모가 Serializable 구현하지 않아 직렬화 불가할때
 * 자식이 Serializable구현하여 부모객체를 직렬화 시키는 것
 */
public class T16NonSerializableParentTest {
/*
 	부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
 	부모객체의 필드값 처리방법
 	
 	1. 부모클래스가 Serializable 인터페이스를 구현하도록 한다.
 	2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여 부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 	
 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = new ObjectOutputStream(
									new FileOutputStream("d:/D_Other/nonSerial.bin")); //저장
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); //직렬화가 일어나는 중
		
		oos.flush();//생략가능
		oos.close(); //닫음
		
		
		ObjectInputStream ois = new ObjectInputStream(
									new FileInputStream("d:/D_Other/nonSerial.bin"));//읽음
		
		Child child2 = (Child) ois.readObject(); //역직렬화
		
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();// 닫음
		
		
		
		
		
		
		
		
	}
}
/**
 * Serializable을 구현하지 않은 부모클래스
 */
class Parent{
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

/**
 * Serializable을 구현한 자식 클래스
 */
class Child extends Parent implements Serializable{
	private String ChildName;

	public String getChildName() {
		return ChildName;
	}

	public void setChildName(String childName) {
		ChildName = childName;
	}
	
	/**
	 * 직렬화 될 때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동 호출 되지 않음)
	 */
	//부모의 parentname을 직렬화하고싶음
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeUTF(getParentName());
		out.defaultWriteObject(); //기본 / write오브젝트 그거 호출해왕
	}
	/**
	 * 역직렬화 될 때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동 호출되지 않음)
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		setParentName(in.readUTF());
		in.defaultReadObject(); //기본 디폴드에저장된값
	}
	
}

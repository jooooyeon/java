package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 기능 보조스트림 예제
 */
public class T13DataIOStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
		
		//DataOutStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
		
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동"); 	//문자열 데이터 출력(변형된 UTF-8) //채팅할때사용
		dos.writeInt(17);		//정수형으로 데이터 출력 (인트 4바이트)
		dos.writeFloat(3.14f);	//실수형(Float)으로 데이터 출력(4바이트)
		dos.writeDouble(3.14);	//실수형(Double)으로 데이터 출력(8바이트)
		dos.writeBoolean(true); //논리형으로 출력(1바이트)
		System.out.println("출력완료...");
		
		dos.close();
		////////////////////////////////////////////////////////
		
		// 출력한 자료 읽어오기
		DataInputStream dis = new DataInputStream(
									new FileInputStream("d:/D_Other/test.dat"));
		
		System.out.println("문자열 자료 : " 		  + dis.readUTF());//채팅할때 ㅐ사용
		System.out.println("정수형 자료 : "		  + dis.readInt());
		System.out.println("실수형(Float) 자료 : "  + dis.readFloat());
		System.out.println("실수형(Double) 자료 : " + dis.readDouble());
		System.out.println("논리형 자료 : "         + dis.readBoolean()); //순서가바뀌면 안됨 
		
		dis.close();
		
		
		
		
		
		
		
	}
}

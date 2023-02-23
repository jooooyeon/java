package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09FileEncodingTest {
/*
 	인코딩 방식에 대하여...
 	
 	한글 인코딩 방식은 크게 UTF-8 과 EUC-KR 방식 두가지로 나누어 볼 수 있다.
 	원래 한글윈도우는 CP949 방식을 사용했는데 윈도우를 개발한 마이크로소프트사에서 EUC-KR 방식에서 확장하였기 때문에
 	MS949 라고도 부른다.
 	
 	한글윈도우의 메모장에서 말하는 ANSI인코딩이란, CP949(Code Page 949)를 말한다.
 	CP949는 EUC-KR의 확장이며, 하위 호환성이 있다.
 	- MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
 	- UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자 : 1byte, 한글 : 3byte) => 가변적
 	- US-ASCII => 영문전용 인코딩 방식
 	 //규격을 하나로 만들었음 -> 유니코드
 	  
 	ANSI는 영어를 표기하기 위해 만든 코드 규격으로 자체에 한글이 없었다가 나중에 EUC-KR, CP949 이라는 식으로 확장하면서
 	한글이 포함되었음.
 	
 	
 */
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
//			fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			// 파일 인코딩 정보를 이용하여 읽어오기
			// InputStreamReader 객체는 파일의 인코딩 방식을 지정할 수 있다.
		//	isr = new InputStreamReader(fis,"cp949");//보조스트림 바이트기반을->문자열로 변경 ,안시 -> cp949방식
//			isr = new InputStreamReader(fis,"UTF-8");//오류남 �ݰ����� 안시->UTF8 
//			isr = new InputStreamReader(fis,"MS949");//정상출력 안시->MS949 
//			isr = new InputStreamReader(fis,"MS949");//오류 utf-8 =>MS949
//			isr = new InputStreamReader(fis,"utf-8");//정상출력 utf-8 =>utf-8
			isr = new InputStreamReader(fis);//정상출력 utf-8 =>
			
			int data=0;
			while((data=isr.read()) != -1) {
				System.out.print((char)data);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				isr.close();//보조 스트림만 닫아도 된다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제2
 * (문자기반의 Buffered 스트림 예제)
 */
public class T12BufferdIOTest {//리더 아웃터(문자)
	public static void main(String[] args) {
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			//이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는
			//해당 '프로젝트 폴더'가 기본(현재)위치가 된다.
			fr = new FileReader("src/kr/or/ddit/basic/T11BufferedIOTest.java"); //상대경로 ./가 생략되어 있는 것임
					
//			int data = 0;
//			while((data=fr.read()) != -1) {
//				System.out.print((char)data);
//			}
			
			br = new BufferedReader(fr);
			
			String temp = "";
			int cnt = 1;
			while((temp = br.readLine()) != null) {
				System.out.printf("%4d : %s \n", cnt++, temp );
			}
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
//				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}

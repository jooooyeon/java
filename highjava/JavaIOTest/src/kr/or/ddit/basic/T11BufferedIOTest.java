package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 입출력 성능 향상을 위한 보조스트림 예제1
 * (바이트 기반의 Buffered 스트림예제)
 * BufferedOutputStream //버퍼기능제공
 */

public class T11BufferedIOTest {
	public static void main(String[] args) {
		//버퍼의 시스템 호출이 잦을수록 부담이 높음
		
		FileOutputStream fos= null;
		BufferedOutputStream bos = null; //파일아웃풋에 붙여씀
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt"); //텍스트파일 생성
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8kb)로 설정된다.
			
			//버퍼의 크기가 5인 스트림 객체 생성
			bos = new BufferedOutputStream(fos,5); //=5kb  , 버퍼사용 = 메모리를 잡아먹는다
			
			for(char ch='1'; ch<='9'; ch++) { //문자열 1부터 9  / 5개씩 2번
				System.out.println("쓰기 작업.");
				bos.write(ch);
			}
			
			bos.flush(); // 작업 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
						 // close시 자동으로 호출됨 (이전버전에는 무조건 작성/ 지금 버전은 자동적용되나봄)
							//버퍼가 다 차지 않아서 시스템에 보내지 못했지만 해당 메소드로 인해서 다 안찾음에도불구하고 내보낼 수 있음
			
			System.out.println("작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
//속성 이름 = 밸류 를 프로퍼티스 파일이라고 부름(텍스트?)
//
public class T02PropertiesTest {
/*
 	외부의 properties파일을 읽어와 Properties객체로 처리하기
 	
 */
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");//./res/db.properties와 같음 앞에 ./생략 
		//소스폴더res 소스폴더안에 넣은것은 컴파일을 해주어야 함 일반파일과는 다른점임
		
		try {
			// 파일 읽기를 수행할 FileInputStream객체 생성하기
			FileInputStream fis = new FileInputStream(file);
			
			
			// Properties 객체로 파일 내용 읽기
			// 파일 내용을 읽어와 속성이름과 속성값으로 분류하여 Properties 객체에 담아준다.
			prop.load(fis); //로드메서드가 필요한것이 파일인풋스트림임?
			
			//읽어온 자료 출력하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames(); //Enumeration이 이터레이터와 비슷한용도로 사용
			while(keys.hasMoreElements()) {  //hasnext와 비슷한원리  하나씩자료 꺼내기
				String key = keys.nextElement();
				String value = prop.getProperty(key); //겟프로퍼티 셋프로퍼디 두가지가있음
				System.out.println(key + " => " + value);
				
			}
			System.out.println("출력 끝...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

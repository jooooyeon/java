package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03ResourceBundleTest {
/*
 	ResourceBundle객체 => 확장자가 properties인 파일 정보를 읽어와 key값과 value값을
 						분리한 정보를 갖는 객체 
 						
 			=> 읽어올 파일은 'key값=value값' 형태로 되어 있어야 한다.
 */
	public static void main(String[] args) {
		/*
		 	ResourceBundle 객체 생성하기
		 	 => 파일을 지정할 때는 '파일명'만 지정하고 확장자는 지정하지 않는다.
		 	 	(확장자는 항상 properties이기 때문에...)
		 */
//		ResourceBundle bundle = ResourceBundle.getBundle("db");  //(프로퍼티스 파일명 작성)
																//리소스번들의 겟번들을 통해서 객체를 만들어줌 
																//"db"만 적었는데도 클래스패스루트에 있는 db.properties를 찾음
																//리소스번들 = 리소스가 뭉쳐있다 
																//메세지를 프로퍼티스파일에 관리를 하겠다?
																//리소스번들 : 로케(위치)에 따라 언어를 다르게 나오도록 하게 만들수도 있음
																//기본주소에 따라 안녕하세요가 출력됨
//		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.JAPAN);  //일본주소
		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.CHINA);  //중국주소
	
		
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement(); 
			//key값을 이용하여 value값을 가져오는 방법
			// => bundle객체변수.getString(key값);
			String value = bundle.getString(key); //관리하고있는 리소스데이터를 키값을 기준으로 꺼내올 수 있음
			
			System.out.println(key + " : " + value); 
			
		}

		System.out.println("출력 끝...");
		
/*
res에 있는 db.properties에서 꺼내옴
password : java
driver : oracle.jdbc.driver.OracleDriver
user : ljy92
url : jdbc:oracle:thin:@localhost:1521/xe
출력 끝...
 */
		
	}
	
}

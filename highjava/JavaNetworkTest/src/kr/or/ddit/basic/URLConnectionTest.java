package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//http를 통해서 자료를가져옴?
public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		
		//URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 추상 클래스
		
		//특정 서버 (예: naver서버)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		
		//Header 정보 가져오기
		
		//URLConnection객체 구하기
		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("content : " + urlConn.getContent());
		
		//전체 Header정보 출력하기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		
		System.out.println("----------------------------------------------");
		
//		InputStream is = (InputStream)urlConn.getContent(); //오브젝트리턴해서 캐스팅해줌
//		InputStreamReader isr = new InputStreamReader(is);
//		
//		int data = 0;
//		
//		while((data = isr.read()) != -1) {
//			System.out.print((char) data);
//		}
//		isr.close();
		
		
		
		//버퍼드 리더 사용
//		InputStream is = (InputStream)urlConn.getContent(); //오브젝트리턴해서 캐스팅해줌
		InputStream is = urlConn.getInputStream(); //위에와 같은 내용 이렇게 사용해도됨
		InputStreamReader isr = new InputStreamReader(is);
		
		BufferedReader br = new BufferedReader(isr);
		String str = ""; //초기화
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
		br.close();
	}
	
	

}

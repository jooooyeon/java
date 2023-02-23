package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession 객체를 제공하는 팩토리 클래스
 * @author PC-11
 *
 */
public class MyBatisUtil {
	
	private static SqlSessionFactory sqlSessionFactory; //객체변수 선언
	
	//한번만 실행되었으면 하는 내용 
	static {
		
		try {
			// 1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); //설정파일의 한글처리용
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			//1-2. 위에서 읽어온 Reader개체를 이용하여 실제 작업을 진행할 객체 생성하기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);//리더 객체가 필요함 그래서 위에서 리더개체를 생성
			
			rd.close(); // Reader 닫기
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//static
	/**
	 * SqlSession객체를 제공하는 팩토리 메서드
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance() {
		return sqlSessionFactory.openSession();  //오토커밋 폴스
	}
	
	

	/**
	 * SqlSession객체를 제공하는 팩토리 메서드
	 * @param autoCommit true이면 오토커핏 사용완료
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sqlSessionFactory.openSession(autoCommit);  //오토커밋 
	}
	
}

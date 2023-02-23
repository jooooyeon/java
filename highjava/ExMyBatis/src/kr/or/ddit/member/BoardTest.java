package kr.or.ddit.member;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardTest {
	public static void main(String[] args) {
		
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/board-config.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("인서트 시작");
		
		
		
		
	}
}

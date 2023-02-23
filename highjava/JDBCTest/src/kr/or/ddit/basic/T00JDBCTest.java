package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class T00JDBCTest {

/*
 	JDBC를 이용한 데이터베이스 처리 순서
 	
 	순서 : JDBC드라이버 로딩 => 해당 DB에 접속 > 질의 (SQL명령을 수행)
 			=> 질의 결과를 받아서 처리 => 종료(자원반납)
 			
 	1. JDBC드라이버로딩(오라클기준)
 	=> JDBC드라이버는 DB를 만든 회사에서 제공한다.
 		Class.forName("oracle.jdbc.driver.OracleDriver");
 		//옵션임 해도되고 안해도 된다. 이걸함으로 인해서 내가 사용할 드라이브가 프로젝트사용가능 확인함이 목적(빌드패스)
 		//리플렉션에서 포네임메소드사용했었음
 		//클래스 오브젝트로 얻어내고 싶음, 그 크래스가 없으면 클래스낫파운드익셉션이 발생 예외가 안터지면 성공
 		 
 		
 	2. 접속하기 : 접속이 성공하면 Connection 객체가 생성된다.
 		DriverManager.getConnection() 메서드 이용한다.
 		//드라이버매니저 클래스의 겟커넥션메서드 사용
 		 //커넥션타입의 객체를 리턴해줌 conn에 넣을 것임
 		  //커넥션객체가 있어야 스테이트먼트를 이용해서 쿼리를 ?할수있음?
 		  
 		
 	3. 질의 : Statement객체 또는 PreparedStatement 객체를 이용하여 SQL문장을 실행한다.
 		//crud쿼리날리려고 필요함?
 		 //
 	
 	4. 결과 : 
 		1) SQL문이 select인 경우 => ResultSet객체가 반환된다.
 			ResultSet객체를 통해 select결과를 가져올 수 있다.
 			
 		2) SQL문이 insert, update, delete일 경우 => 정수값을 반환한다.
 			(정수값은 보통 실행에 성공한 레코드 수를 의미한다)
 			//인트값리턴
 			//리턴값이 1이면 1개 성공 0이면 실패
 			//딜리트 쿼리를 날렷는데 5가 리턴되면 5개가 삭제된것임
 			
 			
 			 //클로즈 :파이널리에 꼭해줘야함
 */
	public static void main(String[] args) {
		
		//JDBC에 사용할 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		
		try {
			// 1. 드라이버 로딩(옵션)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB에 접속하기(Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "ljy92";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			//여기까지문제가 없으면 커넥셔ㄴ 객체생성할수있음
			
			// 3. Statement 객체 생성 => Connection 객체 생성
			stmt = conn.createStatement(); 
			
			// 4. SQL문을 Statement객체를 이용하여 실행하고 실행결과를 ResultSet객체로 반환한다.
			String sql = "select * from lprod";  // 실행할 SQL문
			
			/*//쿼리를 날릴때 2가지 종류가있음 / 리턴타입이 달라서 그럼 셀렉트는 리절트셋 나머지는 인트
			 	select 일 경우 => executeQuery() 이용.
			 	insert, update, delete 일 경우 => executeUpdate() 이용한다.
			 	
			 */
			
			rs = stmt.executeQuery(sql); //리턴 : 리절트 셋 / 
			
			/*
			 	rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고 
			 	그 곳에 자료가 있으면 true, 없으면 false를 반환함
			 */
			while(rs.next()) {
				// 컬럼의 데이터를 가져오는 방법
				// 한줄 컬럼단위로 꺼내옴?
				// 방법1) rs.get자료형이름("컬럼명")
				// 방법2) rs.get자료형이름(컬럼번호) => 컬럼번호는 1번부터 시작
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString(3));
				System.out.println("-------------------------------------------");
			}
			System.out.println("출력 끝...");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			// 6. 종료 (사용했던 자원을 모두 반납한다.)
			if(rs != null)try {rs.close();}catch(SQLException e) {}
			if(stmt != null)try {stmt.close();}catch(SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
			if(conn != null)try {conn.close();}catch(SQLException e) {}
			//만든순서 거꾸로 클로즈하는것이 안전한 방법
		}
		

	
	
	
	}
}

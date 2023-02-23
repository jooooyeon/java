package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 드라이버 로딩, Connection객체 생성 및 자원 반납 기능제공 클래스
 */
public class JDBCUtil {
	static {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");//드라이버 로딩(옵션)
				System.out.println("드라이버 로딩 완료!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();//오류발생시 드라이버가 없는것임
			}
	}//스태틱 블럭 안에 있는 애들은 JDBC유틸클래스가 실행할때(호출되서 실행할때?) 제일 처음 딱한번 실행됨
	
	
	/**
	 * DB커넥션 연결
	 * @return Connection 객체
	 */
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", 
					"ljy92",
					"java");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패!!!");
			return null;
		}
		
	}
	/**
	 * 자원반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, 
							 Statement stmt, 
							 PreparedStatement pstmt, 
							 ResultSet rs) {
					if(rs != null)try {rs.close();}catch(SQLException e) {}
					if(stmt != null)try {stmt.close();}catch(SQLException e) {}
					if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
					if(conn != null)try {conn.close();}catch(SQLException e) {}
					
	}
	
	
}

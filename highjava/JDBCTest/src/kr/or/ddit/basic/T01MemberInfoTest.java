package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.or.ddit.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T01MemberInfoTest {

	// 모두 인터페이스임
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);
	
	
	
	
	//log4j2로깅을 위한 로거 생성하기
	private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);
	
	

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 자료 입력
				insertMember();
				break;
			case 2: // 자료 삭제
				deleteMember();
				break;
			case 3: // 자료 수정
				updateMember();
				break;
			case 4: // 전체 자료 출력
				displayAllMember();
				break;
			case 5: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 5);
	}

	/**
	 * 전체 회원 정보를 출력하기 위한 메서드
	 */
	private void displayAllMember() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주  소");
		System.out.println("----------------------------------------------");
		
		//저장된 자료는 db에 있음 db에 잇는자료를 꺼내야함
		//db에 있는자료를 꺼내와야하기 때문에 jdbc코딩해야함
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember order by mem_id ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql); //select이기 때문에 executeQuery 사용
			
			while(rs.next()) {
				
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
				System.out.println("----------------------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		System.out.println("출력 작업 끝.");
	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteMember() {//키값인 회원아이디만 받으면 삭제가능
		System.out.println();
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.print("회원ID>> ");
		String memId = scan.next(); 
		
		//JDBC
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			//물음표값넣기
			pstmt.setString(1, memId);

			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 정보 삭제 작업 성공!");
			}else {
				System.out.println(memId + "회원 정보 삭제 작업 실패!!! / 없는 회원번호임");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		
		
		
		
		
		
		
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		boolean isExist = false;
		String memId="";
		do {
			System.out.println();
			System.out.println("수정할 회원정보를 입력하세요.");
			System.out.print("회원ID>> ");
			memId = scan.next(); // 

			isExist = checkMember(memId); // 파라미터로 멤아이디

			if (!isExist) {
				System.out.println("회원ID가 " + memId + "인 회원이 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		} while (!isExist);// 존재하는것을 찾을때까지 반복
		

		System.out.print("회원 이름 >> ");
		String memName = scan.next();

		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼비우기

		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql =" update MYMEMBER "
				    	+ " set mem_name = ?,"
				    	+ "    mem_tel = ?,"
				    	+ "    mem_addr = ? "
				    	+ " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate(); //insert, update, delete 일 경우 => executeUpdate() 이용한다
			
			if(cnt > 0) {
				
				
				System.out.println(memId + "회원 정보 수정 성공!");
			}else {
				System.out.println(memId + "회원 정보 수정 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		

	}

	
	
	/**
	 * 회원정보를 추가하기 위한 메서드
	 */
	private void insertMember() {

		boolean isExist = false;
		String memId="";
		do {
			System.out.println();
			System.out.println("새롭게 등록할 회원정보를 입력하세요.");
			System.out.print("회원ID>> ");
			memId = scan.next(); // memId키값임 중복이되면 안됨

			isExist = checkMember(memId); // 파라미터로 멤아이디

			if (isExist) {
				System.out.println("회원ID가 " + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
		} while (isExist);// 두와일문이 계속 돔 isExist가 트루면 중복아이디라 무한반복 임

		System.out.print("회원 이름 >> ");
		String memName = scan.next();

		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine(); // 버퍼비우기

		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();

		try {
			
			conn = JDBCUtil3.getConnection();
/*			// 드라이버 로딩(옵션)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB에 접속하기(Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "ljy92";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);
*/			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)" +
//					"values ('a001', '홍길동', '111-1111', '대전')";
		                	"values (?, ?, ?, ?)";
			
			SQL_LOGGER.debug("쿼리 : " +  sql);
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId); //물음표에 값넣기
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			PARAM_LOGGER.debug("파라미터 값 : " + "memID => " + memId + ", memName => " + memName + 
					", memTel => " + memTel + " ,memAddr => " + memAddr);
			
			
			int cnt = pstmt.executeUpdate();
			
			RESULT_LOGGER.debug("결과 값 : " + cnt);
			
			
			if(cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공!");
			}else {
				System.out.println(memId + "회원 추가 작업 실패!!!");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

	}
	/**
	 * 회원정보가 존재하는지 확인하기 위한 메서드
	 * @param memId 회원번호를 체크할 회원 아이다
	 * @return 회원이 존재하면 true, 존재하지 않으면 false반환함.
	 */
	private boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt from MYMEMBER "
					+ "where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");//cnt가 컬럼명이라서 "cnt 사용"
//				cnt = rs.getInt(1);
			}
			
			if(cnt >0) {
				isExist=true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		
		return isExist;
	}

	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}

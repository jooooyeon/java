package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

//기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
public class noticeboardTest {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	private Scanner scan = new Scanner(System.in);
	
	
	public void displayMenu() {
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("1. 전체 목록 출력");
		System.out.println("2. 새글 작성");
		System.out.println("3. 수정");
		System.out.println("4. 삭제");
		System.out.println("5. 검색");
		System.out.println("6. 종료");
	}
	
	
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 전체 목록 출력
				viewAllNoticeboard();
				break;
			case 2: //새글작성
				createNewNoticeboard();
				break;
			case 3: //수정
				updateNoticeboard();
				break;
			case 4: //삭제
				deleteNoticeboard();
				break;
			case 5: //검색
				searchNoticeboard();
				break;
			case 6: //종료
				endNoticeboard();
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
	}
	
		}while(choice!=6);

}//start 끝

	


	private void endNoticeboard() {
		System.out.println("종료되었습니다");
		
	}


	private List<BoardVO> searchNoticeboard() {
		
		List<BoardVO> bList = new ArrayList<>();
		
		scan.nextLine();
		System.out.println("검색할 내용을 입력하세요");
		System.out.println("번호 >> ");
		String boardNo = scan.nextLine().trim();
		System.out.println("제목 >> ");
		String boardTitle = scan.nextLine().trim();
		System.out.println("작성일자 >> ");
		String boardDate = scan.nextLine().trim();
		System.out.println("작성자 >> ");
		String boardWriter = scan.nextLine().trim();
		System.out.println("내용 >> ");
		String boardContent = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardDate(boardDate);
		bv.setBoardContent(boardContent);
		
//		System.out.println(boardNo+ "\t" + boardTitle+ "\t" +boardDate + "\t" + boardWriter+ "\t" +boardContent );
//		
		 
		
//		"select * from mymember where 1=1 "
//		and mem_addr like '%' || ? || '%'
//		
		
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from JDBC_BOARD where 1=1 ";
			
			if(bv.getBoardNo() != null && !bv.getBoardNo().equals("")) {
				sql += " and boardNo = ? ";
			}
			if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				sql += " and boardTitle like '%' || ? || '%' ";
			}
			if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				sql += " and BoardWriter like '%' || ? || '%' ";
			}
			if(bv.getBoardDate() != null && !bv.getBoardDate().equals("")) {
				sql += " and BoardDate like '%' || ? || '%' ";
			}
			if(bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				sql += " and BoardContent like '%' || ? || '%' ";
			}
			
			
			pstmt = conn.prepareStatement(sql);
			
			int index=1;
			if(bv.getBoardNo() != null && !bv.getBoardNo().equals("")) {
				pstmt.setString(index++, bv.getBoardNo());
			}
			if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				pstmt.setString(index++, bv.getBoardWriter());
			}
			if(bv.getBoardDate() != null && !bv.getBoardDate().equals("")) {
				pstmt.setString(index++, bv.getBoardDate());
			}
			if(bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				pstmt.setString(index++, bv.getBoardContent());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boardNo1 = rs.getString("boardNo");
				String boardTitle1 = rs.getString("boardTitle");
				String boardWriter1 = rs.getString("boardWriter");
				String boardDate1 = rs.getString("boardDate");
				String boardContent1 = rs.getString("boardContent");
				
				BoardVO bv2 = new BoardVO();
				bv2.setBoardNo(boardNo1);
				bv2.setBoardTitle(boardTitle1);
				bv2.setBoardWriter(boardWriter1);
				bv2.setBoardDate(boardDate1);
				bv2.setBoardContent(boardContent1);
				
				bList.add(bv2);
				
			}
			
			
		
			
			
		} catch (SQLException e) {
			throw new RuntimeException("정보검색중 오류",e);
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
			
		}
		return bList;
		
		
	}


	private void deleteNoticeboard() {
		System.out.println("삭제할 게시글 정보입력");
		System.out.println(" 번호 >>");
		String boardNo = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from JDBC_BOARD where BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println("게시글 삭제 완료");
			}else {
				System.out.println("게시글삭제 실패");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
		
		
		
	}


	private void updateNoticeboard() {
		boolean isExist = false;
		String boardNo = "";
		do {
			System.out.println("수정할 게시판의 번호를 입력하세요");
			System.out.print("번호입력>>");
			boardNo = scan.next();
			
			isExist = checkBoard(boardNo);
			
			if(!isExist) {
				System.out.println("번호가 존재하지 않습니다");
			}
			
		}while(!isExist);
		
		scan.nextLine();
		
		System.out.println("변경할 내용 작성 >>");
		String boardContent = scan.nextLine();
		
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " update jdbc_board "
						+ " set BOARD_CONTENT = ? "
						+ " where BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardContent);
			pstmt.setString(2, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println("내용수정성공");
			}else {
				System.out.println("내용수정실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
			
		};
		
	}
	
	

	
	
	private void createNewNoticeboard() {
	
			
			System.out.println("게시글 작성 시작");
			System.out.println(">>제목 입력");
			String boardTitle = scan.next();
			
			System.out.println(">>작성자 입력");
			String boardWriter = scan.next();
			
	
			System.out.println(">>내용 입력");
			String boardContent = scan.next();
			
			scan.nextLine();
			
			try {
				
				conn = JDBCUtil.getConnection();
				
				String sql = "insert into jdbc_board( BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT ) " 
						  + "values( board_seq.nextVal , ?, ?, sysdate, ? )" ;
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardTitle);
				pstmt.setString(2, boardWriter);
				pstmt.setString(3, boardContent);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt <0 ) {
					System.out.println("게시글작성실패!!!");
				}else {
					System.out.println("게시글작성성공");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn, stmt, pstmt, rs);
			}
			
		
	}


	// 전체 목록 출력
	private void viewAllNoticeboard() {
		
		System.out.println("번호\t제목\t작성날짜\t\t\t작성자\t내용");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " select * from jdbc_board order by 1 ";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String boardNo = rs.getString("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				
				System.out.println(boardNo+ "\t" + boardTitle+ "\t" +boardDate + "\t" + boardWriter+ "\t" +boardContent );
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}//viewAllNoticeboard 끝
	
	
	private boolean checkBoard(String boardNo) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select  count(*) from JDBC_BOARD where BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			if(cnt > 0) {
				isExist = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
			
		}
		
		return isExist;
	}
	
	
	
	public static void main(String[] args) {
		noticeboardTest nb = new noticeboardTest();
		nb.start();
	}
}


class BoardVO{
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardDate;

	
	
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	
	
	
	
}



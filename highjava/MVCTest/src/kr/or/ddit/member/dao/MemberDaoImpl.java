package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {

	// 모두 인터페이스임
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {

			conn = JDBCUtil3.getConnection();
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)" + 
													"values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId()); // 물음표에 값넣기
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());

			cnt = pstmt.executeUpdate();


		} catch (SQLException ex) {
			throw new RuntimeException("회원정보 입력중 예외발생!", ex);
			//얘는 옵션임 반드시 잡아야할 필요가 없음 이 아이로 처리하면 코딩하기가 편하당
			//이걸받는 이 오류가 필요한 누군가는 처리한다?
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql =" update MYMEMBER "
				    	+ " set mem_name = ?,"
				    	+ "    mem_tel = ?,"
				    	+ "    mem_addr = ? "
				    	+ " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			 cnt = pstmt.executeUpdate(); //insert, update, delete 일 경우 => executeUpdate() 이용한다
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException("회원정보 수정중 예외 발생!",e);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
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
			}
			
			if(cnt >0) {
				isExist=true;
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException("회원정보 존재여부 체크 중 예외발생!",ex);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		
		return isExist;
	}
		

	@Override
	public int deleteMember(String memId) {
		
		int cnt=0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			//물음표값넣기
			pstmt.setString(1, memId);

			 cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("회원정보 삭제중 예외발생!", e);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		
		List<MemberVO> memList = new ArrayList<>();
		
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
				
				MemberVO mv = new MemberVO();
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("전체 회원 정보 조회 중 예외 발생!", e);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember where 1=1 ";
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' "; 
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(memId);
				mv2.setMemName(memName);
				mv2.setMemTel(memTel);
				mv2.setMemAddr(memAddr);
				
				memList.add(mv2);
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("회원정보 검색 중 예외발생!",e);
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

}

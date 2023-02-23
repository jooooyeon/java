package kr.or.ddit.member;

import java.util.List;

//컨트롤러에 호출
public interface BoardServiceControl {
		
		public int registBoard(BoardVO bv); 
		
		//업데이트/수정
		public int modifyBoard(BoardVO bv);
		
		// 일치하는 번호가 있는지 여부 확인
		public boolean checkBoardNo(String board_id);
		
		//보드 삭제 
		public int removeBoard(String board_id);
		
		//전체검색
		List<BoardVO> selectAllBoard();
		
		
		//일부검색
		List<BoardVO> searchBoard(BoardVO bv);
		
}
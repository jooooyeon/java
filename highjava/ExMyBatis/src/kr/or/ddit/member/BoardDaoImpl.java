package kr.or.ddit.member;

import java.util.List;

public class BoardDaoImpl extends MyBatisBoardDao implements BoardServiceDao {

	private static BoardServiceDao boardDao;
	
	public BoardDaoImpl() {
	}
	
	public static BoardServiceDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = insert("board.insertBoard", bv);
		return 0;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = update("board.updateBoard", bv);
		return 0;
	}

	@Override
	public boolean checkBoardNo(String board_id) {
		boolean isEx = false;
		
		int cnt = selectOne("board.checkBoard",board_id);
		
		if(cnt > 0) {
			isEx = true;
		}
		
		return isEx;
	}

	
	@Override
	public int deleteBoard(String board_id) {
		int cnt = delete("board.deleteBoard", board_id);
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = selectList("board.searchBoard",bv);
		return boardList;
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		List<BoardVO> boardList = selectList("board.selectAllBoard");
		return boardList;
	}

}

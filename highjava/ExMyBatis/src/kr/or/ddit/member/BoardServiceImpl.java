package kr.or.ddit.member;

import java.util.List;

public class BoardServiceImpl implements BoardServiceControl {

	private BoardServiceDao boardDao;
	
	private static BoardServiceControl boardService;
	
	
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		
	}
	
	public static BoardServiceControl getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	@Override
	public int registBoard(BoardVO bv) {
		
		int cnt = boardDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public int modifyBoard(BoardVO bv) {
		return boardDao.updateBoard(bv);
	}


	@Override
	public int removeBoard(String board_id) {
		return boardDao.deleteBoard(board_id);
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		return boardDao.selectAllBoard();
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		return boardDao.searchBoard(bv);
	}

	@Override
	public boolean checkBoardNo(String board_id) {
		return boardDao.checkBoardNo(board_id);
	}

	
}

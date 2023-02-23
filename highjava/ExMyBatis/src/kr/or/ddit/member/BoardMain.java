package kr.or.ddit.member;

import java.util.List;
import java.util.Scanner;



public class BoardMain {

	private BoardServiceControl boardService;

	private Scanner scan = new Scanner(System.in);

	public BoardMain() {
		boardService = BoardServiceImpl.getInstance();

	}

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 입력");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 게시글 출력");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu();
			choice = scan.nextInt();
			switch (choice) {

			case 1:
				insertBoard();
				break;
			case 2:
//				deleteBoard();
				break;
			case 3:
//				updateBoard();
			case 4:
				displayAllBoard();
			case 5:
//				searchBoard();
			case 6:
				System.out.println("종료합니다");
				break;
			default:
				System.out.println("번호잘못입력");

			}
		} while (choice != 6);
	}

	private void displayAllBoard() {
		List<BoardVO> memList = boardService.selectAllBoard();

		if (memList.size() == 0) {
			System.out.println("조회된 데이터가 없습니다");
		} else {
			for (BoardVO mv : memList) {
				System.out.println(	mv.getBoard_no()+ mv.getBoard_title() + "\t" + mv.getBoard_content() +mv.getBoard_id() + mv.getBoard_date() );
				System.out.println("---------------------------------------------");
			}
		}
		System.out.println("출력 작업 끝.");
	}

	

	private void insertBoard() {
		BoardVO bv = new BoardVO();
		boolean isEx = false;
		boolean flag = false;
		while (true) {

			System.out.println("회원번호 입력>>");
			String memid = scan.next();

			isEx = boardService.checkBoardNo(memid);

			if (isEx) {
				System.out.println("해당회원있음");

			} else {
				System.out.println("게시글 제목");
				String board_title = scan.next();
				System.out.println("게시글 내용");
				String board_content = scan.next();
				bv.setBoard_title(board_title);
				bv.setBoard_content(board_content);

				int cnt = boardService.registBoard(bv);

				if (cnt > 0) {
					System.out.println("게시글작성 성공!");
				} else {
					System.out.println("게시글작성 실패");
				}
			}

//		if(isEx) {
//			System.out.println("가입정보가 없습니다 다시입력해주세요");
//		}else {
//			System.out.println("게시글 제목");
//			String board_title = scan.next();
//			System.out.println("게시글 내용");
//			String board_content = scan.next();
//			
//			bv.setBoard_title(board_title);
//			bv.setBoard_content(board_content);
//			
//			int cnt = boardService.registBoard(bv);
//			
//			if(cnt > 0) {
//				System.out.println("게시글작성 성공!");
//			}else {
//				System.out.println("게시글작성 실패");
//			}
//			
//			break;
//		}
		}

	}

	public static void main(String[] args) {
		BoardMain bmain = new BoardMain();
		bmain.start();
	}
}

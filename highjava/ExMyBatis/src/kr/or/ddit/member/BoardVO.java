package kr.or.ddit.member;

public class BoardVO {
	private String board_no;
	private String board_id;
	private String board_title;
	private String board_content;
	private String board_date;
	
	public String getBoard_no() {
		return board_no;
	}

	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}

	

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", board_id=" + board_id + ", board_title=" + board_title
				+ ", board_content=" + board_content + ", board_date=" + board_date + "]";
	}

	

}

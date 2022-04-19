package kr.or.ddit.basic;

public class MemoVO {
	private String no;
	private String title;
	private String writer;
	private String date;
	private String content;
	
	
	public String getNo() {
		return no;
	}





	public void setNo(String no) {
		this.no = no;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getWriter() {
		return writer;
	}





	public void setWriter(String writer) {
		this.writer = writer;
	}





	public String getDate() {
		return date;
	}





	public void setDate(String date) {
		this.date = date;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	@Override
	public String toString() {
		return "MemoVO [no=" + no + ", title=" + title + ", writer=" + writer + ", date=" + date + ", content="
				+ content + "]";
	}
	
}

package model;

public class CommentVO extends UserVO{
	private int cid;
	private int bid;
	private String writer;
	private String cdate;
	private String contents;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		return "CommentVO [cid=" + cid + ", bid=" + bid + ", writer=" + writer + ", cdate=" + cdate + ", contents="
				+ contents + ", getPhoto()=" + getPhoto() + ", getUname()=" + getUname() + "]";
	}
	
	
	
	


}


package model;

public class ReviewVO {
	private int rid;
	private String gid;
	private String uid;
	private String content;
	private String revDate;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRevDate() {
		return revDate;
	}
	public void setRevDate(String revDate) {
		this.revDate = revDate;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [rid=" + rid + ", gid=" + gid + ", uid=" + uid + ", content=" + content + ", revDate="
				+ revDate + "]";
	}
	
	
	
	
}

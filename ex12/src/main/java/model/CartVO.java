package model;

public class CartVO extends GoodsVO{
	private String uid;
	private int qnt;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	
	@Override
	public String toString() {
		return "CartVO [uid=" + uid + ", qnt=" + qnt + ", getGid()=" + getGid() + ", getTitle()=" + getTitle()
				+ ", getPrice()=" + getPrice() + ", getImage()=" + getImage() + "]";
	}
}
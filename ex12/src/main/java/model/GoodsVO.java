
package model;

public class GoodsVO {
	private int ucnt;
	private int fcnt;
	private int rcnt;
	private String gid;
	private String title;
	private int price;
	private String brand;
	private String image;
	private String regDate;
	
	
	public int getRcnt() {
		return rcnt;
	}
	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}
	public int getUcnt() {
		return ucnt;
	}
	public void setUcnt(int ucnt) {
		this.ucnt = ucnt;
	}
	public int getFcnt() {
		return fcnt;
	}
	public void setFcnt(int fcnt) {
		this.fcnt = fcnt;
	}
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	@Override
	public String toString() {
		return "GoodsVO [ucnt=" + ucnt + ", fcnt=" + fcnt + ", rcnt=" + rcnt + ", gid=" + gid + ", title=" + title
				+ ", price=" + price + ", brand=" + brand + ", image=" + image + ", regDate=" + regDate + "]";
	}
	
	
	
}

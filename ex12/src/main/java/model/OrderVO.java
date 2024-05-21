package model;

public class OrderVO extends CartVO{
	private String pid;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "OrderVO [pid=" + pid + ", getQnt()=" + getQnt() + ", getGid()=" + getGid() + ", getPrice()="
				+ getPrice() + "]";
	}

	
	
}

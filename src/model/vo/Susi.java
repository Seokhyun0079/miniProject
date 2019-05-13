package model.vo;

public class Susi {

	private String menuTitle;
	private String menuImage;
	private int price;

	public Susi() {

	}

	public Susi(String menuTitle, String menuImage, int price) {
		super();
		this.menuTitle = menuTitle;
		this.menuImage = menuImage;
		this.price = price;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getMmenuImage() {
		return menuImage;
	}

	public void setMmenuImage(String mmenuImage) {
		this.menuImage = mmenuImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString(){
		return menuTitle + " " + price + " " +menuImage;
	}
	
	
}

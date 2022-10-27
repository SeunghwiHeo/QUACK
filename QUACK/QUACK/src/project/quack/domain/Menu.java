package project.quack.domain;

public class Menu {

	private String menuNum; // 음식번호
	private String menuName; // 음식이름
	private String price; // 가격
	private boolean isAvailable; // 주문상태(가능/불가능)

	public Menu() {

	}

	public Menu(String menuNum, String menuName, String price, boolean isAvailable) {
		super();
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	public String getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(String menuNum) {
		this.menuNum = menuNum;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Menu [menuNum=" + menuNum + ", menuName=" + menuName + ", price=" + price + ", isAvailable="
				+ isAvailable + "]";
	}

}

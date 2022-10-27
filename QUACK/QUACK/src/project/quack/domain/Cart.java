package project.quack.domain;

public class Cart {

	private String menuNum; // 음식번호
	private String menuName; // 음식이름
	private String price; // 가격
	private String amount;// 수량
	// private boolean isAvailable; // 주문상태(가능/불가능)

	public Cart(String menuNum, String menuName, String price, String amount) {
		super();
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.price = price;
		this.amount = amount;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Cart [menuNum=" + menuNum + ", menuName=" + menuName + ", price=" + price + ", amount=" + amount + "]";
	}

}

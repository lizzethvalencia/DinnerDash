package ordering;

public class MenuItem {
	private String itemName;
	private Double itemPrice;
	private int cookTime;
	
	public MenuItem(String itemName, Double itemPrice, int cookTime) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.cookTime = cookTime;
	}
	
	public String getName() {
		return itemName;
	}
	
	public Double getPrice() {
		return itemPrice;
	}
	
	public int getCookTime() {
		return cookTime;
	}
	
	public String toString() {
		String info = itemName + "   " + itemPrice;
		return info;
	}
}

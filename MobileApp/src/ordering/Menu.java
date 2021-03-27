package ordering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
	private File orders;
	private BufferedWriter orderLogger;
	private FileWriter orderLoggerStream;
	private int count;
	private String inputFile;
	private ArrayList<MenuItem> menuItems;
	private StringTokenizer tokens;
	private Scanner sc;
	
	public Menu(File menuName) throws FileNotFoundException {
		menuItems = new ArrayList<MenuItem>();
		sc = new Scanner(menuName);
		count = 1;
		inputFile = menuName.getName();
	}
	
	public void readInputFile() {
		while(sc.hasNextLine()) {
			String item = sc.nextLine();
			tokens = new StringTokenizer(item, ";");
			while(tokens.hasMoreTokens()) {
				String itemName = tokens.nextToken();
				Double itemPrice = Double.parseDouble(tokens.nextToken());
				int itemCookTime = Integer.parseInt(tokens.nextToken());
				MenuItem createItem = new MenuItem(itemName, itemPrice, itemCookTime);
				menuItems.add(createItem);
			}
		}
		sc.close();
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	
	public void logOrder(ArrayList<MenuItem> itemsOrdered, double orderPrice) throws IOException {
		orderLoggerStream = new FileWriter(getFileInstance(),true);
		orderLogger = new BufferedWriter(orderLoggerStream);
			
		orderLogger.write("Order number" + count + ", ");
		count++;
		
		for (MenuItem item: itemsOrdered) 
			orderLogger.write(item.toString() + "\n");
		
		orderLogger.write("Total Price is: $" + orderPrice + "\n \n");
		orderLogger.close();
	}
	
	private File getFileInstance() {
		if (orders == null) {
			String fileName = inputFile + " " + "ItemOrders.txt";
			orders = new File(fileName);
		}
		return orders;
	}
}

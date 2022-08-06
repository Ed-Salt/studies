import java.util.LinkedList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StockLinkedList implements StockList {
	
	LinkedList<StockItem> stockList = new LinkedList<StockItem>();
		
	@Override
	public void addItem(StockItem item) {
		stockList.add(item);
	}

	@Override
	public void deleteItem(String itemID) {
		for (StockItem item : stockList) {
			if (item.getItemID().equals(itemID)) {
				stockList.remove(item);
			}
		}
	}

	@Override
	public void updateItemPrice(String itemID, double price) 
	{
		for (StockItem item : stockList) {
			if (item.getItemID().equals(itemID)) {
				item.setPrice(price);
			}
		}
	}

	@Override
	public void updateItemQuantity(String itemID, int quantity) {
		for (StockItem item : stockList) {
			if (item.getItemID().equals(itemID)) {
				item.setQuantity(quantity);			
			}
		}
	}

	@Override
	public void updateReOrderLevel(String itemID, int reOrderLevel) {
		for (StockItem item : stockList) {
			if (item.getItemID().equals(itemID)) {
				item.setReOrderLevel(reOrderLevel);
			}
		}
	}

	@Override
	public String formatStockList() {
		String fString = "ItemID    Description         Price     Qnty      Re-Order Level \n"
				+ "******    ***********         *****     ****      ************** \n";
		for (StockItem item : stockList) {
			fString = fString + item.format() + "\n";
		}
		return fString;
	}

	@Override
	public String formatReOrderList() {
		String fString = "ItemID    Description         Price     Qnty      Re-Order Level \n"
				+ "******    ***********         *****     ****      ************** \n";
		for (StockItem item : stockList) {
			if (item.getReOrderLevel() > item.getQuantity()) {
				fString = fString + item.format() + "\n";
			}
		}
		return fString;
	}

	@Override
	public void loadStockData(String filename)
	{
		 try (FileInputStream input = new FileInputStream("C:\\Users\\salte\\Documents\\CS\\ICP-1023\\Programming Project\\" + filename + ".txt")) {    
			 int i = input.read();
	         boolean done = false;
	         while (!done) {
	          	String iD = "";
               	for (int j = 0; j < 5; j++) {
               		iD += ((char)input.read());
               	}                
               	i = input.read();
               	String desc = "";
               	for (int j = 0; j < 20; j++) {
               		desc += ((char)input.read());
               	}
               	i = input.read();
               	String prc = "";
               	for (int j = 0; j < 10; j++) {
               		prc += ((char)input.read());
               	}
               	i = input.read();
               	String quant = "";
               	for (int j = 0; j < 10; j++) {
               		i = ((char)input.read());
               		if (Character.isDigit(i)) {
               			quant += (char)i;
               		}
               	}
               	i = input.read();
               	String level = "";
               	for (int j = 0; j < 10; j++) {
               		i = ((char)input.read());
               		if (Character.isDigit(i)) {
               			level += (char)i;
               		}
               	}
               	i = input.read();
               	StockItem savedItem = new StockItem(iD, desc, Double.valueOf(prc), Integer.valueOf(quant), Integer.valueOf(level));
               	addItem(savedItem);
               	i = input.read();
	           	if ((char)i == '*') {
		           	input.close();  
		           	done = true;
	           	}
	         }
				System.out.println("\nLoaded from file:\n"
						+ "C:\\Users\\salte\\Documents\\CS\\"
						+ "ICP-1023\\Programming Project\\" + filename + ".txt\n");
	     }
		 catch(Exception e) {
			 System.out.println(e);    
	     }
	}

	@Override
	public void saveStockData()
	{
		try(FileOutputStream output = new FileOutputStream("C:\\Users\\salte\\Documents\\CS\\ICP-1023\\Programming Project\\StoredStockList.txt")){
			for(StockItem item: stockList) {
				output.write("-".getBytes());
				output.write((item.getItemID() + "_").getBytes());
				output.write(String.format("%-20s" + "_", item.getItemDesc()).getBytes());
				output.write(String.format("%-10s" + "_", Double.toString(item.getPrice())).getBytes());
				output.write(String.format("%-10s" + "_", Integer.toString(item.getQuantity())).getBytes());
				output.write(String.format("%-10s" + "-", Integer.toString(item.getReOrderLevel())).getBytes());
			}
			output.write("*".getBytes());
			output.close();    
			System.out.println("\nSaved to file:\n"
					+ "C:\\Users\\salte\\Documents\\CS\\"
					+ "ICP-1023\\Programming Project\\StoredStockList.txt\n");
		}
		catch (FileNotFoundException e) {
			 System.out.println(e);    
		}
		catch (IOException e) {
			 System.out.println(e);    
		}
	}

}

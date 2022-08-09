
public class StockItem {
	String itemID;
	String itemDesc;
	double price;
	int quantity;
	int reOrderLevel;
	
	StockItem(String iD, String desc, double prc, int quant, int level) 
	{
		itemID = iD;
		itemDesc = desc;
		price = prc;
		quantity = quant;
		reOrderLevel = level;
	}
	
	public String toString()
	{
		return getClass().getName() + "[itemID:" + itemID + ", itemDesc:" + itemDesc + ", price:" + price +
				", quantity:" + quantity + ", reOrderLevel:" + reOrderLevel + "]";
	}
	
	public String format()
	{
		return String.format("%-10s", itemID) + 
				String.format("%-20s", itemDesc) +
				String.format("%-10s", price) +
				String.format("%-10s", quantity) +
				String.format("%-10s", reOrderLevel);
	}
	
	public String getItemID()
	{
		return itemID;
	}
	
	public String getItemDesc()
	{
		return itemDesc;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public int getReOrderLevel()
	{
		return reOrderLevel;
	}
	
	public void setPrice(double prc)
	{
		price = prc;
	}
	
	public void setQuantity(int quant)
	{
		quantity = quant;
	}
	
	public void setReOrderLevel(int level)
	{
		reOrderLevel = level;
	}

}

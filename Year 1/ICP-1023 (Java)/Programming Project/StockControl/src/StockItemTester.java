
public class StockItemTester {

	public static void main(String[] args) 
	{
		StockItem item1 = new StockItem("H0554", "Hard Drive 500GB", 31.99, 278, 100);
		System.out.println("Stock Item Tester\n*****************\n");
		System.out.println("1. Check 'get...' functions\n");
		System.out.println("Expected:\nH0554\nHard Drive 500GB\n31.99\n278\n100\n");
		System.out.println("Actual:\n" + item1.getItemID() + "\n" + item1.getItemDesc() + "\n" +
		item1.getPrice() + "\n" + item1.getQuantity() + "\n" + item1.getReOrderLevel());
		item1.setPrice(39.99);
		item1.setQuantity(134);
		item1.setReOrderLevel(150);
		System.out.println("2. Check 'set' and 'toString' functions\n"
				+ "(changing 31.99 -> 39.99, 278 -> 134, 100-> 150)\n");
		System.out.println("Expected:\nStockItem[itemID:H0554, itemDesc:Hard Drive 500GB, "
				+ "price:39.99, quantity:134, reOrderLevel:150]");
		System.out.println("Actual: \n" + item1.toString() + "\n");
		System.out.println("3. Check 'format' function");
		System.out.println("Expected:\nH0554     " + "Hard Drive 500GB    "
				+ "39.99     " + "134       " + "150       ");
		System.out.println("Actual: \n" + item1.format() + "\n");
}

}

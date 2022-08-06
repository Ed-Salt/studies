
public class StockListTester {

	public static void main(String[] args) 
	{
		StockLinkedList stockList1 = new StockLinkedList();
		StockItem item1 = new StockItem("H0554", "Hard Drive 500GB", 31.99, 278, 100);
		StockItem item2 = new StockItem("H1072", "Hard Drive 1TB", 43.99, 140, 50);
		StockItem item3 = new StockItem("S0201", "SS Drive 256GB", 61.99, 97, 20);
		stockList1.addItem(item1);
		stockList1.addItem(item2);
		stockList1.addItem(item3);
		System.out.println("Stock List Tester\n*****************\n");
		System.out.println("1. Check 'addItem' and 'formatStockList' functions\n");
		System.out.println("Expected:\nItemID    Description         Price     Qnty      Re-Order Level \n" + 
				"******    ***********         *****     ****      ************** \n" + 
				"H0554     " + "Hard Drive 500GB    " + "31.99     " + "278       " + "100       \n" +
				"H1072     " + "Hard Drive 1TB      " + "43.99     " + "140       " + "50        \n" +
				"S0201     " + "SS Drive 256GB      " +	"61.99     " + "97        " + "20        \n");
		System.out.println("Actual: \n" + stockList1.formatStockList() + "\n");
		stockList1.deleteItem(item2.getItemID());
		System.out.println("2. Check 'deleteItem' function\n(removing H1072 - item2)\n");
		System.out.println("Expected:\nItemID    Description         Price     Qnty      Re-Order Level \n" + 
				"******    ***********         *****     ****      ************** \n" + 
				"H0554     " + "Hard Drive 500GB    " + "31.99     " + "278       " + "100       \n" +
				"S0201     " + "SS Drive 256GB      " +	"61.99     " + "97        " + "20        \n");
		System.out.println("Actual: \n" + stockList1.formatStockList() + "\n");
		stockList1.updateItemPrice(item1.getItemID(), 36.99);
		stockList1.updateItemQuantity(item1.getItemID(), 67);
		stockList1.updateReOrderLevel(item3.getItemID(), 75);
		System.out.println("3. Check 'update...' functions\n");
		System.out.println("Expected:\nItemID    Description         Price     Qnty      Re-Order Level \n" + 
				"******    ***********         *****     ****      ************** \n" + 
				"H0554     " + "Hard Drive 500GB    " + "36.99     " + "67        " + "100       \n" +
				"S0201     " + "SS Drive 256GB      " +	"61.99     " + "97        " + "75        \n");
		System.out.println("Actual: \n" + stockList1.formatStockList() + "\n");
		System.out.println("4. Check 'formatReOrderList' function\n");
		System.out.println("Expected:\nItemID    Description         Price     Qnty      Re-Order Level \n" + 
				"******    ***********         *****     ****      ************** \n" + 
				"H0554     " + "Hard Drive 500GB    " + "36.99     " + "67        " + "100       \n");
		System.out.println("Actual: \n" + stockList1.formatReOrderList() + "\n");
		System.out.println("5. Check 'save/loadStockData' functions\n");
		System.out.println("Expected:\n\nSaved to file:\n" + 
				"C:\\Users\\salte\\Documents\\CS\\" + 
				"ICP-1023\\Programming Project\\StoredStockList.txt\n\n" +
				"\nLoaded from file:\n" +
				"C:\\Users\\salte\\Documents\\CS\\" +
				"ICP-1023\\Programming Project\\StoredStockList.txt\n\n" +
				"ItemID    Description         Price     Qnty      Re-Order Level \n" + 
				"******    ***********         *****     ****      ************** \n" + 
				"H0554     " + "Hard Drive 500GB    " + "36.99     " + "67        " + "100       \n" +
				"S0201     " + "SS Drive 256GB      " +	"61.99     " + "97        " + "75        \n");
		System.out.println("Actual:");
		stockList1.saveStockData();
		StockLinkedList stockList2 = new StockLinkedList();
		stockList2.loadStockData("StoredStockList");
		System.out.println(stockList2.formatStockList() + "\n");
	}

}

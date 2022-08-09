
public class StockListApp {

	public static void main(String[] args) 
	{
		//create the stock list object
		StockList stockList = new StockLinkedList();
				
		//create an interface
		StockListCLI stockListCLI = new StockListCLI(stockList);
				
		//display the menu
		stockListCLI.doMenu();
	}

}

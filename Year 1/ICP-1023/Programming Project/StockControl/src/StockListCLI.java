import java.util.Scanner;

public class StockListCLI 
{
	
		private static StockList stock = null;
			
		//initialises the stockList
		public StockListCLI(StockList aStockList)
		{
			stock = aStockList;
		}
				
		//prints the menu and takes the users choice
		public void doMenu()
		{
			Scanner in = new Scanner(System.in);
			int choice = 0;
			//loops until 8 (quit) is chosen
			while (choice != 8) {
				System.out.print("Registry Main Menu\n******************\n\n"
						+ "1. Add an item\n"
						+ "2. Delete an item\n"
						+ "3. Update item price\n"
						+ "4. Update item quantity\n"
						+ "5. Update re-order level\n"
						+ "6. Print stock list\n"
						+ "7. Print re-order list\n"
						+ "8. Exit\n"
						+ ":> ");
				choice = in.nextInt();
				//validation
				while (choice > 8 || choice < 1) {
					System.out.print("Invalid input!\n\n:>");
					choice = in.nextInt();			
				}
				if (choice == 1) {
					doAddItem();
				}
				else if (choice == 2) {
					doDeleteItem();
				}
				else if (choice == 3) {
					doUpdateItemPrice();
				}
				else if (choice == 4) {
					doUpdateItemQuantity();
				}
				else if (choice == 5) {
					doUpdateItemReOrderLevel();
				}
				else if (choice == 6) {
					doPrintStockList();
				}
				else if (choice == 7) {
					doPrintReOrderList();
				}
			}
		}
		
		//asks for item details
		//creates the item and adds to the stock list
		public static void doAddItem()
		{
			Scanner in = new Scanner(System.in);
			String cont = "Y";
			while (cont.equals("Y")) {
				System.out.println("\n\nAdd New Item\n************\n");
				System.out.printf("%-25s:> ", "Enter ID");
				String iD = in.next();
				in.nextLine();
				System.out.printf("%-25s:> ", "Enter Description");
				String desc = in.nextLine();
				System.out.printf("%-25s:> ", "Enter Price");
				double prc = in.nextDouble();
				System.out.printf("%-25s:> ", "Enter Quantity");
				int quant = in.nextInt();
				System.out.printf("%-25s:> ", "Enter Re-Order Level");
				int level = in.nextInt();
				StockItem anItem = new StockItem(iD, desc, prc, quant, level);
				stock.addItem(anItem);
				System.out.printf("%-20s:> ", "Enter another? [Y/N]");
				cont = in.next().toUpperCase();
				//validation
				while (!cont.equals("Y") && !cont.equals("N")) {
					System.out.println("Invalid input!\n");
					cont = in.next().toUpperCase();
				}
			}
		}
		
		//asks for an item ID
		//removes the item from the stock with the same ID
		public void doDeleteItem()
		{
			Scanner in = new Scanner(System.in);
			String cont = "Y";
			while (cont == "Y") {
				System.out.println("\n\nDelete Item\n***********\n");
				System.out.printf("%-20s:> ", "Enter item's ID");
				String iD = in.next();
				stock.deleteItem(iD);
				System.out.printf("%-20s:> ", "Delete another? [Y/N]");
				cont = in.next().toUpperCase();
				while (!cont.equals("Y") && !cont.equals("N")) {
					System.out.println("Invalid input!\n");
					cont = in.next().toUpperCase();
				}
			}
		}
		
		//asks for an item ID
		//updates the item's price with the same ID
		public void doUpdateItemPrice()
		{
			Scanner in = new Scanner(System.in);
			String cont = "Y";
			while (cont == "Y") {
				System.out.println("\n\nUpdate Price\n************\n");
				System.out.printf("%-25s:> ", "Enter item's ID");
				String iD = in.next();
				System.out.printf("%-25s:> ", "Enter item's new price");
				double prc = in.nextDouble();
				stock.updateItemPrice(iD, prc);
				System.out.printf("%-25s:> ", "Update another's price? [Y/N]");
				cont = in.next().toUpperCase();
				while (!cont.equals("Y") && !cont.equals("N")) {
					System.out.println("Invalid input!\n");
					cont = in.next().toUpperCase();
				}
			}
		}
		
		//asks for an item ID
		//updates the item's quantity with the same ID
		public void doUpdateItemQuantity()
		{
			Scanner in = new Scanner(System.in);
			String cont = "Y";
			while (cont == "Y") {
				System.out.println("\n\nUpdate Quantity\n***************\n");
				System.out.printf("%-25s:> ", "Enter item's ID");
				String iD = in.next();
				System.out.printf("%-25s:> ", "Enter item's new quantity");
				int quant = in.nextInt();
				stock.updateItemQuantity(iD, quant);
				System.out.printf("%-25s:> ", "Update another's quantity? [Y/N]");
				cont = in.next().toUpperCase();
				while (!cont.equals("Y") && !cont.equals("N")) {
					System.out.println("Invalid input!\n");
					cont = in.next().toUpperCase();
				}
			}
		}

		//asks for an item ID
		//updates the item's re-order level with the same ID
		public void doUpdateItemReOrderLevel()
		{
			Scanner in = new Scanner(System.in);
			String cont = "Y";
			while (cont == "Y") {
				System.out.println("\n\nUpdate Re-Order Level\n*********************\n");
				System.out.printf("%-32s:> ", "Enter item's ID");
				String iD = in.next();
				System.out.printf("%-32s:> ", "Enter item's new re-order level");
				int quant = in.nextInt();
				stock.updateReOrderLevel(iD, quant);
				System.out.printf("%-32s:> ", "Update another's quantity? [Y/N]");
				cont = in.next().toUpperCase();
				while (!cont.equals("Y") && !cont.equals("N")) {
					System.out.println("Invalid input!\n");
					cont = in.next().toUpperCase();
				}
			}
		}
		
		//prints the content of the stock list
		public void doPrintStockList()
		{
			System.out.println("\n\nPrint Stock List\n****************\n");
			System.out.println(stock.formatStockList());
		}
	
		//prints the content of the re-order list
		public void doPrintReOrderList()
		{
			System.out.println("\n\nPrint Re-Order List\n*******************\n");
			System.out.println(stock.formatReOrderList());
		}

}

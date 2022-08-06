import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DatabaseTextInterface {
			
	private DatabaseManager manager;
	private String table = "";
	private String form = "";
	private String ast = "*";
	
	public DatabaseTextInterface() throws ClassNotFoundException, IOException, SQLException {
			manager = new DatabaseManager();
	}
	
	public void printMenu() {
		System.out.println("\nMain Menu"
				+ "\n********************"
				+ "\n1. Students"
				+ "\n2. Modules"
				+ "\n3. Registrations"
				+ "\n4. Staff"
				+ "\n5. Teaches"
				+ "\n6. Reports"
				+ "\n0. Quit");
	}
	
	public int getMenuInput(int input) {
		Scanner in = new Scanner(System.in);
		while (input < 0 || input > 6) {
			System.out.print("\n:> ");
			input = in.nextInt();
		}
		System.out.println();
		switch(input) {
		case 1:
			table = "Student";
			form = "%-9s%-24s%-20s";
			ast = "******** "+
				  "*********************** " +
				  "***************************";
			break;
		case 2:
			table = "Module";
			form = "%-10s%-32s%-5s";
			ast = "********* "+
				  "***************************** " +
				  "*******";
			break;
		case 3:
			table = "Registration";
			form = "%-9s%-10s";
			ast = "******** "+
				  "********";
			break;
		case 4:
			table = "Staff";
			form = "%-9s%-24s%-20s";
			ast = "******** "+
					  "*********************** " +
					  "***************************";
			break;
		case 5:
			table = "Teaches";
			form = "%-9s%-10s";
			ast = "******** "+
				  "********";
			break;
		case 6:
			table = "Report";
			form = "%-9s%-24s%-16s";
			break;
		default:
			break;
		}
		return input;
	}
	
	public void printSubMenu() {
		System.out.println("\nSub-Menu (" + table +")"
				+ "\n********************"
				+ "\n1. Add " + table.toLowerCase()
				+ "\n2. Remove " + table.toLowerCase()
				+ "\n3. Update " + table.toLowerCase()
				+ "\n4. List " + table.toLowerCase()+"s"
				+ "\n0. Return to Main Menu");
	}
	
	public int getSubMenuInput(int input) {
		input = -1;
		Scanner in = new Scanner(System.in);
		while (input < 0 || input > 4) {
			System.out.print("\n:> ");
			input = in.nextInt();
		}
		System.out.println();
		return input;		
	}
	
	public void doChoice(int input) throws SQLException, IOException {
		Scanner in = new Scanner(System.in);
		String par1 = " ";
		String par2 = " ";
		String par3 = " ";
		switch(input) {
		case 1:
			if (!table.equals("Teaches") && !table.equals("Registration")) {
				System.out.println("\nPlease enter the " +table.toLowerCase()+ " id: ");
				while (par1.equals("") || 
						par1.equals(" ")) {
					System.out.print("\n:> ");
					par1 = in.nextLine();
				}
				System.out.println("\nPlease enter the " +table.toLowerCase()+ " name: ");
				while (par2.equals("") || 
						par2.equals(" ")) {
					System.out.print("\n:> ");
					par2 = in.nextLine();
				}
				if (!table.equals("Module")) {
					if (table.equals("Student"))
						System.out.println("\nPlease enter their degree scheme: ");
					else 
						System.out.println("\nPlease enter the staff grade: ");
					while (par3.equals("") || 
							par3.equals(" ")) {
						System.out.print("\n:> ");
						par3 = in.nextLine();
					}
				} else {
					System.out.println("\nPlease enter the credit value: ");
					boolean isNum = false;
					while (!isNum || par3.equals("") || 
							par3.equals(" ")) {
						isNum = true;
						System.out.print("\n:> ");
						par3 = in.nextLine();
						for (int i = 0; i < par3.length(); i++) {
							if (!Character.isDigit(par3.charAt(i))) {
								isNum = false;
							}
						}
					}
				}
			} else {
				if (table.equals("Registration")) 
					System.out.println("\nPlease enter the student's id: ");
				else
					System.out.println("\nPlease enter the staff id: ");
				while (par1.equals("") || 
						par1.equals(" ")) {
					System.out.print("\n:> ");
					par1 = in.nextLine();
				}
				System.out.println("\nPlease enter the module id: ");
				while (par2.equals("") || 
						par2.equals(" ")) {
					System.out.print("\n:> ");
					par2 = in.nextLine();
				}
			}
			manager.addToTable(table.toLowerCase(), par1, par2, par3);
			break;
		case 2:
			if (!table.equals("Registration") && !table.equals("Teaches")) {
				System.out.println("\nPlease enter the " +table.toLowerCase()+ " id: ");
				while (par1.equals("") || 
						par1.equals(" ")) {
					System.out.print("\n:> ");
					par1 = in.nextLine();
				}
			} else {
				if (table.equals("Registration"))
					System.out.println("\nPlease enter the student's id: ");
				else
					System.out.println("\nPlease enter the staff id: ");
				while (par1.equals("") || 
						par1.equals(" ")) {
					System.out.print("\n:> ");
					par1 = in.nextLine();
				}
			}
			manager.delFromTable(table.toLowerCase(), par1);
			break;
		case 3:
			if (!table.equals("Registration") && !table.equals("Teaches")) {
				System.out.println("\nPlease enter the " +table.toLowerCase()+ " id: ");
				while (par1.equals("") || 
						par1.equals(" ")) {
					System.out.print("\n:> ");
					par1 = in.nextLine();
				}
				List<String> entries = manager.getTable(table.toLowerCase(), "SELECT * FROM " + table.toLowerCase()+
						" WHERE " +table.toLowerCase()+ "_id = '" + par1 + "';");
				System.out.println("\nPlease enter the new " +table.toLowerCase()+ " name (leave blank to remain unchanged): ");
				while (par2.equals(" ")) {
					System.out.print("\n:> ");
					par2 = in.nextLine();
				}
				if (par2.equals("")) {
					par2 = entries.get(1); 
				}
				if (!table.equals("Module")) {
					String entryName = "";
					if (table.equals("Student")) 
						entryName = "degree_scheme";
					else
						entryName = "staff_grade";
					System.out.println("\nPlease enter the new "+entryName.replace('_', ' ')+" (leave blank to remain unchanged): ");
					while (par3.equals(" ")) {
						System.out.print("\n:> ");
						par3 = in.nextLine();
					}
					if (par3.equals("")) {
						par3 = entries.get(2);
					}
					manager.updateEntry(table.toLowerCase(), par1, entryName, par3);
				} else {
					System.out.println("\nPlease enter the new credits value (leave blank to remain unchanged): ");
					boolean isNum = false;
					while (!isNum) {
						isNum = true;
						System.out.print("\n:> ");
						par3 = in.nextLine();
						for (int i = 0; i < par3.length(); i++) {
							if (Character.isDigit(par3.charAt(i))) {
								isNum = false;
							}
						}
						if (par3.equals(""))
							isNum = true;
					}
					if (par3.equals("")) {
						par3 = entries.get(2);
					}
					manager.updateEntry(table.toLowerCase(), par1, "credits", par3);
				}
				manager.updateEntry(table.toLowerCase(), par1, table+"_name", par2);
			} else {
				String entryName = "";
				if (table.equals("Registration"))
					entryName = "student";
				else
					entryName = "staff";
				System.out.println("\nPlease enter the "+entryName+" id: ");
				while (par1.equals("") || 
						par1.equals(" ")) {
					System.out.print("\n:> ");
					par1 = in.nextLine();
				}
				List<String> entries = manager.getTable(table.toLowerCase(), "SELECT * FROM " + table.toLowerCase()+
						" WHERE "+entryName+"_id = '" + par1 + "';");
				System.out.println("\nPlease enter the new module id: ");
				while (par2.equals(" ")) {
					System.out.print("\n:> ");
					par2 = in.nextLine();
				}
				if (par2.equals("")) {
					par2 = entries.get(1);
				}
				manager.updateEntry(table.toLowerCase(), par1, "module_id", par2);
			}
			break;
		case 4:
			List<String> entries = new LinkedList<String>();
			entries = manager.getTable(table.toLowerCase(), "SELECT * FROM " + table.toLowerCase());
			for (int i = 0; i < entries.size(); i++) {
				System.out.println(ast);
				if (!table.equals("Registration") && !table.equals("Teaches")) {
					System.out.println(String.format(form,  entries.get(i), entries.get(i+1), entries.get(i+2)));
					i += 2;
				} else {
					System.out.println(String.format(form,  entries.get(i), entries.get(i+1)));
					i += 1;
				}
			}
			System.out.print("\nPress 'Enter' to continue...");
			System.in.read();
			break;
		default:
			break;
		}
	}
}

public class PackTester
{
	public static void main(String[] args) 
	{
		Pack pack1 = new Pack();
		System.out.println(pack1.toString());
		System.out.println("\n*Shuffle*\n");
		pack1.shuffle();
		System.out.println(pack1.toString());
	}
}
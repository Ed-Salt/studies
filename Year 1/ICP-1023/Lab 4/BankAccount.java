public class BankAccount
{

	private double balance;
	private String owner;	

	public BankAccount(double initialBalance, String newOwner)
	{
		balance = initialBalance;
		owner = newOwner;
	}
	
	public String toString()
	{ 
		return getClass().getName() +
		"[balance=" + balance + 
		"owner=" + owner + "]";
	}

}

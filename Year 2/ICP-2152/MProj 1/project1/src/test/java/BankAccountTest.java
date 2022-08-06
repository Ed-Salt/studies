import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class BankAccountTest {


	@Test
	public void testGetBalance() throws SQLException {
		BankAccount anAccount = new BankAccount(10, 250.00);
		
		double actual = anAccount.getBalance();
		assertEquals(250.00, actual, 0.001);
	}

	@Test
	public void testDeposit() throws SQLException {
		BankAccount anAccount = new BankAccount(10, 0.01);
		
		anAccount.deposit(149.99);
		
		double actual = anAccount.getBalance();
		assertEquals(150.00, actual, 0.001);
	}

	@Test
	public void testWithdraw() throws SQLException {
		BankAccount anAccount = new BankAccount(10, 200.00);
		anAccount.withdraw(100);
		
		double actual = anAccount.getBalance();
		assertEquals(100.00, actual, 0.001);
	}

	@Test
	public void testTransfer() throws SQLException {
		BankAccount anAccount = new BankAccount(10, 250.00);
		BankAccount anotherAccount = new BankAccount(11, 49.99);
		anAccount.transfer(50.01, anotherAccount);
		
		double actual = anAccount.getBalance();
		assertEquals(199.99, actual, 0.001);
		
		actual = anotherAccount.getBalance();
		assertEquals(100.00, actual, 0.001);
	}

}

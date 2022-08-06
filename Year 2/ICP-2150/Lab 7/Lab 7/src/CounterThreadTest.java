
public class CounterThreadTest extends Thread {

	
	public static void main(String[] args) 
	{	
		Counter c = new Counter();
		
		int n = 100000;
		
		CounterThread ct1 = new CounterThread(c,n);
		CounterThread ct2 = new CounterThread(c,n);
		
		ct1.start();
		ct2.start();
		/*
		try {
			ct1.join();
			ct2.join();
		}
		catch (InterruptedException e) {
			System.out.println("Error during join");
		}
		*/
		System.out.println("The value of the counter = "
				+ c.getCount() + ", : expected value is "
				+ 2 * n);
	}
}

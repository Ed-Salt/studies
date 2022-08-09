
public class PrimitiveCalculatorImpl implements Calculator {

	public int add(int a, int b) {
		return a+b;
	}

	public int subtract(int a, int b) {
		return a-b;
	}

	public int multiply(int a, int b) {
		return a*b;
	}

	public int integerDivide(int a, int b) {
		return a/b;
	}
	
	public void dummy( ) {
		System.out.println("Dummy!");
	}

}

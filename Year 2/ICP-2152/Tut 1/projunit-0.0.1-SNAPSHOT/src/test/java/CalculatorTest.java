import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calc = new PrimitiveCalculatorImpl();
		testAddWithCalc(calc);
		
		calc = new RegisterMathCalculatorImpl();
		testAddWithCalc(calc);
	}

	private void testAddWithCalc(Calculator calc) {
		int actual = calc.add(10, 10);
		assertEquals(20, actual);
		
		actual = calc.add(10, -10);
		assertEquals(0, actual);
		
		actual = calc.add(-10, 10);
		assertEquals(0, actual);
		
		actual = calc.add(-10, -10);
		assertEquals(-20, actual);
	}
	
	@Test
	public void testSubtract() {
		Calculator calc = new PrimitiveCalculatorImpl();
		int actual = calc.subtract(10, 10);
		assertEquals(0, actual);
		
		actual = calc.subtract(10, -10);
		assertEquals(20, actual);
		
		actual = calc.subtract(-10, 10);
		assertEquals(-20, actual);
		
		actual = calc.subtract(-10, -10);
		assertEquals(0, actual);
	}

	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public void testIntegerDivide() {
		fail("Not yet implemented");
	}

}

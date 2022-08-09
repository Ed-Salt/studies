
public class RegisterMathCalculatorImpl implements Calculator {

	public int add(int a, int b) {
		return a+b;
	}

	public int subtract(int a, int b) {
		return a-b;
	}

	public int multiply(int a, int b) {
		int answer = 0;
		for (int i = 0; i < b; i++) {
			answer = add(answer, a);
		}
		return answer;
	}

	public int integerDivide(int a, int b) {
		int answer = 0;
		while (a >= b) {
			answer++;
			a = subtract(a, b);
		}
		return answer;
	}

}

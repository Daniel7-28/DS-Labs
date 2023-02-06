public class Lab01P1Wrapper {

	private static class Arithmetic{

		int a = 0;
		int b = 0;

		public Arithmetic(int a, int b){
			this.a=a;
			this.b=b;
		}

		public int getA() {return a;};
		public void setA(int a) {this.a = a;}
		public int getB() {return b;};
		public void setB(int b) {this.b = b;}

		public int add() {
			return a + b;
		}

		public int substract() {
			return a - b;
		}

		public int multiply() {
			return a * b;
		}

		public double divide() {
			return a/b;
		}
	}

	public static class Calculator extends Arithmetic{
		public Calculator(int a, int b) {
			super(a, b);
		}
	}
}


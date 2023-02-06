
public class Lab02P1Wrapper {

	/**
	 * Exercise #1
	 * Returns the quotient obtained by dividing two integers
	 * by RECURSIVELY subtracting one integer from the other
	 *  
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static long div(int dividend, int divisor) {
		if(divisor == 0) {
			return 0;
		}
		else if(dividend - divisor == 0) {
			return 1;
		}
		else if(dividend < divisor) {
			return 0;
		}
		return (1 + div(dividend - divisor, divisor)); 
	}
}

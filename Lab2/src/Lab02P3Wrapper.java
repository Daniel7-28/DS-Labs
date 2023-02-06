import java.util.ArrayList;

public class Lab02P3Wrapper {

	/**
	 * Exercise #3
	 * Returns a NEW String that replaces the nth occurrence of a given (from) 
	 * pattern by another (to) pattern in a given string (text).
	 * The method must return a new String even if the pattern does not appear in the text.
	 * 
	 * YOUR METHOD MUST BE RECURSIVE.  Non-recursive methods will receive half the given credit.
	 * 
	 * HINT: The startsWith method from the String class may come in handy
	 * WARNING: YOU CANNOT USE THE replace nor replaceAll methods from the String class.
	 *
	 * @param from - Pattern to look for
	 * @param to - Pattern to replace from with
	 * @param text - text to look for from and replace with to
	 * @param nth - nth occurrence of from in text
	 * @return - the updated string with the replaced pattern
	 */
	public static String replace(String from, String to, String text, int nth) {
	if(nth == 0 || text.length() == 0) return text;
	if(text.startsWith(from) && nth > 1) {
		return (from + replace(from, to, text.substring(from.length()),nth-1));
	}
	if (text.startsWith(from) && nth == 1) {
		return to + replace(from, to, text.substring(from.length()),nth - 1);
	}
	if(!text.startsWith(from)) {
		return text.substring(0,1) + replace(from, to, text.substring(1), nth);
	}
		return "";
	}
}
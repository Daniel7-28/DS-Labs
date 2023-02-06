package ciic4020.lab10.testerClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class TestingUtils {
	public static ArrayList<Integer> generateListOfIntegers(int n) { 
		ArrayList<Integer> results = new ArrayList<>(n); 
		
		Random r = new Random(); 
		
		for (int i = 0; i < n; i++) 
			results.add(r.nextInt(n)); 
		
		return results; 
	}

	public static ArrayList<Integer> generateAscendingIntegers(int n) { 
		ArrayList<Integer> results = new ArrayList<>(n); 
		
		for (int i = 1; i <= n; i++) 
			results.add(i); 
		
		return results; 
	}
	
	public static <E> void displayListElements(String msg, ArrayList<E> list) { 
		System.out.println(msg); 
		for (int i=0; i<list.size(); i++) 
			System.out.println("list["+ i + "] = " + list.get(i)); 
		System.out.println("END\n\n"); 
	}

	/**
	 * Test whether the integers are in order, per the comparator.
	 * 
	 * @param list  The ArrayList to test
	 * @param cmp   The comparator to determine the order
	 * @return      True if ArrayList is in order per comparator, false otherwise.
	 */
	public static <E> boolean isInOrder(ArrayList<E> list, Comparator<E> cmp) {
		for (int i = 0; i < list.size() - 1; i++)
			if (cmp.compare(list.get(i), list.get(i + 1)) > 0)
				return false;
		return true;
	}
}
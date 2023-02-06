package ciic4020.lab10.testerClasses;

import java.util.Comparator;

public class IntegerComparator2 implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	}

	/****************************************** 
	 * TODO ADD YOUR ANSWER TO EXERCISE 6 HERE* 
	 ******************************************/
	
	/*	The difference between comparator1 and comparator2
		is that comparator1 use the first element to compare
		and comparator2 use the second element,and  
		because of this using comparator1 will sort the elements from smallest to biggest
		and comparator2 from biggest to smallest.
		Yes, the results are as I expected.
	*/
	
}

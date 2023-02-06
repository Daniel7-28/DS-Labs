package ciic4020.lab10.testerClasses;

import java.util.ArrayList;
import java.util.Comparator;

import ciic4020.lab10.strategiesClasses.MergeSort;

public class MergeSortTester {

	public static void main(String[] args) {
		
		ArrayList<Integer> data = TestingUtils.generateListOfIntegers(500); 
		
		TestingUtils.displayListElements("Original Data", data);
		
		Comparator<Integer> cmp = new IntegerComparator1();
		MergeSort<Integer> sorter = new MergeSort<>(cmp); 
		 
		sorter.sortList(data);
		
		TestingUtils.displayListElements("Sorted", data);

		if (!TestingUtils.isInOrder(data, cmp))
			System.out.println(sorter.getName() + " failed!");
	}

}

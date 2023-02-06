package ciic4020.lab10.strategiesClasses;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementation of the in-place version of Quicksort.
 * 
 * @author pedroirivera-vega
 */
public class QuickSort<E> extends AbstractSortingStrategy<E> {

	public QuickSort(Comparator<E> cmp) { 
		super("QuickSort", cmp); 
	}

	@Override
	public void sortList(ArrayList<E> dataSet) {
		/* We start things off by telling qs to sort the whole thing */
		qs(dataSet, 0, dataSet.size()-1); 
	}

	/**
	 * Applies the divide and conquer strategy to sort using 
	 * QuickSort algorithm. It works on the portion of the ArrayList
	 * from position first to position last (first..last). 
	 * 
	 * @param data   The ArrayList being sorted
	 * @param first  The index of first position in portion to be sorted
	 * @param last   The index of last  position in portion to be sorted
	 */
	private void qs(ArrayList<E> data, int first, int last) {
		if(first < last) {
			int p = partitionList(data, first, last);
			
			qs(data, first, p - 1);
			qs(data, p + 1, last);
		}
	}

	/**
	 * Partition method. ... See discussion in lab's document.
	 * 
	 * @param data   The ArrayList being partitioned
	 * @param first  The index of first position in portion to be partitioned
	 * @param last   The index of last  position in portion to be partitioned
	 * @return       The index where the pivot was placed
	 */
	private int partitionList(ArrayList<E> data, int first, int last) { 
		E pivot = data.get(last); 
		int left = first, right = last-1; 

		while (left <= right) {
			while (left <= right && cmp.compare(data.get(left), pivot) <= 0)
				left++; 
			while (left <= right && cmp.compare(data.get(right), pivot) > 0)
				right--; 
			if (left < right)
				SortingUtils.swapListElements(data, left++, right--); 
		}
		SortingUtils.swapListElements(data, left, last); 

		return left; 

	}
}
package ciic4020.lab10.strategiesClasses;

import java.util.ArrayList;

public class SortingUtils {

	public static <E> void swapListElements(ArrayList<E> list, int i, int j) { 
		list.set(i, list.set(j, list.get(i)));
	}
}
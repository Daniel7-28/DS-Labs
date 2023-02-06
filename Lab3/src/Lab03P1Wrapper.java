import java.util.Arrays;

public class Lab03P1Wrapper {

	public static int[] twoSum(int[] array, int targetSum) {
		Arrays.sort(array);
		
		int i = 0;
		int j = array.length - 1;
		int[] emptyArray= {};
		while(i < j) {
			if(array[i] + array[j] > targetSum) {
				j--;
			} 
			else if(array[i] + array[j] < targetSum) {
				i++;
			}
			else if(array[i] + array[j] == targetSum){
				int[] subArray = {array[i], array[j]};
				return subArray;
			}
		}
		return emptyArray; 
	}
}
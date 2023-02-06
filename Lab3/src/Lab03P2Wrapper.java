public class Lab03P2Wrapper {

	public static int firstDuplicate(int[] array) {
		int n = array.length;
		
		boolean[] flag = new boolean[n + 1];
		
		for (int i = 0; i < n; i++) {
			if(array[i] >= 1 && array[i] <= n) {
				if(flag[array[i]]) return array[i];
				flag[array[i]] = true;
			}
		}
		return -1;
	}
}
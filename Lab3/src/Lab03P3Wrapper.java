public class Lab03P3Wrapper{

	public static int[] searchMatrix(int[][] matrix, int target){
		int i =0;
		int n = matrix.length; //rows
		int m = matrix[0].length - 1; //columns

			while(i < n && m >= 0) {
				if(matrix[i][m] == target) {
					return new int[] {i, m};
				}
				if(matrix[i][m] > target){
						m--;	
				}
				else {
						i++;
				}
			}
		return new int[] {-1,-1};
	}
}
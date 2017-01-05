package testDao;

import java.util.Random;

public class Test {

	private static int[] getRandomIndex(int number) {
		int[] result = new int[number];
		while ((number--) > 0) {
			int temp = getNum(result.length);
			while (true && number >= 0) {
				if (check(temp, result)) {
					temp = getNum(result.length);
				} else {
					result[number] = temp;
					break;
				}
			}
		}
		return result;
	}

	private static boolean check(int t, int[] result) {
		boolean flag = false;
		for (int i = 0; i < result.length; i++) {
			if (t == result[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static int getNum(int number) {
		Random random = new Random();
		return random.nextInt(number)+1;
	}
	
	public static void main(String[] args) throws Exception {  
        
    //  int[] result = getRandomIndex(12);
		double[] result = new double[2];
      for (int i = 0; i < result.length; i++) {
    		  System.out.print((result[i])+" ");
		}
		System.out.println();
    }  
}

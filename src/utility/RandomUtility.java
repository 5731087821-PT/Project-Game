package utility;

import java.util.Random;

public class RandomUtility {
	
	private static Random random = new Random();
	
	public static int random(int s,int e) {
		return random.nextInt(e-s+1) + s;
	}
}

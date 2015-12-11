package utility;

import java.util.Random;

public class Randomul {
	
	private static Random rand = new Random();
	
	public static int rand(int s,int e) {
		return rand.nextInt(e-s+1) + s;
	}
}

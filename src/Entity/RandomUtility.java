package Entity;

import java.util.Random;

public class RandomUtility {
	public static Random random = new Random();
	
	public RandomUtility(){
		
	}
	
	public static int random(int min, int max){
		return random.nextInt(max-min)+min;
	}
}
package entity;

import java.util.Random;

public class RandomUtility {
	static Random rand = new Random(); 
	RandomUtility(){
		
	}
	public static int random(int s,int e){
//		return (int) Math.random()*(a-b)+a;
		return s + rand.nextInt(e-s+1); 
	}
}

package utility;

public class Debugger {
	private static int count;
	public static void printTest(Object obj){
		System.out.println((++count) + " -> " + obj.getClass() + " " +obj.hashCode());
	}
}

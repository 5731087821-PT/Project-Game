package utility;

public class TimeToCounter {
	public static int getCounter(int timeINms){
		int delayTime = (timeINms-10)/ConfigurableOption.sleepTime;
		return delayTime;
	}
}

package utility;

public class TimeCounter {
	public static int getTimeCounter(int timeINms){
		int delayTime = (timeINms-10)/ConfigurableOption.sleepTime;
		return delayTime;
	}
}

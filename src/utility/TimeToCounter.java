package utility;

public class TimeToCounter {
	public static int ClockCycle = ConfigurableOption.sleepTime;
	public static int getCounter(int timeINms){
		int delayTime = (timeINms-10)/ClockCycle;
		return delayTime;
	}
}

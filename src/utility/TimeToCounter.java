package utility;

public class TimeToCounter {
	public static int ClockCycle = ConfigurableOption.sleepTime;
	public static int getCounter(int timeINms){
		return (timeINms-10)/ClockCycle;
	}
}

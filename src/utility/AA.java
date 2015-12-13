package utility;

public class AA {
	public static int getCounterTime(int timeINms){
		int delayTime = (timeINms-10)/ConfigurableOption.sleepTime;
		return delayTime;
	}
}

package utility;

import Main.ScreenManager;

public class ConfigurableOption {
	public static final boolean debugGraphic = false;
	
	public static final int sleepTime = 15;
	public static final int northScreenHeight = 400;//200//400
	public static final int southScreenHeight = 400;//360//400
	public static final int screenWidth = 1280;//720//1280
	public static final int screenHeight = northScreenHeight+southScreenHeight;
	public static final int statusHeight = 40;
	public static final int characterHeight = 140;
	public static final int ENDSTAGE = 4;
	
	public static final int STARTSCREEN = ScreenManager.INTROSCREEN;

	public static final int nextZombieSpeedUp = (int) ((ConfigurableOption.screenWidth*0.20)/100.0);

	public static boolean PAUSE = false;

	public static int xGateway0 = (screenWidth*36)/100;
	public static int yGateway0 = 0;
	public static int xGateway1 = (screenWidth*36)/100;
	public static int yGateway1 = 0;
	public static int xGateway2 = (screenWidth*62)/100;
	public static int yGateway2 = 0;
	
	public static int xSpacebarTab = 3*screenWidth/10;
	public static int ySpacebarTab = 30;//30
	public static int spacebarTabHeight = 20;//20
	public static int gapWidth = 50;
	public static int tabDistance = 4*screenWidth/10;
	
//	public static int wireFrameWidth = (int) (tabDistance*0.5);
	public static int wireFrameHeight = (int) (southScreenHeight*0.45);
	
	public static int stageStartAt = 0;
	public static int stageNow;
	public static int seedCoin = 0;
	public static int coinLimit = 10;
	public static int coinCounter = 0;
	public static int COINS = 0;
	
	public static int TEST = 0;
}

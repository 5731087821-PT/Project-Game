/**
 * @author Thuchchai Jiamsorn (5731050521) 
 * @version 11 Nov 2015
 * Lab5 part2 (2/2015) in 2110215 Prog Meth
 */
package render;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lib.ConfigurableOption;
import lib.GameScreen;
import lib.GameTitle;
import lib.GameWindow;
import lib.IGameLogic;
import lib.IRenderableHolder;
import lib.IRenderableObject;



public class GameManager {

	private static final int REFRESH_DELAY = 10;
	public static final int TICK_PER_SECONDS = 1000/REFRESH_DELAY;
	
	private static GameTitle titleScene;
	private static GameScreen gameScreen;
	private static GameWindow gameWindow;
	private static JPanel nextScene = null;
	
	public static void runGame(IGameLogic gameLogic){
		titleScene = new GameTitle();
		
		if(gameLogic instanceof IRenderableHolder){
			gameScreen = new GameScreen((IRenderableHolder)gameLogic);
		}else{
			gameScreen = new GameScreen(new IRenderableHolder() {
				private List<IRenderableObject> emptyList = new ArrayList<IRenderableObject>(0);
				@Override
				public List<IRenderableObject> getSortedRenderableObject() {
					return emptyList;
				}
			});
		}
		
		gameWindow = new GameWindow(titleScene);
		
		while(true){
			try {
				Thread.sleep(REFRESH_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameWindow.getCurrentScene().repaint();
			if(gameWindow.getCurrentScene() instanceof GameScreen){
				gameLogic.logicUpdate();
				InputUtility.postUpdate();
			}
			if(nextScene != null){
				if(gameWindow.getCurrentScene() instanceof GameScreen)
					gameLogic.onExit();
				gameWindow.switchScene(nextScene);
				if(nextScene instanceof GameScreen)
					gameLogic.onStart();
				nextScene = null;
			}
		}
	}
	
	public static void goToTitle(){
		nextScene = titleScene;
	}
	
	public static void newGame(){
		nextScene = gameScreen;
	}
	
	public static void resizeScreen(){
		gameScreen.applyResize();
		gameWindow.setSize(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight);
		titleScene.validate();
	}
}
